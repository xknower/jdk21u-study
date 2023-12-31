package java.sql;

/**
 * A builder created from a {@code DataSource}  or {@code XADataSource} object,
 * used to create a {@link ShardingKey} with sub-keys of supported data types.
 * Implementations must
 * support JDBCType.VARCHAR and  may also support additional data types.
 * <p>
 * The following example illustrates the use of {@code ShardingKeyBuilder} to
 * create a {@link ShardingKey}:
 * <pre>
 * {@code
 *
 *     DataSource ds = new MyDataSource();
 *     ShardingKey shardingKey = ds.createShardingKeyBuilder()
 *                           .subkey("abc", JDBCType.VARCHAR)
 *                           .subkey(94002, JDBCType.INTEGER)
 *                           .build();
 * }
 * </pre>
 *
 * @since 9
 */
public interface ShardingKeyBuilder {

    /**
     * This method will be called to add a subkey into a Sharding Key object being
     * built. The order in which subkey method is called is important as it
     * indicates the order of placement of the subkey within the Sharding Key.
     *
     * @param subkey contains the object that needs to be part of shard sub key
     * @param subkeyType sub-key data type of type java.sql.SQLType
     * @return this builder object
     */
    ShardingKeyBuilder subkey(Object subkey, SQLType subkeyType);

    /**
     * Returns an instance of the object defined by this builder.
     *
     * @return The built object
     * @throws java.sql.SQLException If an error occurs building the object
     */
    ShardingKey build() throws SQLException;
}
