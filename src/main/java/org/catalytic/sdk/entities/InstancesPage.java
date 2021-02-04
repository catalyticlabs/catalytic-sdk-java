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

    /**
     * Add a list of Instances.
     *
     * Note that this is different than setting a list of Instances.
     *
     * @param instances     The Instances to add
     * @param nextPageToken The next page token
     */
    public void addInstances(List<Instance> instances, String nextPageToken) {
        this.instances.addAll(instances);
        this.count = this.count + instances.size();
        this.nextPageToken = nextPageToken;
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