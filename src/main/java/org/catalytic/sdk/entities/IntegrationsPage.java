package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of Integrations
 */
public class IntegrationsPage extends Page {

    private List<Integration> integrations;

    public IntegrationsPage(List<Integration> integrations, int count) {
        this.integrations = integrations;
        this.count = count;
    }

    public IntegrationsPage(List<Integration> integrations, int count, String nextPageToken) {
        this.integrations = integrations;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<Integration> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(List<Integration> integrations) {
        this.integrations = integrations;
    }

    @Override
    public String toString() {
        return "IntegrationsPage{" +
                "integrations=" + integrations +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}
