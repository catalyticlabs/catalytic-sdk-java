package org.catalytic.sdk.entities;

/**
 * An object which represents a page of instances
 */
public class InstancesPage extends Page {

    private Instance[] instances;

    public InstancesPage(Instance[] instances, int count) {
        this.instances = instances;
        this.count = count;
    }

    public InstancesPage(Instance[] instances, int count, String nextPageToken) {
        this.instances = instances;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public Instance[] getInstances() {
        return instances;
    }

    public void setInstances(Instance[] instances) {
        this.instances = instances;
    }
}