package org.catalytic.sdk.search;

import java.time.OffsetDateTime;

/**
 * An expression object for a range of datetime's
 */
public class DateTimeOffsetRange {
    private OffsetDateTime lowerBoundInclusive;
    private OffsetDateTime upperBoundInclusive;

    public DateTimeOffsetRange(OffsetDateTime lowerBoundInclusive) {
        this.lowerBoundInclusive = lowerBoundInclusive;
    }

    public DateTimeOffsetRange(OffsetDateTime lowerBoundInclusive, OffsetDateTime upperBoundInclusive) {
        this.lowerBoundInclusive = lowerBoundInclusive;
        this.upperBoundInclusive = upperBoundInclusive;
    }

    public OffsetDateTime getLowerBoundInclusive() {
        return lowerBoundInclusive;
    }

    public void setLowerBoundInclusive(OffsetDateTime lowerBoundInclusive) {
        this.lowerBoundInclusive = lowerBoundInclusive;
    }

    public OffsetDateTime getUpperBoundInclusive() {
        return upperBoundInclusive;
    }

    public void setUpperBoundInclusive(OffsetDateTime upperBoundInclusive) {
        this.upperBoundInclusive = upperBoundInclusive;
    }

    @Override
    public String toString() {
        return "DateTimeOffsetRange{" +
                "lowerBoundInclusive=" + lowerBoundInclusive +
                ", upperBoundInclusive=" + upperBoundInclusive +
                '}';
    }
}
