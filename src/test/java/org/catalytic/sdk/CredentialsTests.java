package org.catalytic.sdk;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class CredentialsTests {

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Test
    public void itShouldFetchTokenFromEnvVar() throws Exception {
        environmentVariables.set("CATALYTIC_CREDENTIALS", "foobar");
        Credentials credentials = new Credentials();
        credentials.fetchToken(null);
    }
}
