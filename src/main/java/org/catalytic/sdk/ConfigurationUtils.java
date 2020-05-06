package org.catalytic.sdk;

import org.catalytic.sdk.generated.ApiClient;

/**
 * A convenience class so that we only create a singleton of ApiClient
 */
public class ConfigurationUtils {

    private static ApiClient apiClient;

    /**
     * Creates a Configuration if one doesn't exist and returns it
     *
     * @param secret    The secret to set on the configuration
     * @return          The Configuration object
     */
    public static ApiClient getApiClient(String secret) {
        if (apiClient == null) {
            apiClient = new ApiClient();
            // TODO: Dynamically fetch the version from build.gradle
            apiClient.setUserAgent("Catalytic Java SDK/0.0.1-SNAPSHOT");
            apiClient.setBearerToken(secret.trim());
        }

        return apiClient;
    }
}
