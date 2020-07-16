package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of instance steps
 */
public class InstanceStepsPage extends Page {

    private List<InstanceStep> instanceSteps;

    public InstanceStepsPage(List<InstanceStep> instanceSteps, int count, String nextPageToken) {
        this.instanceSteps = instanceSteps;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<InstanceStep> getInstanceSteps() {
        return instanceSteps;
    }

    public void setInstanceSteps(List<InstanceStep> instanceSteps) {
        this.instanceSteps = instanceSteps;
    }

    @Override
    public String toString() {
        return "InstanceStepsPage{" +
                "instanceSteps=" + instanceSteps +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}
