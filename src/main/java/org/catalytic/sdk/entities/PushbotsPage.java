package org.catalytic.sdk.entities;

/**
 * An object which represents a page of pushbots
 */
public class PushbotsPage extends Page {

    private Pushbot[] pushbots;

    public PushbotsPage(Pushbot[] pushbots, int count) {
        this.pushbots = pushbots;
        this.count = count;
    }

    public PushbotsPage(Pushbot[] pushbots, int count, String nextPageToken) {
        this.pushbots = pushbots;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public Pushbot[] getPushbots() {
        return pushbots;
    }

    public void setPushbots(Pushbot[] pushbots) {
        this.pushbots = pushbots;
    }
}