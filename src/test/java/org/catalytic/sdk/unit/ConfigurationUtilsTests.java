package org.catalytic.sdk.unit;

import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.generated.ApiClient;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ConfigurationUtilsTests {

    @Test
    public void itShouldGetAnApiClient() {
        ApiClient apiClient = ConfigurationUtils.getApiClient("foobar");
        assertThat(apiClient).isNotNull();
    }
}
