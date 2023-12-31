package java.sql;

/**
 * A builder created from a {@code DataSource} object,
 * used to establish a connection to the database that the
 * {@code data source} object represents.  The connection
 * properties that were specified for the {@code data source} are used as the
 * default values by the {@code ConnectionBuilder}.
 * <p>The following example illustrates the use of {@code ConnectionBuilder}
 * to create a {@link Connection}:
 *
 * <pre>{@code
 *     DataSource ds = new MyDataSource();
 *     ShardingKey superShardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("EASTERN_REGION", JDBCType.VARCHAR)
 *                           .build();
 *     ShardingKey shardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("PITTSBURGH_BRANCH", JDBCType.VARCHAR)
 *                           .build();
 *     Connection con = ds.createConnectionBuilder()
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
public interface ConnectionBuilder  {

    /**
     * Specifies the username to be used when creating a connection
     *
     * @param username the database user on whose behalf the connection is being
     * made
     * @return the same {@code ConnectionBuilder} instance
     */
    ConnectionBuilder user(String username);

    /**
     * Specifies the password to be used when creating a connection
     *
     * @param password the password to use for this connection. May be {@code null}
     * @return the same {@code ConnectionBuilder} instance
     */
    ConnectionBuilder password(String password);

    /**
     * Specifies a {@code shardingKey} to be used when creating a connection
     *
     * @param shardingKey the ShardingKey. May be {@code null}
     * @return the same {@code ConnectionBuilder} instance
     * @see ShardingKey
     * @see ShardingKeyBuilder
     */
    ConnectionBuilder shardingKey(ShardingKey shardingKey);

    /**
     * Specifies a {@code superShardingKey} to be used when creating a connection
     *
     * @param superShardingKey the SuperShardingKey. May be {@code null}
     * @return the same {@code ConnectionBuilder} instance
     * @see ShardingKey
     * @see ShardingKeyBuilder
     */
    ConnectionBuilder superShardingKey(ShardingKey superShardingKey);

    /**
     * Returns an instance of the object defined by this builder.
     *
     * @return The built object
     * @throws java.sql.SQLException If an error occurs building the object
     */
    Connection build() throws SQLException;
}
