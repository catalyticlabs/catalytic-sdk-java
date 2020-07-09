package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.AccessToken;
import org.catalytic.sdk.entities.AccessTokensPage;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.AccessTokensApi;
import org.catalytic.sdk.generated.api.AuthenticationApi;
import org.catalytic.sdk.generated.model.AccessTokenCreationRequest;
import org.catalytic.sdk.generated.model.AccessTokenCreationWithEmailAndPasswordRequest;
import org.catalytic.sdk.generated.model.TokenType;
import org.catalytic.sdk.generated.model.WaitForAccessTokenApprovalRequest;
import org.catalytic.sdk.search.Where;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccessTokensTests {
    AccessTokensApi credentialsApi;
    AuthenticationApi authenticationApi;

    @Before
    public void before() {
        credentialsApi = mock(AccessTokensApi.class);
        authenticationApi = mock(AuthenticationApi.class);
    }

    @Test
    public void getAccessTokens_itShouldGetAccessTokens() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getAccessTokens_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void getAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        when(credentialsApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = UnauthorizedException.class)
    public void findAccessTokens_itShouldReturnUnauthorizedException() throws Exception {
        when(credentialsApi.findAccessTokens(null, null, null, null, null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findAccessTokens_itShouldReturnInternalErrorException() throws Exception {
        when(credentialsApi.findAccessTokens(null, null, null, null, null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.find();
    }

    @Test
    public void findAccessTokens_itShouldReturnAllAccessTokens() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        org.catalytic.sdk.generated.model.AccessTokensPage credentialsPage = new org.catalytic.sdk.generated.model.AccessTokensPage();
        credentialsPage.setAccessTokens(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findAccessTokens(null, null, null, null, null, null, null, null, null, null, null, null, null))
                .thenReturn(credentialsPage);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessTokensPage results = accessTokensClient.find();
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getAccessTokens().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void findAccessTokens_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        org.catalytic.sdk.generated.model.AccessTokensPage credentialsPage = new org.catalytic.sdk.generated.model.AccessTokensPage();
        credentialsPage.setAccessTokens(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findAccessTokens(null, null, null, null, null, null, null, null, null, null, null, "25", null))
                .thenReturn(credentialsPage);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessTokensPage results = accessTokensClient.find("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getAccessTokens().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void findAccessTokens_itShouldFindUserByEmail() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        org.catalytic.sdk.generated.model.AccessTokensPage credentialsPage = new org.catalytic.sdk.generated.model.AccessTokensPage();
        credentialsPage.setAccessTokens(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findAccessTokens("alice@example.com", null, null, null, null, null, null, null, null, null, null, null, null))
                .thenReturn(credentialsPage);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        Where where = new Where();
        AccessTokensPage results = accessTokensClient.find(where.text().is("alice@example.com"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getAccessTokens().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void findAccessTokens_itShouldFindUserByEmailAndPage() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        org.catalytic.sdk.generated.model.AccessTokensPage credentialsPage = new org.catalytic.sdk.generated.model.AccessTokensPage();
        credentialsPage.setAccessTokens(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findAccessTokens("alice@example.com", null, null, null, null, null, null, null, null, null, null, "25", null))
                .thenReturn(credentialsPage);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        Where where = new Where();
        AccessTokensPage results = accessTokensClient.find(where.text().is("alice@example.com"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getAccessTokens().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void revokeAccessTokens_itShouldRevokeAccessTokens() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = UnauthorizedException.class)
    public void revokeAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void revokeAccessTokens_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void revokeAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        when(credentialsApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = UnauthorizedException.class)
    public void createAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(authenticationApi.createAndApproveAccessToken(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.create("example", "alice@example.com", "mypassword");
    }

    @Test(expected = InternalErrorException.class)
    public void createAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(authenticationApi.createAndApproveAccessToken(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
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
        when(authenticationApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
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
        when(authenticationApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
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
        when(authenticationApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
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
        when(authenticationApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = accessTokensClient.create("example", "alice@example.com", "mypassword", "My AccessTokens");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(accessToken.getName()).isEqualTo("My AccessTokens");
    }

    @Test(expected = UnauthorizedException.class)
    public void createAccessTokensWithWebApprovalFlow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        when(authenticationApi.createAccessToken(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.createWithWebApprovalFlow("example");
    }

    @Test(expected = InternalErrorException.class)
    public void createAccessTokensWithWebApprovalFlow_itShouldThrowInternalErrorException() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        when(authenticationApi.createAccessToken(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        accessTokensClient.createWithWebApprovalFlow("example");
    }

    @Test
    public void createAccessTokensWithWebApprovalFlow_itShouldCreateAccessTokens() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(authenticationApi.createAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
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
        when(authenticationApi.createAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = accessTokensClient.createWithWebApprovalFlow("example", "My AccessTokens");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(accessToken.getName()).isEqualTo("My AccessTokens");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrl() throws InternalErrorException {
        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);

        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, null, null, null, null, null);
        String url = accessTokensClient.getApprovalUrl(accessToken);
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Catalytic+SDK");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrlWithName() throws InternalErrorException {
        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);

        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, "Example App", null, null, null, null);
        String url = accessTokensClient.getApprovalUrl(accessToken, "Example App");
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Example+App");
    }

    @Test(expected = UnauthorizedException.class)
    public void waitForApproval_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(authenticationApi.waitForAccessTokenApproval(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }

    @Test(expected = InternalErrorException.class)
    public void waitForApproval_itShouldThrowInternalErrorException() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(authenticationApi.waitForAccessTokenApproval(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }

    @Test
    public void waitForApproval_itShouldWaitForApproval() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(authenticationApi.waitForAccessTokenApproval(request)).thenReturn(null);

        AccessTokens accessTokensClient = new AccessTokens(credentialsApi, authenticationApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }
}
