#ifndef MsiDb_h
#define MsiDb_h

#include <windows.h>
#include <msiquery.h>

#include "MsiUtils.h"


class Guid;

namespace msi {

void closeDatabaseView(MSIHANDLE h);

class CA;
class DatabaseView;
class DatabaseRecord;


/**
 * Opens product's database to query properties.
 * The database is opened in R/O mode, i.e. it is safe to call this method
 * even if there is active install/uninstall session. Unlike MsiOpenProduct(),
 * it never fails with 1618 ('Another installation is
 * already in progress') error code.
 *
 * Database can be opened from product code GUID, path to msi package or from
 * custom action.
 *
 * If opened from CA the database is opened in R/W mode, however only adding
 * new temporary records is supported. It is forbidden to change data in
 * existing records.
 */
class Database {
public:
    /**
     * Opens msi database from the given product code GUID.
     * Throws exception if fails.
     */
    explicit Database(const Guid& productCode);

    /**
     * Opens msi database from the given path to .msi file.
     * Throws exception if fails.
     */
    explicit Database(const tstring& msiPath);

    /**
     * Opens msi database from the given custom action.
     * Throws exception if fails.
     */
    explicit Database(const CA& ca);

    ~Database() {
      if (dbHandle != 0) {
        closeMSIHANDLE(dbHandle);
      }
    }

    /**
     * Returns value of property with the given name.
     * Throws NoMoreItemsError if property with the given name doesn't exist
     * or Error if some error occurred.
     */
    tstring getProperty(const tstring& name) const;

    /**
     * Returns value of property with the given name.
     * Returns empty string if property with the given name doesn't exist or
     * if some error occurred.
     */
    tstring getProperty(const std::nothrow_t&, const tstring& name) const;

    friend class DatabaseView;

private:
    Database(const Database&);
    Database& operator=(const Database&);
private:
    const tstring msiPath;
    MSIHANDLE dbHandle;
};

typedef std::unique_ptr<Database> DatabasePtr;


class DatabaseRecord {
public:
    DatabaseRecord(): handle(MSIHANDLE(0)) {
    }

    DatabaseRecord(const DatabaseRecord& other): handle(MSIHANDLE(0)) {
        handle = other.handle;
        other.handle = 0;
    }

    DatabaseRecord& operator=(const DatabaseRecord& other);

    friend class DatabaseView;

    explicit DatabaseRecord(unsigned fieldCount);

    explicit DatabaseRecord(DatabaseView& view) : handle(MSIHANDLE(0)) {
        fetch(view);
    }

    ~DatabaseRecord() {
      if (handle != 0) {
        closeMSIHANDLE(handle);
      }
    }

    DatabaseRecord& fetch(DatabaseView& view);

    DatabaseRecord& tryFetch(DatabaseView& view);

    DatabaseRecord& setString(unsigned idx, const tstring& v);

    DatabaseRecord& setInteger(unsigned idx, int v);

    DatabaseRecord& setStreamFromFile(unsigned idx, const tstring& v);

    unsigned getFieldCount() const;

    tstring getString(unsigned idx) const;

    int getInteger(unsigned idx) const;

    void saveStreamToFile(unsigned idx, const tstring& path) const;

    bool empty() const {
        return 0 == handle;
    }

    MSIHANDLE getHandle() const {
        return handle;
    }

private:
    mutable MSIHANDLE handle;
};


class DatabaseView {
public:
    DatabaseView(const Database& db, const tstring& sqlQuery,
                        const DatabaseRecord& queryParam=DatabaseRecord());

    ~DatabaseView() {
      if (handle != 0) {
        closeMSIHANDLE(handle);
      }
    }

    DatabaseRecord fetch();

    DatabaseRecord tryFetch();

    DatabaseView& modify(const DatabaseRecord& record, MSIMODIFY mode);

private:
    tstring sqlQuery;
    const Database& db;
    MSIHANDLE handle;
};

} // namespace msi

#endif // #ifndef MsiDb_h
