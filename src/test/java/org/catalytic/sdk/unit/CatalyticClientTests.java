package org.catalytic.sdk.unit;

import org.catalytic.sdk.CatalyticClient;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CatalyticClientTests {

    @Test
    // NOTE: THIS TEST WILL FAIL LOCALLY IF YOU HAVE AN ACCESS TOKEN IN ~/.catalytic/tokens/default
    public void itShouldHaveNullAccessToken() throws Exception {
        CatalyticClient catalytic = new CatalyticClient();
        assertThat(catalytic.getAccessToken()).isNull();
    }

    @Test
    public void itShouldHaveAnAccessToken() throws Exception {
        CatalyticClient catalytic = new CatalyticClient("1234");
        assertThat(catalytic.getAccessToken()).isEqualTo("1234");
    }
}
