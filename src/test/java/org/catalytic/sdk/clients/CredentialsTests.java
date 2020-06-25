package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.CredentialsPage;
import org.catalytic.sdk.exceptions.CredentialsNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.AuthenticationApi;
import org.catalytic.sdk.generated.api.UserCredentialsApi;
import org.catalytic.sdk.generated.model.CredentialType;
import org.catalytic.sdk.generated.model.CredentialsCreationRequest;
import org.catalytic.sdk.generated.model.CredentialsCreationWithEmailAndPasswordRequest;
import org.catalytic.sdk.search.Where;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CredentialsTests {
    UserCredentialsApi credentialsApi;
    AuthenticationApi authenticationApi;

    @Before
    public void before() {
        credentialsApi = mock(UserCredentialsApi.class);
        authenticationApi = mock(AuthenticationApi.class);
    }

    @Test
    public void getCredentials_itShouldGetCredentials() throws Exception {
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        when(credentialsApi.getCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getCredentials_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(credentialsApi.getCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = CredentialsNotFoundException.class)
    public void getCredentials_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(credentialsApi.getCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void getCredentials_itShouldThrowInternalErrorException() throws Exception {
        when(credentialsApi.getCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.get("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = UnauthorizedException.class)
    public void findCredentials_itShouldReturnUnauthorizedException() throws Exception {
        when(credentialsApi.findCredentials(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findCredentials_itShouldReturnInternalErrorException() throws Exception {
        when(credentialsApi.findCredentials(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.find();
    }

    @Test
    public void findCredentials_itShouldReturnAllCredentials() throws Exception {
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        org.catalytic.sdk.generated.model.CredentialsPage credentialsPage = new org.catalytic.sdk.generated.model.CredentialsPage();
        credentialsPage.setCredentials(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findCredentials(null, null, null, null, null, null, null, null, null))
                .thenReturn(credentialsPage);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        CredentialsPage results = credentialsClient.find();
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCredentials().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void findCredentials_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        org.catalytic.sdk.generated.model.CredentialsPage credentialsPage = new org.catalytic.sdk.generated.model.CredentialsPage();
        credentialsPage.setCredentials(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findCredentials(null, null, null, null, null, null, null, "25", null))
                .thenReturn(credentialsPage);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        CredentialsPage results = credentialsClient.find("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCredentials().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void findCredentials_itShouldFindUserByEmail() throws Exception {
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        org.catalytic.sdk.generated.model.CredentialsPage credentialsPage = new org.catalytic.sdk.generated.model.CredentialsPage();
        credentialsPage.setCredentials(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findCredentials("alice@example.com", null, null, null, null, null, null, null, null))
                .thenReturn(credentialsPage);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        Where where = new Where();
        CredentialsPage results = credentialsClient.find(where.text().is("alice@example.com"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCredentials().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void findCredentials_itShouldFindUserByEmailAndPage() throws Exception {
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        org.catalytic.sdk.generated.model.CredentialsPage credentialsPage = new org.catalytic.sdk.generated.model.CredentialsPage();
        credentialsPage.setCredentials(Arrays.asList(creds));
        credentialsPage.setCount(Arrays.asList(creds).size());
        credentialsPage.setNextPageToken(null);
        when(credentialsApi.findCredentials("alice@example.com", null, null, null, null, null, null, "25", null))
                .thenReturn(credentialsPage);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        Where where = new Where();
        CredentialsPage results = credentialsClient.find(where.text().is("alice@example.com"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCredentials().get(0).getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void revokeCredentials_itShouldRevokeCredentials() throws Exception {
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        when(credentialsApi.revokeCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test(expected = UnauthorizedException.class)
    public void revokeCredentials_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(credentialsApi.revokeCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(401, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = CredentialsNotFoundException.class)
    public void revokeCredentials_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(credentialsApi.revokeCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(404, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = InternalErrorException.class)
    public void revokeCredentials_itShouldThrowInternalErrorException() throws Exception {
        when(credentialsApi.revokeCredentials("114c0d7d-c291-4ad2-a10d-68c5dd532af3")).thenThrow(new ApiException(500, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.revoke("114c0d7d-c291-4ad2-a10d-68c5dd532af3");
    }

    @Test(expected = UnauthorizedException.class)
    public void createCredentials_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(authenticationApi.createAndApproveCredentials(request)).thenThrow(new ApiException(401, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.create("example", "alice@example.com", "mypassword");
    }

    @Test(expected = InternalErrorException.class)
    public void createCredentials_itShouldThrowInternalErrorException() throws Exception {
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        when(authenticationApi.createAndApproveCredentials(request)).thenThrow(new ApiException(500, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.create("example", "alice@example.com", "mypassword");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCredentials_itShouldThrowIllegalArgumentException() throws Exception {
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        when(authenticationApi.createAndApproveCredentials(request)).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.create("!@#$", "alice@example.com", "mypassword");
    }

    @Test
    public void createCredentials_itShouldCreateCredentials() throws Exception {
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        when(authenticationApi.createAndApproveCredentials(request)).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.create("example", "alice@example.com", "mypassword");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void createCredentials_itShouldCreateCredentialsWithDomain() throws Exception {
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        when(authenticationApi.createAndApproveCredentials(request)).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.create("example.pushbot.com", "alice@example.com", "mypassword");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void createCredentials_itShouldCreateCredentialsWithName() throws Exception {
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain("example.pushbot.com");
        request.setEmail("alice@example.com");
        request.setPassword("mypassword");
        request.setName("My Credentials");
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        creds.setName("My Credentials");
        when(authenticationApi.createAndApproveCredentials(request)).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.create("example", "alice@example.com", "mypassword", "My Credentials");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(credentials.getName()).isEqualTo("My Credentials");
    }

    @Test(expected = UnauthorizedException.class)
    public void createCredentialsWithWebApprovalFlow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        CredentialsCreationRequest request = new CredentialsCreationRequest();
        request.setDomain("example.pushbot.com");
        when(authenticationApi.createCredentials(request)).thenThrow(new ApiException(401, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.createWithWebApprovalFlow("example");
    }

    @Test(expected = InternalErrorException.class)
    public void createCredentialsWithWebApprovalFlow_itShouldThrowInternalErrorException() throws Exception {
        CredentialsCreationRequest request = new CredentialsCreationRequest();
        request.setDomain("example.pushbot.com");
        when(authenticationApi.createCredentials(request)).thenThrow(new ApiException(500, null));

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        credentialsClient.createWithWebApprovalFlow("example");
    }

    @Test
    public void createCredentialsWithWebApprovalFlow_itShouldCreateCredentials() throws Exception {
        CredentialsCreationRequest request = new CredentialsCreationRequest();
        request.setDomain("example.pushbot.com");
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        when(authenticationApi.createCredentials(request)).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.createWithWebApprovalFlow("example");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
    }

    @Test
    public void createCredentialsWithWebApprovalFlow_itShouldCreateCredentialsWithName() throws Exception {
        CredentialsCreationRequest request = new CredentialsCreationRequest();
        request.setDomain("example.pushbot.com");
        request.setName("My Credentials");
        org.catalytic.sdk.generated.model.Credentials creds = new org.catalytic.sdk.generated.model.Credentials();
        creds.setId(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        creds.setType(CredentialType.USER);
        creds.setName("My Credentials");
        when(authenticationApi.createCredentials(request)).thenReturn(creds);

        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);
        org.catalytic.sdk.entities.Credentials credentials = credentialsClient.createWithWebApprovalFlow("example", "My Credentials");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getId()).isEqualTo(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"));
        assertThat(credentials.getName()).isEqualTo("My Credentials");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrl() throws InternalErrorException {
        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);

        org.catalytic.sdk.entities.Credentials credentials = new org.catalytic.sdk.entities.Credentials(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, null, null, null, null, null);
        String url = credentialsClient.getApprovalUrl(credentials);
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Catalytic+SDK");
    }

    @Test
    public void getApprovalUrl_itShouldReturnApprovalUrlWithName() throws InternalErrorException {
        Credentials credentialsClient = new Credentials(credentialsApi, authenticationApi);

        org.catalytic.sdk.entities.Credentials credentials = new org.catalytic.sdk.entities.Credentials(UUID.fromString("114c0d7d-c291-4ad2-a10d-68c5dd532af3"), "example.com", null, "Example App", null, null, null, null);
        String url = credentialsClient.getApprovalUrl(credentials, "Example App");
        assertThat(url).isEqualTo("https://example.com/credentials/approve?userTokenID=114c0d7d-c291-4ad2-a10d-68c5dd532af3&application=Example+App");
    }
}
