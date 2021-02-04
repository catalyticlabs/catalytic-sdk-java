package org.catalytic.sdk.search;

/**
 * An expression object for matching exact strings
 */
public class ExactStringSearchExpression {
    private String isEqualTo;

    public String getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(String isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    @Override
    public String toString() {
        return "ExactStringSearchExpression{" +
                "isEqualTo='" + isEqualTo + '\'' +
                '}';
    }
}
