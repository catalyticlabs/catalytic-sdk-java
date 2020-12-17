package org.catalytic.sdk.search;

/**
 * An expression object for matching booleans
 */
public class BooleanSearchExpression {
    private Boolean isEqualTo;

    public Boolean getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(Boolean isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    @Override
    public String toString() {
        return "BooleanSearchExpression{" +
                "isEqualTo=" + isEqualTo +
                '}';
    }
}
