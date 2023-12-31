package jdk.management.jfr;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

import jdk.jfr.Configuration;


/**
 * Management representation of a {@code Configuration}.
 *
 * @see Configuration
 *
 * @since 9
 */
public final class ConfigurationInfo {
    private final Map<String, String> settings;
    private final String name;
    private final String label;
    private final String description;
    private final String provider;
    private final String contents;

    ConfigurationInfo(Configuration config) {
        this.settings = config.getSettings();
        this.name = config.getName();
        this.label = config.getLabel();
        this.description = config.getDescription();
        this.provider = config.getProvider();
        this.contents = config.getContents();
    }

    private ConfigurationInfo(CompositeData cd) {
        this.settings = createMap(cd.get("settings"));
        this.name = (String) cd.get("name");
        this.label = (String) cd.get("label");
        this.description = (String) cd.get("description");
        this.provider = (String) cd.get("provider");
        this.contents = (String) cd.get("contents");
    }

    private static Map<String, String> createMap(Object o) {
        if (o instanceof TabularData td) {
            Collection<?> values = td.values();
            Map<String, String> map = HashMap.newHashMap(values.size());
            for (Object value : td.values()) {
                if (value instanceof CompositeData cdRow) {
                    Object k = cdRow.get("key");
                    Object v = cdRow.get("value");
                    if (k instanceof String sk && v instanceof String sv) {
                        map.put(sk, sv);
                    }
                }
            }
            return Collections.unmodifiableMap(map);
        }
        return Collections.emptyMap();
    }

    /**
     * Returns the provider of the configuration associated with this
     * {@code ConfigurationInfo} (for example, {@code "OpenJDK"}).
     *
     * @return the provider, or {@code null} if doesn't exist
     *
     * @see Configuration#getProvider()
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Returns the textual representation of the configuration associated with
     * this {@code ConfigurationInfo}, typically the contents of the
     * configuration file that was used to create the configuration.
     *
     * @return contents, or {@code null} if doesn't exist
     *
     * @see Configuration#getContents()
     */
    public String getContents() {
        return contents;
    }

    /**
     * Returns the settings for the configuration associated with this
     * {@code ConfigurationInfo}.
     *
     * @return a {@code Map} with settings, not {@code null}
     *
     * @see Configuration#getSettings()
     */
    public Map<String, String> getSettings() {
        return settings;
    }

    /**
     * Returns the human-readable name (for example, {@code "Continuous"} or {@code "Profiling"}) for
     * the configuration associated with this {@code ConfigurationInfo}
     *
     * @return the label, or {@code null} if doesn't exist
     *
     * @see Configuration#getLabel()
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the name of the configuration associated with this
     * {@code ConfigurationInfo} (for example, {@code "default"}).
     *
     * @return the name, or {@code null} if doesn't exist
     *
     * @see Configuration#getLabel()
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a short sentence that describes the configuration associated with
     * this {@code ConfigurationInfo} (for example, {@code "Low
     * overhead configuration safe for continuous use in production
     * environments"}.
     *
     * @return the description, or {@code null} if doesn't exist
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a {@code ConfigurationInfo} object represented by the specified
     * {@code CompositeData}.
     * <p>
     * The following table shows the required attributes that the specified {@code CompositeData} must contain.
     * <blockquote>
     * <table class="striped">
     * <caption>Required names and types for CompositeData</caption>
     * <thead>
     * <tr>
     * <th scope="col" style="text-align:left">Name</th>
     * <th scope="col" style="text-align:left">Type</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <th scope="row">name</th>
     * <td>{@code String}</td>
     * </tr>
     * <tr>
     * <th scope="row">label</th>
     * <td>{@code String}</td>
     * </tr>
     * <tr>
     * <th scope="row">description</th>
     * <td>{@code String}</td>
     * </tr>
     * <tr>
     * <th scope="row">provider</th>
     * <td>{@code String}</td>
     * </tr>
     * <tr>
     * <th scope="row">contents</th>
     * <td>{@code String}</td>
     * </tr>
     *
     * <tr>
     * <th scope="row">settings</th>
     * <td>{@code javax.management.openmbean.TabularData} with a
     * {@code TabularType} with the keys {@code "key"} and {@code "value"}, both
     * of the {@code String} type</td>
     * </tr>
     * </tbody>
     * </table>
     * </blockquote>
     *
     * @param cd {@code CompositeData} representing a {@code ConfigurationInfo}
     *
     * @throws IllegalArgumentException if {@code cd} does not represent a
     *         {@code ConfigurationInfo} with the required attributes
     *
     * @return a {@code ConfigurationInfo} object represented by {@code cd} if
     *         {@code cd} is not {@code null}, {@code null} otherwise
     */
    public static ConfigurationInfo from(CompositeData cd) {
        if (cd == null) {
            return null;
        }
        return new ConfigurationInfo(cd);
    }

    /**
     * Returns a description of the configuration that is associated with this
     * {@code ConfigurationInfo}.
     *
     * @return the description of the configuration, not {@code null}
     */
    @Override
    public String toString() {
        Stringifier s = new Stringifier();
        s.add("name", name);
        s.add("label", label);
        s.add("description", description);
        s.add("provider", provider);
        return s.toString();
    }
}
