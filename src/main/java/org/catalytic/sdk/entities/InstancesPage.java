package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of instances
 */
public class InstancesPage extends Page {

    private List<Instance> instances;

    public InstancesPage(List<Instance> instances, int count) {
        this.instances = instances;
        this.count = count;
    }

    public InstancesPage(List<Instance> instances, int count, String nextPageToken) {
        this.instances = instances;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    @Override
    public String toString() {
        return "InstancesPage{" +
                "instances=" + instances +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}