package org.catalytic.sdk;

import org.catalytic.sdk.generated.ApiClient;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ConfigurationUtilsTests {

    @Test
    public void itShouldGetApiClient() {
        ApiClient apiClient = ConfigurationUtils.getApiClient("abcd1234");
        assertThat(apiClient).isNotNull();
    }
}
