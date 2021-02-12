package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.AccessToken;
import org.catalytic.sdk.entities.AccessTokensPage;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccessTokensTests {
    AccessTokensApi accessTokensApi;

    @Before
    public void before() {
        accessTokensApi = mock(AccessTokensApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void list_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        AccessTokens accessTokensClient = new AccessTokens(null);
        accessTokensClient.list();
    }

    @Test(expected = UnauthorizedException.class)
    public void list_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(accessTokensApi.listAccessTokens(null, null)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.list();
    }

    @Test(expected = InternalErrorException.class)
    public void list_itShouldThrowInternalErrorException() throws Exception {
        when(accessTokensApi.listAccessTokens(null, null)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.list();
    }

    @Test
    public void list_itShouldListAccessTokensWithOnePage() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken internalAccessToken = new org.catalytic.sdk.generated.model.AccessToken();
        internalAccessToken.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        internalAccessToken.setType(TokenType.USER);

        org.catalytic.sdk.generated.model.AccessTokensPage internalAccessTokensPage = new org.catalytic.sdk.generated.model.AccessTokensPage();
        List<org.catalytic.sdk.generated.model.AccessToken> accessTokens = Arrays.asList(internalAccessToken);

        internalAccessTokensPage.setAccessTokens(accessTokens);
        internalAccessTokensPage.setCount(accessTokens.size());
        internalAccessTokensPage.setNextPageToken("");

        when(accessTokensApi.listAccessTokens(null, null))
                .thenReturn(internalAccessTokensPage);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessTokensPage accessTokensPage = accessTokensClient.list();
        assertThat(accessTokensPage).isNotNull();
        assertThat(accessTokensPage.getAccessTokens()).isNotEmpty();
        assertThat(accessTokensPage.getAccessTokens().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void list_itShouldListAccessTokensWithMoreThanOnePage() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken accessTokenOne = new org.catalytic.sdk.generated.model.AccessToken();
        accessTokenOne.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        accessTokenOne.setType(TokenType.USER);
        List<org.catalytic.sdk.generated.model.AccessToken> accessTokenOneArray = Arrays.asList(accessTokenOne);

        org.catalytic.sdk.generated.model.AccessToken accessTokenTwo = new org.catalytic.sdk.generated.model.AccessToken();
        accessTokenTwo.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        accessTokenTwo.setType(TokenType.USER);
        List<org.catalytic.sdk.generated.model.AccessToken> accessTokenTwoArray = Arrays.asList(accessTokenTwo);

        org.catalytic.sdk.generated.model.AccessTokensPage pageOne = new org.catalytic.sdk.generated.model.AccessTokensPage();
        pageOne.setAccessTokens(accessTokenOneArray);
        pageOne.setCount(accessTokenOneArray.size());
        pageOne.setNextPageToken("123abc");

        org.catalytic.sdk.generated.model.AccessTokensPage pageTwo = new org.catalytic.sdk.generated.model.AccessTokensPage();
        pageTwo.setAccessTokens(accessTokenTwoArray);
        pageTwo.setCount(accessTokenTwoArray.size());
        pageTwo.setNextPageToken("");

        when(accessTokensApi.listAccessTokens(null, null))
                .thenReturn(pageOne);

        when(accessTokensApi.listAccessTokens("123abc", null))
                .thenReturn(pageTwo);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessTokensPage accessTokensPage = accessTokensClient.list();
        assertThat(accessTokensPage).isNotNull();
        assertThat(accessTokensPage.getAccessTokens()).isNotEmpty();
        assertThat(accessTokensPage.getCount()).isEqualTo(2);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getAccessToken_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        AccessTokens accessTokensClient = new AccessTokens(null);
        accessTokensClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getAccessToken_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(accessTokensApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getAccessToken_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(accessTokensApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void getAccessToken_itShouldThrowInternalErrorException() throws Exception {
        when(accessTokensApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test
    public void getAccessToken_itShouldGetAccessTokens() throws Exception {
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(accessTokensApi.getAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
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
        when(accessTokensApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessToken accessToken = accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = UnauthorizedException.class)
    public void revokeAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(accessTokensApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void revokeAccessTokens_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(accessTokensApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void revokeAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        when(accessTokensApi.revokeAccessToken("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = UnauthorizedException.class)
    public void createAccessTokens_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(accessTokensApi.createAndApproveAccessToken(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.create("example", "alice@example.com", "mypassword");
    }

    @Test(expected = InternalErrorException.class)
    public void createAccessTokens_itShouldThrowInternalErrorException() throws Exception {
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(accessTokensApi.createAndApproveAccessToken(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
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
        when(accessTokensApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
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
        when(accessTokensApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
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
        when(accessTokensApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
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
        when(accessTokensApi.createAndApproveAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessToken accessToken = accessTokensClient.create("example", "alice@example.com", "mypassword", "My AccessTokens");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(accessToken.getName()).isEqualTo("My AccessTokens");
    }

    @Test(expected = UnauthorizedException.class)
    public void createAccessTokensWithWebApprovalFlow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        when(accessTokensApi.createAccessToken(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.createWithWebApprovalFlow("example");
    }

    @Test(expected = InternalErrorException.class)
    public void createAccessTokensWithWebApprovalFlow_itShouldThrowInternalErrorException() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        when(accessTokensApi.createAccessToken(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        accessTokensClient.createWithWebApprovalFlow("example");
    }

    @Test
    public void createAccessTokensWithWebApprovalFlow_itShouldCreateAccessTokens() throws Exception {
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain("example.pushbot.com");
        org.catalytic.sdk.generated.model.AccessToken creds = new org.catalytic.sdk.generated.model.AccessToken();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(TokenType.USER);
        when(accessTokensApi.createAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
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
        when(accessTokensApi.createAccessToken(request)).thenReturn(creds);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessToken accessToken = accessTokensClient.createWithWebApprovalFlow("example", "My AccessTokens");
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(accessToken.getName()).isEqualTo("My AccessTokens");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrl() throws InternalErrorException {
        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);

        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, null, null, null, null, null);
        String url = accessTokensClient.getApprovalUrl(accessToken);
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Catalytic+SDK");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrlWithName() throws InternalErrorException {
        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);

        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, "Example App", null, null, null, null);
        String url = accessTokensClient.getApprovalUrl(accessToken, "Example App");
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Example+App");
    }

    @Test(expected = UnauthorizedException.class)
    public void waitForApproval_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(accessTokensApi.waitForAccessTokenApproval(request)).thenThrow(new ApiException(401, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }

    @Test(expected = InternalErrorException.class)
    public void waitForApproval_itShouldThrowInternalErrorException() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(accessTokensApi.waitForAccessTokenApproval(request)).thenThrow(new ApiException(500, null));

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }

    @Test
    public void waitForApproval_itShouldWaitForApproval() throws Exception {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken("1234");
        request.setWaitTimeMillis(100);
        when(accessTokensApi.waitForAccessTokenApproval(request)).thenReturn(null);

        AccessTokens accessTokensClient = new AccessTokens("1234", accessTokensApi);
        AccessToken accessToken = new AccessToken(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example", "user", "my token", "1234", "my-secret", "prod", "alice");
        accessTokensClient.waitForApproval(accessToken, 100);
    }
}
