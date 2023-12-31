package javax.sql;

import java.sql.*;

/**
 * A factory for {@code XAConnection} objects that is used internally.
 * An object that implements the {@code XADataSource} interface is
 * typically registered with a naming service that uses the
 * Java Naming and Directory Interface
 * (JNDI).
 *  <p>
 * An implementation of {@code XADataSource} must include a public no-arg
 * constructor.
 * @since 1.4
 */

public interface XADataSource extends CommonDataSource {

  /**
   * Attempts to establish a physical database connection that can be
   * used in a distributed transaction.
   *
   * @return  an {@code XAConnection} object, which represents a
   *          physical connection to a data source, that can be used in
   *          a distributed transaction
   * @throws SQLException if a database access error occurs
   * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
   * this method
   * @throws SQLTimeoutException when the driver has determined that the
   * timeout value specified by the {@code setLoginTimeout} method
   * has been exceeded and has at least tried to cancel the
   * current database connection attempt
   * @since 1.4
   */
  XAConnection getXAConnection() throws SQLException;

  /**
   * Attempts to establish a physical database connection, using the given
   * user name and password. The connection that is returned is one that
   * can be used in a distributed transaction.
   *
   * @param user the database user on whose behalf the connection is being made
   * @param password the user's password
   * @return  an {@code XAConnection} object, which represents a
   *          physical connection to a data source, that can be used in
   *          a distributed transaction
   * @throws SQLException if a database access error occurs
   * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
   * this method
   * @throws SQLTimeoutException when the driver has determined that the
   * timeout value specified by the {@code setLoginTimeout} method
   * has been exceeded and has at least tried to cancel the
   * current database connection attempt
   * @since 1.4
   */
  XAConnection getXAConnection(String user, String password)
    throws SQLException;

  /**
   * {@inheritDoc}
   * @since 1.4
   */
  @Override
  java.io.PrintWriter getLogWriter() throws SQLException;

  /**
   * {@inheritDoc}
   * @since 1.4
   */
  @Override
  void setLogWriter(java.io.PrintWriter out) throws SQLException;

  /**
   * {@inheritDoc}
   * @since 1.4
   */
  @Override
  void setLoginTimeout(int seconds) throws SQLException;

  /**
   * {@inheritDoc}
   * @since 1.4
   */
  @Override
  int getLoginTimeout() throws SQLException;

   // JDBC 4.3

  /**
   * Creates a new {@code XAConnectionBuilder} instance
   * @implSpec
   * The default implementation will throw a {@code SQLFeatureNotSupportedException}.
   * @return The XAConnectionBuilder instance that was created
   * @throws SQLException if an error occurs creating the builder
   * @throws SQLFeatureNotSupportedException if the driver does not support sharding
   * @since 9
   * @see XAConnectionBuilder
   */
  default XAConnectionBuilder createXAConnectionBuilder() throws SQLException {
        throw new SQLFeatureNotSupportedException("createXAConnectionBuilder not implemented");
  };

 }
