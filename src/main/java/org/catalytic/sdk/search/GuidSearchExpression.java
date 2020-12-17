package org.catalytic.sdk.search;

import java.util.UUID;

/**
 * An expression object for matching UUID's
 */
public class GuidSearchExpression {
    private UUID isEqualTo;

    public UUID getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(UUID isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    @Override
    public String toString() {
        return "GuidSearchExpression{" +
                "isEqualTo=" + isEqualTo +
                '}';
    }
}
