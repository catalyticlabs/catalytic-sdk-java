package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of actions
 */
public class ActionsPage extends Page {

    private List<Action> actions;

    public ActionsPage(List<Action> actions, int count, String nextPageToken) {
        this.actions = actions;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    /**
     * Add a list of actions.
     *
     * Note that this is different than setting a list of actions.
     *
     * @param actions        The actions to add
     * @param nextPageToken The next page token
     */
    public void addActions(List<Action> actions, String nextPageToken) {
        this.actions.addAll(actions);
        this.count = this.count + actions.size();
        this.nextPageToken = nextPageToken;
    }

    @Override
    public String toString() {
        return "ActionsPage{" +
                "actions=" + actions +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}