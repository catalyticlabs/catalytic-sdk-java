package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An Integration object
 */
public class Integration {

    private String id;
    private String name;
    private Boolean isCustomIntegration;
    private List<IntegrationConnection> connections;
    private List<Field> connectionParams;

    public Integration(String id, String name, Boolean isCustomIntegration, List<IntegrationConnection> connections, List<Field> connectionParams) {
        this.id = id;
        this.name = name;
        this.isCustomIntegration = isCustomIntegration;
        this.connections = connections;
        this.connectionParams = connectionParams;
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

    public Boolean getCustomIntegration() {
        return isCustomIntegration;
    }

    public void setCustomIntegration(Boolean customIntegration) {
        isCustomIntegration = customIntegration;
    }

    public List<IntegrationConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<IntegrationConnection> connections) {
        this.connections = connections;
    }

    public List<Field> getConnectionParams() {
        return connectionParams;
    }

    public void setConnectionParams(List<Field> connectionParams) {
        this.connectionParams = connectionParams;
    }

    @Override
    public String toString() {
        return "Integration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCustomIntegration=" + isCustomIntegration +
                ", connections=" + connections +
                ", connectionParams=" + connectionParams +
                '}';
    }
}
