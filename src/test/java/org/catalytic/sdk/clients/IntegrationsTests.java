package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.Field;
import org.catalytic.sdk.entities.Integration;
import org.catalytic.sdk.entities.IntegrationConfiguration;
import org.catalytic.sdk.entities.IntegrationConnection;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.IntegrationsApi;
import org.catalytic.sdk.generated.model.*;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationsTests {

    IntegrationsApi integrationsApi;

    @Before
    public void before() {
        integrationsApi = mock(IntegrationsApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getIntegration_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Integrations integrationsClient = new Integrations(null);
        integrationsClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getIntegration_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(integrationsApi.getIntegration("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = IntegrationNotFoundException.class)
    public void getIntegration_itShouldThrowIntegrationNotFoundExceptionIfIntegrationDoesNotExist() throws Exception {
        when(integrationsApi.getIntegration("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(404, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void getIntegration_itShouldThrowInternalErrorException() throws Exception {
        when(integrationsApi.getIntegration("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void getIntegration_itShouldGetAnIntegration() throws Exception {
        org.catalytic.sdk.generated.model.Integration sdkIntegration = new org.catalytic.sdk.generated.model.Integration();
        sdkIntegration.setId("my-integration");
        when(integrationsApi.getIntegration("my-integration")).thenReturn(sdkIntegration);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        Integration integration = integrationsClient.get("my-integration");
        assertThat(integration).isNotNull();
        assertThat(integration.getId()).isEqualTo("my-integration");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void createIntegration_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Integrations integrationsClient = new Integrations(null);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.create("My integration", oauthConfig);
    }

    @Test(expected = UnauthorizedException.class)
    public void createIntegration_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        IntegrationCreationRequest request = new IntegrationCreationRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My integration");
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myId");
        integrationConfiguration.setClientSecret("mySecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);
        request.setConfig(integrationConfiguration);
        when(integrationsApi.createIntegration(request)).thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.create("My integration", oauthConfig);
    }

    @Test(expected = InternalErrorException.class)
    public void createIntegration_itShouldThrowInternalErrorException() throws Exception {
        IntegrationCreationRequest request = new IntegrationCreationRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My integration");
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myId");
        integrationConfiguration.setClientSecret("mySecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);
        request.setConfig(integrationConfiguration);
        when(integrationsApi.createIntegration(request)).thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.create("My integration", oauthConfig);
    }

    @Test
    public void createIntegration_itShouldCreateAnIntegration() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myId");
        integrationConfiguration.setClientSecret("mySecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);

        IntegrationCreationRequest request = new IntegrationCreationRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My integration");
        request.setConfig(integrationConfiguration);

        org.catalytic.sdk.generated.model.Integration sdkIntegration = new org.catalytic.sdk.generated.model.Integration();
        sdkIntegration.setId("My integration");
        sdkIntegration.setId("my-integration");

        when(integrationsApi.createIntegration(request)).thenReturn(sdkIntegration);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        Integration integration = integrationsClient.create("My integration", oauthConfig);

        assertThat(integration).isNotNull();
        assertThat(integration.getId()).isEqualTo("my-integration");
    }
//
    @Test(expected = AccessTokenNotFoundException.class)
    public void updateIntegration_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Integrations integrationsClient = new Integrations(null);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.update("1234", "My integration", oauthConfig);
    }

    @Test(expected = UnauthorizedException.class)
    public void updateIntegration_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myId");
        integrationConfiguration.setClientSecret("mySecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);

        IntegrationUpdateRequest request = new IntegrationUpdateRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My integration");
        request.setConfig(integrationConfiguration);

        when(integrationsApi.updateIntegration("1234", request)).thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.update("1234", "My integration", oauthConfig);
    }

    @Test(expected = IntegrationNotFoundException.class)
    public void updateIntegration_itShouldThrowIntegrationNotFoundException() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myId");
        integrationConfiguration.setClientSecret("mySecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);

        IntegrationUpdateRequest request = new IntegrationUpdateRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My integration");
        request.setConfig(integrationConfiguration);

        when(integrationsApi.updateIntegration("1234", request)).thenThrow(new ApiException(404, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.update("1234", "My integration", oauthConfig);
    }

    @Test(expected = InternalErrorException.class)
    public void updateIntegration_itShouldThrowInternalErrorException() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myId");
        integrationConfiguration.setClientSecret("mySecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);

        IntegrationUpdateRequest request = new IntegrationUpdateRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My integration");
        request.setConfig(integrationConfiguration);

        when(integrationsApi.updateIntegration("1234", request)).thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.update("1234", "My integration", oauthConfig);
    }

    @Test
    public void updateIntegration_itShouldUpdateAnIntegration() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConfiguration integrationConfiguration = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        integrationConfiguration.setClientId("myOtherId");
        integrationConfiguration.setClientSecret("myOtherSecret");
        integrationConfiguration.setTokenPath("/token");
        integrationConfiguration.setRevokePath("/revoke");
        integrationConfiguration.setAuthorizeBaseUrl(new URI("https://example.com"));
        integrationConfiguration.setSite(new URI("https://example.com/oauth"));
        integrationConfiguration.setScopes(Arrays.asList("read", "write"));
        integrationConfiguration.setUseBodyAuth(false);

        IntegrationUpdateRequest request = new IntegrationUpdateRequest();
        request.setType(IntegrationType.OAUTH2);
        request.setName("My new integration");
        request.setConfig(integrationConfiguration);

        List<org.catalytic.sdk.generated.model.Field> connectionParams = new ArrayList<>();
        org.catalytic.sdk.generated.model.Field field = new org.catalytic.sdk.generated.model.Field();
        field.setName("foo");
        field.setValue("bar");
        field.setFieldType(FieldType.TEXT);
        connectionParams.add(field);

        org.catalytic.sdk.generated.model.Integration internalIntegration = new org.catalytic.sdk.generated.model.Integration();
        internalIntegration.setId("my-integration");
        internalIntegration.setName("My new integration");
        internalIntegration.setConnectionParams(connectionParams);

        when(integrationsApi.updateIntegration("1234", request)).thenReturn(internalIntegration);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myOtherId",
                "myOtherSecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        Integration integration = integrationsClient.update("1234", "My new integration", oauthConfig);
        assertThat(integration).isNotNull();
        assertThat(integration.getId()).isEqualTo("my-integration");
        assertThat(integration.getName()).isEqualTo("My new integration");

    }

    @Test(expected = InternalErrorException.class)
    public void deleteIntegration_itShouldThrowInternalErrorException() throws Exception {
        when(integrationsApi.deleteIntegration("1234")).thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.delete("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteIntegration_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(integrationsApi.deleteIntegration("1234")).thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.delete("1234");
    }

    @Test(expected = IntegrationNotFoundException.class)
    public void deleteIntegration_itShouldThrowIntegrationNotFoundException() throws Exception {
        when(integrationsApi.deleteIntegration("1234")).thenThrow(new ApiException(404, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConfiguration oauthConfig = new IntegrationConfiguration(
                "myId",
                "mySecret",
                "/token",
                "/revoke",
                new URI("https://example.com/oauth"),
                new URI("https://example.com"),
                Arrays.asList("read", "write"),
                false
        );
        integrationsClient.delete("1234");
    }

    @Test
    public void deleteIntegration_itShouldDeleteIntegration() throws Exception {
        org.catalytic.sdk.generated.model.Integration internalIntegration = new org.catalytic.sdk.generated.model.Integration();
        internalIntegration.setId("my-integration");

        when(integrationsApi.deleteIntegration("1234")).thenReturn(internalIntegration);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.delete("1234");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getIntegrationConnection_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Integrations integrationsClient = new Integrations(null);
        integrationsClient.getIntegrationConnection("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getIntegrationConnection_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(integrationsApi.getIntegrationConnection("-", "2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.getIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = IntegrationConnectionNotFoundException.class)
    public void getIntegrationConnection_itShouldThrowIntegrationConnectionNotFoundException() throws Exception {
        when(integrationsApi.getIntegrationConnection("-", "2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(404, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.getIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void getIntegrationConnection_itShouldThrowInternalErrorException() throws Exception {
        when(integrationsApi.getIntegrationConnection("-", "2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.getIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void getIntegrationConnection_itShouldGetIntegrationConnection() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection = new org.catalytic.sdk.generated.model.IntegrationConnection();
        internalIntegrationConnection.setIntegrationId("ac14952a-a331-457c-ac7d-9a284258b65a");
        internalIntegrationConnection.setId("2b4362d6-5e46-494c-846f-c53184c8d124");
        when(integrationsApi.getIntegrationConnection("-", "2b4362d6-5e46-494c-846f-c53184c8d124")).thenReturn(internalIntegrationConnection);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        IntegrationConnection integrationConnection = integrationsClient.getIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(integrationConnection.getIntegrationId()).isEqualTo("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void createIntegrationConnection_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Integrations integrationsClient = new Integrations(null);
        integrationsClient.createIntegrationConnection("1234", "My connection", null);
    }

    @Test(expected = IntegrationNotFoundException.class)
    public void createIntegrationConnection_itShouldThrowIntegrationNotFoundException() throws Exception {
        List<FieldUpdateRequest> internalConnectionParams = new ArrayList<>();
        FieldUpdateRequest fieldUpdateRequest = new FieldUpdateRequest();
        fieldUpdateRequest.setName("foo");
        fieldUpdateRequest.setReferenceName("foo");
        fieldUpdateRequest.setValue("bar");
        internalConnectionParams.add(fieldUpdateRequest);

        IntegrationConnectionCreationRequest request = new IntegrationConnectionCreationRequest();
        request.setIntegrationId("2b4362d6-5e46-494c-846f-c53184c8d124");
        request.setName("My connection");
        request.setConnectionParams(internalConnectionParams);
        when(integrationsApi.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", request))
                .thenThrow(new ApiException(404, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);

        List<Field> connectionParams = new ArrayList<>();
        connectionParams.add(new Field("foo", "bar"));
        integrationsClient.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", "My connection", connectionParams);
    }

    @Test(expected = InternalErrorException.class)
    public void createIntegrationConnection_itShouldThrowInternalErrorException() throws Exception {
        List<FieldUpdateRequest> internalConnectionParams = new ArrayList<>();
        FieldUpdateRequest fieldUpdateRequest = new FieldUpdateRequest();
        fieldUpdateRequest.setName("foo");
        fieldUpdateRequest.setReferenceName("foo");
        fieldUpdateRequest.setValue("bar");
        internalConnectionParams.add(fieldUpdateRequest);

        IntegrationConnectionCreationRequest request = new IntegrationConnectionCreationRequest();
        request.setIntegrationId("2b4362d6-5e46-494c-846f-c53184c8d124");
        request.setName("My connection");
        request.setConnectionParams(internalConnectionParams);
        when(integrationsApi.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", request))
                .thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);

        List<Field> connectionParams = new ArrayList<>();
        connectionParams.add(new Field("foo", "bar"));
        integrationsClient.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", "My connection", connectionParams);
    }

    @Test(expected = UnauthorizedException.class)
    public void createIntegrationConnection_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        List<FieldUpdateRequest> internalConnectionParams = new ArrayList<>();
        FieldUpdateRequest fieldUpdateRequest = new FieldUpdateRequest();
        fieldUpdateRequest.setName("foo");
        fieldUpdateRequest.setReferenceName("foo");
        fieldUpdateRequest.setValue("bar");
        internalConnectionParams.add(fieldUpdateRequest);

        IntegrationConnectionCreationRequest request = new IntegrationConnectionCreationRequest();
        request.setIntegrationId("2b4362d6-5e46-494c-846f-c53184c8d124");
        request.setName("My connection");
        request.setConnectionParams(internalConnectionParams);
        when(integrationsApi.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", request))
                .thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);

        List<Field> connectionParams = new ArrayList<>();
        connectionParams.add(new Field("foo", "bar"));
        integrationsClient.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", "My connection", connectionParams);
    }

    @Test
    public void createIntegrationConnection_itShouldCreateAnIntegrationConnection() throws Exception {
        List<FieldUpdateRequest> internalConnectionParams = new ArrayList<>();
        FieldUpdateRequest fieldUpdateRequest = new FieldUpdateRequest();
        fieldUpdateRequest.setName("foo");
        fieldUpdateRequest.setReferenceName("foo");
        fieldUpdateRequest.setValue("bar");
        internalConnectionParams.add(fieldUpdateRequest);

        IntegrationConnectionCreationRequest request = new IntegrationConnectionCreationRequest();
        request.setIntegrationId("2b4362d6-5e46-494c-846f-c53184c8d124");
        request.setName("My connection");
        request.setConnectionParams(internalConnectionParams);

        org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection = new org.catalytic.sdk.generated.model.IntegrationConnection();
        internalIntegrationConnection.setId("ac14952a-a331-457c-ac7d-9a284258b65a");
        internalIntegrationConnection.setIntegrationId("2b4362d6-5e46-494c-846f-c53184c8d124");
        internalIntegrationConnection.setName("My connection");

        when(integrationsApi.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", request))
                .thenReturn(internalIntegrationConnection);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);

        List<Field> connectionParams = new ArrayList<>();
        connectionParams.add(new Field("foo", "bar"));
        IntegrationConnection integrationConnection = integrationsClient.createIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124", "My connection", connectionParams);
        assertThat(integrationConnection).isNotNull();
        assertThat(integrationConnection.getId()).isEqualTo("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(integrationConnection.getIntegrationId()).isEqualTo("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteIntegrationConnection_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(integrationsApi.deleteIntegrationConnection("-", "ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(401, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.deleteIntegrationConnection("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = IntegrationConnectionNotFoundException.class)
    public void deleteIntegrationConnection_itShouldThrowIntegrationNotFoundExceptionIfIntegrationDoesNotExist() throws Exception {
        when(integrationsApi.deleteIntegrationConnection("-", "ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(404, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.deleteIntegrationConnection("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void deleteIntegrationConnection_itShouldThrowInternalErrorException() throws Exception {
        when(integrationsApi.deleteIntegrationConnection("-", "ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(500, null));

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.deleteIntegrationConnection("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void deleteIntegrationConnection_itShouldDeleteAnIntegrationConnection() throws Exception {
        org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection = new org.catalytic.sdk.generated.model.IntegrationConnection();
        internalIntegrationConnection.setId("2b4362d6-5e46-494c-846f-c53184c8d124");

        when(integrationsApi.deleteIntegrationConnection("-", "2b4362d6-5e46-494c-846f-c53184c8d124"))
                .thenReturn(internalIntegrationConnection);

        Integrations integrationsClient = new Integrations("1234", integrationsApi);
        integrationsClient.deleteIntegrationConnection("2b4362d6-5e46-494c-846f-c53184c8d124");
    }
}
