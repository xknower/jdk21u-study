package jdk.jfr.internal.query;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.internal.query.Configuration.Truncate;
import jdk.jfr.internal.query.Query.Grouper;
import jdk.jfr.internal.query.Query.OrderElement;

/**
 * Field is the core class of the package.
 * <p>
 * It contains all the information related to how a field in a query should be
 * grouped, sorted, formatted and aggregated.
 * <p>
 * Defaults are derived from the metadata available in the event type, for
 * example, numeric fields are right-aligned in the output, but can also
 * be set using query clauses COLUMN and FORMAT.
 * <p>
 * Settings in {@Configuration} overrides any field setting.
 */
final class Field {
    // The fields to use as data sources, for example, when a
    // field references multiple event types. First field
    // is always the same as this field.
    // (It's confusing, but hard to come up with an another
    // abstraction that doesn't complicate the implementation.)
    final List<Field> sourceFields = new ArrayList<>();

    // Source type
    final FilteredType type;

    // Symbolic name
    final String name;

    // Index in the fields list
    int index;

    // Human readable name
    String label;

    // Function to extract a value from an event object
    Function<RecordedEvent, Object> valueGetter;

    // Set if the field is part of GROUP BY clause
    Grouper grouper;

    // Set if the field is part of ORDER BY clause
    OrderElement orderer;

    // Set if the field is part of an aggregation
    Aggregator aggregator = Aggregator.MISSING;

    // Height of a table cell
    int cellHeight = 1;

    // Truncation mode (beginning or end)
    Truncate truncate;

    // If the value visible
    boolean visible;

    // Should value be aligned left
    boolean alignLeft;

    // Should value be normalized between 0.0 and 1.0
    boolean normalized;

    // Should column be sorted textually
    boolean lexicalSort;

    // A percentage value
    boolean percentage;

    // A frequency value
    boolean frequency;

    // A memory address
    boolean memoryAddress;

    // A byte value
    boolean bytes;

    // A bits value
    boolean bits;

    // A fractional type (double or float)
    boolean fractionalType;

    // An integral type (byte, short, int, long)
    boolean integralType;

    // A java.time.Duration
    boolean timespan;

    // A java.time.Instant
    boolean timestamp;

    // Used by LAST_BATCH aggregator
    Instant last = Instant.EPOCH;

    // The data type, for example, jdk.types.Frame or java.lang.String
    String dataType;

    // Should not be given additional whitespace if available
    public boolean fixedWidth;

    // Text to render if value is missing, typically used when value is null
    public String missingText = "N/A";

    public Field(FilteredType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return type.getName() + "#" + name;
    }
}
