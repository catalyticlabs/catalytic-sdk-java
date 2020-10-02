package org.catalytic.sdk.entities;

/**
 * An Integration Connection object
 */
public class IntegrationConnection {

    private String id;
    private String name;
    private String integrationId;

    public IntegrationConnection(String id, String name, String integrationId) {
        this.id = id;
        this.name = name;
        this.integrationId = integrationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    @Override
    public String toString() {
        return "IntegrationConnection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", integrationId=" + integrationId +
                '}';
    }
}
