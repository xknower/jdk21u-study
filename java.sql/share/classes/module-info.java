/**
 * Defines the JDBC API.
 *
 * @uses java.sql.Driver
 *
 * @moduleGraph
 * @since 9
 */
module java.sql {
    requires transitive java.logging;
    requires transitive java.transaction.xa;
    requires transitive java.xml;

    exports java.sql;
    exports javax.sql;

    uses java.sql.Driver;
}
