#include <algorithm>
#include <windows.h>

#include "WinApp.h"
#include "Guid.h"
#include "SysInfo.h"
#include "MsiUtils.h"
#include "FileUtils.h"
#include "WinFileUtils.h"
#include "Executor.h"
#include "Resources.h"


namespace {
int exitCode = -1;

void launchApp() {
    const auto cmdline = SysInfo::getCommandArgs();
    if (std::find(cmdline.begin(), cmdline.end(), L"uninstall") != cmdline.end()) {
        // This is uninstall request.

        // Get product code of the product to uninstall.
        const auto productCodeUtf8 = Resource(L"product_code", RT_RCDATA).binary();
        const Guid productCode = Guid(std::string(
                (const char*)productCodeUtf8.data(), productCodeUtf8.size()));

        // Uninstall product.
        msi::SuppressUI suppressUI;
        exitCode = (int)msi::uninstall().setProductCode(productCode)(
                                                    std::nothrow).getValue();
        return;
    }

    // Create temporary directory where to extract msi file.
    const auto tempMsiDir = FileUtils::createTempDirectory();

    // Schedule temporary directory for deletion.
    FileUtils::Deleter cleaner;
    cleaner.appendRecursiveDirectory(tempMsiDir);

    const auto msiPath = FileUtils::mkpath() << tempMsiDir << L"main.msi";

    // Extract msi file.
    Resource(L"msi", RT_RCDATA).saveToFile(msiPath);

    // Setup executor to run msiexec
    Executor msiExecutor(SysInfo::getWIPath());
    msiExecutor.arg(L"/i").arg(msiPath);
    const auto args = SysInfo::getCommandArgs();
    std::for_each(args.begin(), args.end(),
            [&msiExecutor] (const tstring& arg) {
        msiExecutor.arg(arg);
    });

    // Install msi file.
    exitCode = msiExecutor.execAndWaitForExit();
}
} // namespace


int __stdcall WinMain(HINSTANCE, HINSTANCE, LPSTR lpCmdLine, int nShowCmd) {
    app::wlaunch(std::nothrow, launchApp);
    return exitCode;
}
