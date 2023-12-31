/**
 * Defines the JDBC RowSet API.
 *
 * @uses javax.sql.rowset.RowSetFactory
 *
 * @moduleGraph
 * @since 9
 */
module java.sql.rowset {
    requires transitive java.logging;
    requires transitive java.naming;
    requires transitive java.sql;

    exports javax.sql.rowset;
    exports javax.sql.rowset.serial;
    exports javax.sql.rowset.spi;

    uses javax.sql.rowset.RowSetFactory;
}
