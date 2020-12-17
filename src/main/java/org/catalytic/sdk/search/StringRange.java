package org.catalytic.sdk.search;

/**
 * An expression object for a range of strings
 */
public class StringRange {
    private String lowerBoundInclusive;
    private String upperBoundInclusive;

    public StringRange(String lowerBoundInclusive, String upperBoundInclusive) {
        this.lowerBoundInclusive = lowerBoundInclusive;
        this.upperBoundInclusive = upperBoundInclusive;
    }

    public String getLowerBoundInclusive() {
        return lowerBoundInclusive;
    }

    public void setLowerBoundInclusive(String lowerBoundInclusive) {
        this.lowerBoundInclusive = lowerBoundInclusive;
    }

    public String getUpperBoundInclusive() {
        return upperBoundInclusive;
    }

    public void setUpperBoundInclusive(String upperBoundInclusive) {
        this.upperBoundInclusive = upperBoundInclusive;
    }

    @Override
    public String toString() {
        return "StringRange{" +
                "lowerBoundInclusive='" + lowerBoundInclusive + '\'' +
                ", upperBoundInclusive='" + upperBoundInclusive + '\'' +
                '}';
    }
}
