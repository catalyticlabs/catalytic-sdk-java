package org.catalytic.sdk;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class AccessTokensTests {

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Test
    public void itShouldFetchTokenFromEnvVar() throws Exception {
        environmentVariables.set("CATALYTIC_TOKEN", "foobar");
        Credentials credentials = new Credentials();
        credentials.fetchToken(null);
    }
}
