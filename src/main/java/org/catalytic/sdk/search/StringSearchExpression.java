package org.catalytic.sdk.search;

/**
 * An expression object for matching strings
 */
public class StringSearchExpression {
    private String isEqualTo;
    private String contains;
    private StringRange between;

    public String getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(String isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public StringRange getBetween() {
        return between;
    }

    public void setBetween(StringRange between) {
        this.between = between;
    }

    @Override
    public String toString() {
        return "StringSearchExpression{" +
                "isEqualTo='" + isEqualTo + '\'' +
                ", contains='" + contains + '\'' +
                ", between=" + between +
                '}';
    }
}
