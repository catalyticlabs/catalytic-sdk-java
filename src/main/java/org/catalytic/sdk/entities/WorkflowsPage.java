package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of workflows
 */
public class WorkflowsPage extends Page {

    private List<Workflow> workflows;

    public WorkflowsPage(List<Workflow> workflows, int count) {
        this.workflows = workflows;
        this.count = count;
    }

    public WorkflowsPage(List<Workflow> workflows, int count, String nextPageToken) {
        this.workflows = workflows;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<Workflow> workflows) {
        this.workflows = workflows;
    }

    @Override
    public String toString() {
        return "WorkflowsPage{" +
                "workflows=" + workflows +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}