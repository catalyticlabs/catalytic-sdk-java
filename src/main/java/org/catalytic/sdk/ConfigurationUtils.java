package org.catalytic.sdk;

import org.catalytic.sdk.generated.ApiClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A convenience class so that we only create a singleton of ApiClient
 */
public class ConfigurationUtils {

    private static ApiClient apiClient;

    /**
     * Creates a Configuration if one doesn't exist and returns it
     *
     * @param token The secret to set on the configuration
     * @return      The Configuration object
     */
    public static ApiClient getApiClient(String token) {
        if (apiClient == null) {
            apiClient = new ApiClient();
            apiClient.setUserAgent("Catalytic Java SDK/" + getVersion());

            if (token != null) {
                apiClient.setBearerToken(token.trim());
            }
        }

        return apiClient;
    }

    /**
     * Gets the current sdk version from the generated file "version"
     *
     * @return  The version of the sdk
     */
    private static String getVersion() {
        Path path = Paths.get("src/main/java/org/catalytic/sdk/version");
        String version = null;
        try {
            version = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            // The project should never build without if this happens so therefore just print a stacktrace
            e.printStackTrace();
        }

        return version;
    }
}
