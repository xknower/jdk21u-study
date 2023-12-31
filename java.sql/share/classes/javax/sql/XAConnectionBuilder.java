package javax.sql;

import java.sql.SQLException;
import java.sql.ShardingKey;

/**
 * A builder created from a {@code XADataSource} object,
 * used to establish a connection to the database that the
 * {@code data source} object represents.  The connection
 * properties that were specified for the {@code data source} are used as the
 * default values by the {@code XAConnectionBuilder}.
 * <p>The following example illustrates the use of {@code XAConnectionBuilder}
 * to create a {@link XAConnection}:
 *
 * <pre>{@code
 *     XADataSource ds = new MyXADataSource();
 *     ShardingKey superShardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("EASTERN_REGION", JDBCType.VARCHAR)
 *                           .build();
 *     ShardingKey shardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("PITTSBURGH_BRANCH", JDBCType.VARCHAR)
 *                           .build();
 *     XAConnection con = ds.createXAConnectionBuilder()
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
public interface XAConnectionBuilder  {

    /**
     * Specifies the username to be used when creating a connection
     *
     * @param username the database user on whose behalf the connection is being
     * made
     * @return the same {@code XAConnectionBuilder} instance
     */
    XAConnectionBuilder user(String username);

    /**
     * Specifies the password to be used when creating a connection
     *
     * @param password the password to use for this connection. May be {@code null}
     * @return the same {@code XAConnectionBuilder} instance
     */
    XAConnectionBuilder password(String password);

    /**
     * Specifies a {@code shardingKey} to be used when creating a connection
     *
     * @param shardingKey the ShardingKey. May be {@code null}
     * @return the same {@code XAConnectionBuilder} instance
     * @see java.sql.ShardingKey
     * @see java.sql.ShardingKeyBuilder
     */
    XAConnectionBuilder shardingKey(ShardingKey shardingKey);

    /**
     * Specifies a {@code superShardingKey} to be used when creating a connection
     *
     * @param superShardingKey the SuperShardingKey. May be {@code null}
     * @return the same {@code XAConnectionBuilder} instance
     * @see java.sql.ShardingKey
     * @see java.sql.ShardingKeyBuilder
     */
    XAConnectionBuilder superShardingKey(ShardingKey superShardingKey);

    /**
     * Returns an instance of the object defined by this builder.
     *
     * @return The built object
     * @throws java.sql.SQLException If an error occurs building the object
     */
    XAConnection build() throws SQLException;

}
