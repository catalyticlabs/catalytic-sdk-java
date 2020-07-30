package org.catalytic.sdk;

import org.catalytic.sdk.generated.ApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A convenience class so that we only create a singleton of ApiClient
 */
public class ConfigurationUtils {

    /**
     * Creates a Configuration if one doesn't exist and returns it
     *
     * @param token     The secret to set on the configuration
     * @return          The Configuration object
     */
    public static ApiClient getApiClient(String token) {
        ApiClient apiClient = new ApiClient();
        apiClient.setUserAgent("catalytic-sdk-java/" + getVersion());

        if (token != null) {
            apiClient.setBearerToken(token.trim());
        }

        return apiClient;
    }

    /**
     * Gets the current sdk version from the generated file "version"
     *
     * @return  The version of the sdk
     */
    private static String getVersion() {
        String version = null;

        try (InputStream in = ConfigurationUtils.class.getClassLoader().getResourceAsStream("version");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            version = reader.readLine();
        } catch (IOException e) {
            // The project should never build without if this happens so therefore just print a stacktrace
            e.printStackTrace();
        }

        return version;
    }
}
