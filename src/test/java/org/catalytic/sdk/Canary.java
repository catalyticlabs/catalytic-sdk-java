package org.catalytic.sdk;

import org.catalytic.sdk.entities.Pushbot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Canary {

    @Test
    public void createPushbot () {
        Pushbot pushbot = new Pushbot();
        pushbot.setName("My pushbot");

        assertEquals(pushbot.getName(), "My pushbot");
    }
}