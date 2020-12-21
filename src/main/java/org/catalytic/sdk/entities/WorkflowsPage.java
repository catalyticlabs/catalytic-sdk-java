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

    /**
     * Add a list of Workflows.
     *
     * Note that this is different than setting a list of Workflows.
     *
     * @param workflows     The workflows to add
     * @param nextPageToken The next page token
     */
    public void addWorkflows(List<Workflow> workflows, String nextPageToken) {
        this.workflows.addAll(workflows);
        this.count = this.count + workflows.size();
        this.nextPageToken = nextPageToken;
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