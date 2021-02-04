package org.catalytic.sdk.search;

import org.catalytic.sdk.entities.InstanceStatus;

/**
 * An expression object for matching InstanceStatus's
 */
public class InstanceStatusSearchExpression {

    private InstanceStatus isEqualTo;

    public InstanceStatus getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(InstanceStatus isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    @Override
    public String toString() {
        return "InstanceStatusSearchExpression{" +
                "isEqualTo=" + isEqualTo +
                '}';
    }
}
