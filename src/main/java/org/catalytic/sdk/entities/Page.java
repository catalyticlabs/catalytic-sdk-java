package org.catalytic.sdk.entities;

/**
 * A class meant to be extended for paged objects
 */
public abstract class Page {

    int count;
    String nextPageToken;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}