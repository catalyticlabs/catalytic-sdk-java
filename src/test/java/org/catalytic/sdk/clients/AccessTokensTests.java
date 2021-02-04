package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.AccessToken;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.AccessTokensApi;
import org.catalytic.sdk.generated.model.AccessTokenCreationRequest;
import org.catalytic.sdk.generated.model.AccessTokenCreationWithEmailAndPasswordRequest;
import org.catalytic.sdk.generated.model.TokenType;
import org.catalytic.sdk.generated.model.WaitForAccessTokenApprovalRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccessTokensTests {
    AccessTokensApi credentialsApi;

    @Before
    public void before() {
        credentialsApi = mock(AccessTokensApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getAccessToken_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        AccessTokens accessTokensClient = new AccessTokens(null);
        accessTokensClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getAccessToken_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getAccessToken_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void getAccessToken_itShouldThrowInternalErrorException() throws Exception {
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test
    public void getAccessToken_itShouldGetAccessTokens() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void revokeAccessToken_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        AccessTokens accessTokensClient = new AccessTokens(null);
        accessTokensClient.revoke("1234");
    }

    @Test
    public void revokeAccessTokens_itShouldRevokeAccessTokens() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = UnauthorizedException.class)
    public void revokeAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void revokeAccessTokens_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void revokeAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = UnauthorizedException.class)
    public void createAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(credentialsApi.createAndApproveAccessToken(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.create("example", "alice@example.com", "mypassword");
    }

    @Test(expected = InternalErrorException.class)
    public void createAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(credentialsApi.createAndApproveAccessToken(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.create("example", "alice@example.com", "mypassword");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAccessTokens_itShouldThrowIllegalArgumentException() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.create("!@#$", "alice@example.com", "mypassword");
    }

    @Test
    public void createAccessTokens_itShouldCreateAccessTokens() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.create("example", "alice@example.com", "mypassword");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void createAccessTokens_itShouldCreateAccessTokensWithDomain() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.create("example.pushbot.com", "alice@example.com", "mypassword");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void createAccessTokens_itShouldCreateAccessTokensWithName() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        request.setName("My AccessTokens");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        creds.setName("My AccessTokens");
        when(credentialsApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.create("example", "alice@example.com", "mypassword", "My AccessTokens");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(accessToken.getName()).isEqualTo("My AccessTokens");
    }

    @Test(expected = UnauthorizedException.class)
    public void createAccessTokensWithWebApprovalFlow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        when(credentialsApi.createAccessToken(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.createWithWebApprovalFlow("example");
    }

    @Test(expected = InternalErrorException.class)
    public void createAccessTokensWithWebApprovalFlow_itShouldThrowInternalErrorException() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        when(credentialsApi.createAccessToken(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        accessTokensClient.createWithWebApprovalFlow("example");
    }

    @Test
    public void createAccessTokensWithWebApprovalFlow_itShouldCreateAccessTokens() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.createAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.createWithWebApprovalFlow("example");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void createAccessTokensWithWebApprovalFlow_itShouldCreateAccessTokensWithName() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        request.setName("My AccessTokens");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        creds.setName("My AccessTokens");
        when(credentialsApi.createAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = accessTokensClient.createWithWebApprovalFlow("example", "My AccessTokens");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(accessToken.getName()).isEqualTo("My AccessTokens");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrl() throws InternalErrorException {
        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);

        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, null, null, null, null, null);
        String url = accessTokensClient.getApprovalUrl(accessToken);
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Catalytic+SDK");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrlWithName() throws InternalErrorException {
        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);

        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, "Example App", null, null, null, null);
        String url = accessTokensClient.getApprovalUrl(accessToken, "Example App");
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Example+App");
    }

    @Test(expected = UnauthorizedException.class)
    public void waitForApproval_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(credentialsApi.waitForAccessTokenApproval(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }

    @Test(expected = InternalErrorException.class)
    public void waitForApproval_itShouldThrowInternalErrorException() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(credentialsApi.waitForAccessTokenApproval(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }

    @Test
    public void waitForApproval_itShouldWaitForApproval() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(credentialsApi.waitForAccessTokenApproval(request)).thenReturn(null);

        AccessTokens accessTokensClient = new AccessTokens("1234", credentialsApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }
}
