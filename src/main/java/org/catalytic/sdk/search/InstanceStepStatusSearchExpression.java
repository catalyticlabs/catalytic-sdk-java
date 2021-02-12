package org.catalytic.sdk.search;

import org.catalytic.sdk.entities.InstanceStepStatus;

/**
 * An expression object for matching InstanceStepStatus's
 */
public class InstanceStepStatusSearchExpression {

    private InstanceStepStatus isEqualTo;

    public InstanceStepStatus getIsEqualTo() {
        return isEqualTo;
    }

    public void setIsEqualTo(InstanceStepStatus isEqualTo) {
        this.isEqualTo = isEqualTo;
    }

    @Override
    public String toString() {
        return "InstanceStepStatusSearchExpression{" +
                "isEqualTo=" + isEqualTo +
                '}';
    }
}
