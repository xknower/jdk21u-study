#include "MsiDb.h"
#include "MsiCA.h"
#include "Version.h"
#include "FileUtils.h"
#include "WinErrorHandling.h"


JP_CA(CheckInstallDir) {
    const tstring installDir = ca.getProperty(_T("INSTALLDIR"));

    bool canProceed = !FileUtils::isFileExists(installDir);
    if (!canProceed && FileUtils::isDirectory(installDir)) {
        canProceed = !FileUtils::isDirectoryNotEmpty(installDir);
    }

    ca.setProperty(_T("INSTALLDIR_VALID"), canProceed ? _T("1") : _T("0"));
}


namespace {

typedef Version<
    VersionDetails::Base<10, VersionDetails::Parser, 2>
> DottedVersion;


class ProductInfo {
public:
    explicit ProductInfo(const Guid& pc): productCode(pc),
            version(msi::getProductInfo(pc, INSTALLPROPERTY_VERSIONSTRING)) {
    }

    const DottedVersion& getVersion() const {
        return version;
    }

    const Guid& getProductCode() const {
        return productCode;
    }

private:
    Guid productCode;
    DottedVersion version;
};


void findInstalledProducts(const Guid& upgradeCode,
                                            std::vector<ProductInfo>& products) {
    const LPCTSTR upgradeCodeStr = upgradeCode.toMsiString().c_str();
    for (DWORD productCodeIdx = 0; true; ++productCodeIdx) {
        TCHAR productCode[39 /* http://msdn.microsoft.com/en-us/library/aa370101(v=vs.85).aspx */];
        const UINT status = MsiEnumRelatedProducts(upgradeCodeStr, 0,
                                              productCodeIdx, productCode);
        if (ERROR_NO_MORE_ITEMS == status) {
            break;
        }

        if (ERROR_SUCCESS == status) {
            LOG_TRACE(tstrings::any() << "Found " << productCode << " product");
            JP_NO_THROW(products.push_back(ProductInfo(Guid(productCode))));
        } else {
            LOG_WARNING(tstrings::any()
                        << "MsiEnumRelatedProducts("
                        << upgradeCodeStr << ", "
                        << productCodeIdx
                        << ") failed with error=[" << status << "]");
            if (ERROR_INVALID_PARAMETER == status) {
                break;
            }
        }
    }
}

DottedVersion getDottedVersion(const msi::DatabaseRecord& record, UINT idx) {
    if (!MsiRecordIsNull(record.getHandle(), idx)) {
        JP_NO_THROW(return DottedVersion(record.getString(idx)));
    }

    return DottedVersion();
}

bool dbContainsUpgradeTable(const msi::Database &db) {
    msi::DatabaseView view(db,
                    _T("SELECT Name FROM _Tables WHERE Name = 'Upgrade'"));
    msi::DatabaseRecord record;
    while (!record.tryFetch(view).empty()) {
        return true;
    }
    return false;
}

} // namespace

JP_CA(FindRelatedProductsEx) {
    if (ca.isInMode(MSIRUNMODE_MAINTENANCE)) {
        // MSI skips standard FindRelatedProducts action in maintenance mode,
        // so should we do for custom FindRelatedProducts action
        LOG_TRACE("Not run in maintenance mode");
        return;
    }

    const msi::Database db(ca);
    if (!dbContainsUpgradeTable(db)) {
        LOG_TRACE("The package doesn't contain Upgrade table");
        return;
    }

    const Guid upgradeCode = Guid(ca.getProperty(_T("UpgradeCode")));

    std::vector<ProductInfo> installedProducts;
    findInstalledProducts(upgradeCode, installedProducts);

    bool migratePropRemoved = false;

    // https://docs.microsoft.com/en-us/windows/win32/adsi/sql-dialect
    msi::DatabaseView view(db, (tstrings::any()
            << _T("SELECT `VersionMin`,`VersionMax`,`Attributes`,`ActionProperty` FROM Upgrade WHERE `ActionProperty` <> NULL And `UpgradeCode` = '")
            << upgradeCode.toMsiString() << _T("'")).tstr());
    msi::DatabaseRecord record;
    while (!record.tryFetch(view).empty()) {
        const tstring actionProperty = record.getString(4);

        // Clean up properties set by the standard FindRelatedProducts action
        ca.removeProperty(actionProperty);
        if (!migratePropRemoved) {
            ca.removeProperty(_T("MIGRATE"));
            migratePropRemoved = true;
        }

        const DottedVersion versionMin = getDottedVersion(record, 1);
        const DottedVersion versionMax = getDottedVersion(record, 2);

        const int attrs = MsiRecordIsNull(
                          record.getHandle(), 3) ? 0 : record.getInteger(3);

        std::vector<ProductInfo>::const_iterator productIt =
                                                installedProducts.begin();
        std::vector<ProductInfo>::const_iterator productEnd =
                                                installedProducts.end();
        for (; productIt != productEnd; ++productIt) {
            bool minMatch;
            if (versionMin.source().empty()) {
                minMatch = true;
            } else if (attrs & msidbUpgradeAttributesVersionMinInclusive) {
                minMatch = (versionMin <= productIt->getVersion());
            } else {
                minMatch = (versionMin < productIt->getVersion());
            }

            bool maxMatch;
            if (versionMax.source().empty()) {
                maxMatch = true;
            } else if (attrs & msidbUpgradeAttributesVersionMaxInclusive) {
                maxMatch = (productIt->getVersion() <= versionMax);
            } else {
                maxMatch = (productIt->getVersion() < versionMax);
            }

            if (minMatch && maxMatch) {
                tstring value = productIt->getProductCode().toMsiString();
                ca.setProperty(actionProperty, value);
                ca.setProperty(_T("MIGRATE"), value);
                // Bail out after the first match as action
                // property has been set already.
                // There is no way to communicate multiple product codes
                // through a single property.
                break;
            }
        }
    }
}
