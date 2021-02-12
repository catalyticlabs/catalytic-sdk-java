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

    /**
     * Add a list of InstanceSteps.
     *
     * Note that this is different than setting a list of InstanceSteps.
     *
     * @param instanceSteps The InstanceSteps to add
     * @param nextPageToken The next page token
     */
    public void addInstanceSteps(List<InstanceStep> instanceSteps, String nextPageToken) {
        this.instanceSteps.addAll(instanceSteps);
        this.count = this.count + instanceSteps.size();
        this.nextPageToken = nextPageToken;
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
