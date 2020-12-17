package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of users
 */
public class UsersPage extends Page {

    private List<User> users;

    public UsersPage(List<User> users, int count, String nextPageToken) {
        this.users = users;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Add a list of users.
     *
     * Note that this is different than setting a list of users.
     *
     * @param users         The users to add
     * @param nextPageToken The next page token
     */
    public void addUsers(List<User> users, String nextPageToken) {
        this.users.addAll(users);
        this.count = this.count + users.size();
        this.nextPageToken = nextPageToken;
    }

    @Override
    public String toString() {
        return "UsersPage{" +
                "users=" + users +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}