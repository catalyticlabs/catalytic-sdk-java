package org.catalytic.sdk.entities;

/**
 * An object which represents a page of users
 */
public class UsersPage extends Page {

    private User[] users;

    public UsersPage(User[] users, int count, String nextPageToken) {
        this.users = users;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}