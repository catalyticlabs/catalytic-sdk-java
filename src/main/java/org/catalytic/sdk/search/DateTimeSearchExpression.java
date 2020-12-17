package org.catalytic.sdk.search;

import java.time.OffsetDateTime;

/**
 * An expression object for matching OffsetDateTime objects
 */
public class DateTimeSearchExpression {
    private OffsetDateTime isEqualTo;
    private OffsetDateTime contains;
    private DateTimeOffsetRange between;

    public OffsetDateTime getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(OffsetDateTime isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    public OffsetDateTime getContains() {
        return contains;
    }

    public void setContains(OffsetDateTime contains) {
        this.contains = contains;
    }

    public DateTimeOffsetRange getBetween() {
        return between;
    }

    public void setBetween(DateTimeOffsetRange between) {
        this.between = between;
    }

    @Override
    public String toString() {
        return "DateTimeSearchExpression{" +
                "isEqualTo=" + isEqualTo +
                ", contains=" + contains +
                ", between=" + between +
                '}';
    }
}
