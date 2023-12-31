package javax.sql;

import java.sql.SQLException;
import java.sql.ShardingKey;

/**
 * A builder created from a {@code ConnectionPoolDataSource} object,
 * used to establish a connection to the database that the
 * {@code data source} object represents.  The connection
 * properties that were specified for the {@code data source} are used as the
 * default values by the {@code PooledConnectionBuilder}.
 * <p>The following example illustrates the use of {@code PooledConnectionBuilder}
 * to create a {@link XAConnection}:
 *
 * <pre>{@code
 *     ConnectionPoolDataSource ds = new MyConnectionPoolDataSource();
 *     ShardingKey superShardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("EASTERN_REGION", JDBCType.VARCHAR)
 *                           .build();
 *     ShardingKey shardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("PITTSBURGH_BRANCH", JDBCType.VARCHAR)
 *                           .build();
 *     PooledConnection con = ds.createPooledConnectionBuilder()
 *                       .user("rafa")
 *                       .password("tennis")
 *                       .shardingKey(shardingKey)
 *                       .superShardingKey(superShardingKey)
 *                       .build();
 * }</pre>
 *
 * @since 9
 *
 */
public interface PooledConnectionBuilder  {

    /**
     * Specifies the username to be used when creating a connection
     *
     * @param username the database user on whose behalf the connection is being
     * made
     * @return the same {@code PooledConnectionBuilder} instance
     */
    PooledConnectionBuilder user(String username);

    /**
     * Specifies the password to be used when creating a connection
     *
     * @param password the password to use for this connection. May be {@code null}
     * @return the same {@code PooledConnectionBuilder} instance
     */
    PooledConnectionBuilder password(String password);

    /**
     * Specifies a {@code shardingKey} to be used when creating a connection
     *
     * @param shardingKey the ShardingKey. May be {@code null}
     * @return the same {@code PooledConnectionBuilder} instance
     * @see java.sql.ShardingKey
     * @see java.sql.ShardingKeyBuilder
     */
    PooledConnectionBuilder shardingKey(ShardingKey shardingKey);

    /**
     * Specifies a {@code superShardingKey} to be used when creating a connection
     *
     * @param superShardingKey the SuperShardingKey. May be {@code null}
     * @return the same {@code PooledConnectionBuilder} instance
     * @see java.sql.ShardingKey
     * @see java.sql.ShardingKeyBuilder
     */
    PooledConnectionBuilder superShardingKey(ShardingKey superShardingKey);

    /**
     * Returns an instance of the object defined by this builder.
     *
     * @return The built object
     * @throws java.sql.SQLException If an error occurs building the object
     */
    PooledConnection build() throws SQLException;

}
