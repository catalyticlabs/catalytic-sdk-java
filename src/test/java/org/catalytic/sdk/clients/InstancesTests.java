package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.Instance;
import org.catalytic.sdk.entities.InstanceStatus;
import org.catalytic.sdk.entities.InstanceStep;
import org.catalytic.sdk.entities.InstanceStepStatus;
import org.catalytic.sdk.entities.InstanceStepsPage;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.InstanceStepsApi;
import org.catalytic.sdk.generated.api.InstancesApi;
import org.catalytic.sdk.generated.model.*;
import org.catalytic.sdk.search.InstanceStepSearchClause;
import org.catalytic.sdk.search.InstanceStepsWhere;
import org.catalytic.sdk.search.InstancesWhere;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InstancesTests {

    InstancesApi instancesApi;
    InstanceStepsApi instanceStepsApi;

    @Before
    public void before() {
        instancesApi = mock(InstancesApi.class);
        instanceStepsApi = mock(InstanceStepsApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getInstance_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances instancesClient = new Instances(null);
        instancesClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InstanceNotFoundException.class)
    public void getInstance_itShouldThrowInstanceNotFoundExceptionIfInstanceDoesNotExist() throws Exception {
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void getInstance_itShouldThrowInternalErrorException() throws Exception {
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void getInstance_itShouldGetAnInstance() throws Exception {
        org.catalytic.sdk.generated.model.Instance sdkInstance = new org.catalytic.sdk.generated.model.Instance();
        sdkInstance.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        sdkInstance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        sdkInstance.setVisibility(InstanceVisibility.OPEN);
        sdkInstance.setFieldVisibility(FieldVisibility.PUBLIC);
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenReturn(sdkInstance);

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        Instance instance = instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(instance).isNotNull();
        assertThat(instance.getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void list_itShouldReturnAllInstances() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance1 = new org.catalytic.sdk.generated.model.Instance();
        org.catalytic.sdk.generated.model.Instance instance2 = new org.catalytic.sdk.generated.model.Instance();
        instance1.setName("Instance one");
        instance1.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance1.setVisibility(InstanceVisibility.OPEN);
        instance1.setFieldVisibility(FieldVisibility.PUBLIC);
        instance2.setName("Instance two");
        instance2.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance2.setVisibility(InstanceVisibility.OPEN);
        instance2.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage firstPage = new org.catalytic.sdk.generated.model.InstancesPage();
        org.catalytic.sdk.generated.model.InstancesPage secondPage = new org.catalytic.sdk.generated.model.InstancesPage();
        firstPage.setInstances(Arrays.asList(instance1));
        firstPage.setCount(Arrays.asList(instance1).size());
        firstPage.setNextPageToken("123");
        secondPage.setInstances(Arrays.asList(instance2));
        secondPage.setCount(Arrays.asList(instance2).size());
        secondPage.setNextPageToken("123");
        secondPage.setNextPageToken("");
        when(instancesApi.searchInstances(null, null, new org.catalytic.sdk.generated.model.InstanceSearchClause()))
                .thenReturn(firstPage);
        when(instancesApi.searchInstances("123", null, new org.catalytic.sdk.generated.model.InstanceSearchClause()))
                .thenReturn(secondPage);

        Instances instancesClient = new Instances("1234", instancesApi, null);
        org.catalytic.sdk.entities.InstancesPage results = instancesClient.list();

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("Instance one");
        assertThat(results.getInstances().get(1).getName()).isEqualTo("Instance two");
    }

    @Test(expected = UnauthorizedException.class)
    public void search_itShouldReturnUnauthorizedException() throws Exception {
        when(instancesApi.searchInstances(null, null, new org.catalytic.sdk.generated.model.InstanceSearchClause()))
                .thenThrow(new ApiException(401, null));

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstancesClient.search(null);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void search_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances InstancesClient = new Instances(null);
        InstancesClient.search(null);
    }

    @Test(expected = InternalErrorException.class)
    public void search_itShouldReturnInternalErrorException() throws Exception {
        when(instancesApi.searchInstances(null, null, new org.catalytic.sdk.generated.model.InstanceSearchClause()))
                .thenThrow(new ApiException(500, null));

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstancesClient.search(null);
    }

    @Test
    public void search_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setName("My instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");
        when(instancesApi.searchInstances("abc123", null, new org.catalytic.sdk.generated.model.InstanceSearchClause()))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(null, "abc123", null);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My instance");
    }

    @Test
    public void search_itShouldFindInstanceById() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression id = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        id.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        InstanceSearchClause.setId(id);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.id(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
    }

    @Test
    public void search_itShouldFindInstanceByWorkflowId() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        instance.setWorkflowId(UUID.fromString("14a044c2-d11d-4d64-a51b-074e4cf7b667"));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression workflowId = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        workflowId.setIsEqualTo(UUID.fromString("14a044c2-d11d-4d64-a51b-074e4cf7b667"));
        InstanceSearchClause.setWorkflowId(workflowId);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.workflowId("14a044c2-d11d-4d64-a51b-074e4cf7b667");
        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstances().get(0).getWorkflowId()).isEqualTo(UUID.fromString("14a044c2-d11d-4d64-a51b-074e4cf7b667"));
    }

    @Test
    public void search_itShouldFindInstanceByRootInstanceId() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        instance.setRootInstanceId(UUID.fromString("14a044c2-d11d-4d64-a51b-074e4cf7b667"));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression rootInstanceId = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        rootInstanceId.setIsEqualTo(UUID.fromString("14a044c2-d11d-4d64-a51b-074e4cf7b667"));
        InstanceSearchClause.setRootInstanceId(rootInstanceId);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.rootInstanceId("14a044c2-d11d-4d64-a51b-074e4cf7b667");
        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstances().get(0).getRootInstanceId()).isEqualTo(UUID.fromString("14a044c2-d11d-4d64-a51b-074e4cf7b667"));
    }

    @Test
    public void search_itShouldFindInstanceByOwner() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setOwnerEmail("Bob Marley");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression ownerEmail = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
        ownerEmail.setIsEqualTo("Bob Marley");
        InstanceSearchClause.setOwnerEmail(ownerEmail);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.ownerEmail("Bob Marley");

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getOwnerEmail()).isEqualTo("Bob Marley");
    }

    @Test
    public void search_itShouldFindInstanceByIsTest() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setIsTest(true);
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.BoolSearchExpression isTest = new org.catalytic.sdk.generated.model.BoolSearchExpression();
        isTest.setIsEqualTo(true);
        InstanceSearchClause.setIsTest(isTest);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.isTest(true);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getIsTest()).isEqualTo(true);
    }

    @Test
    public void search_itShouldFindInstanceByName() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setName("My instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("My instance");
        InstanceSearchClause.setName(name);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.name("My instance");

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My instance");
    }

    @Test
    public void search_itShouldFindInstanceByPartialName() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setName("My instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setContains("my");
        InstanceSearchClause.setName(name);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.nameContains("my");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My instance");
    }

    @Test
    public void search_itShouldFindInstanceBetweenNames() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setName("My instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange nameRange = new org.catalytic.sdk.generated.model.StringRange();
        nameRange.setLowerBoundInclusive("Ma");
        nameRange.setUpperBoundInclusive("Mz");
        name.setBetween(nameRange);
        InstanceSearchClause.setName(name);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.nameBetween("Ma", "Mz");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My instance");
    }

    @Test
    public void search_itShouldFindInstanceByStatus() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setName("My instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.InstanceStatusSearchExpression status = new org.catalytic.sdk.generated.model.InstanceStatusSearchExpression();
        status.setIsEqualTo(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        InstanceSearchClause.setStatus(status);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.status(InstanceStatus.RUNNING);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My instance");
    }

    @Test
    public void search_itShouldFindInstanceByDescription() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setDescription("My first instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setIsEqualTo("My first instance");
        InstanceSearchClause.setDescription(description);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.description("My first instance");

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getDescription()).isEqualTo("My first instance");
    }

    @Test
    public void search_itShouldFindInstanceByPartialDescription() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setDescription("My first instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setContains("first");
        InstanceSearchClause.setDescription(description);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.descriptionContains("first");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getDescription()).isEqualTo("My first instance");
    }

    @Test
    public void search_itShouldFindInstanceBetweenDescriptions() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setDescription("My first instance");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange fullNameRange = new org.catalytic.sdk.generated.model.StringRange();
        fullNameRange.setLowerBoundInclusive("Ma");
        fullNameRange.setUpperBoundInclusive("Mz");
        fullName.setBetween(fullNameRange);
        InstanceSearchClause.setDescription(fullName);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.descriptionBetween("Ma", "Mz");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getDescription()).isEqualTo("My first instance");
    }

    @Test
    public void search_itShouldFindInstanceByCategory() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setCategory("General");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression category = new org.catalytic.sdk.generated.model.StringSearchExpression();
        category.setIsEqualTo("General");
        InstanceSearchClause.setCategory(category);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.category("General");

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getCategory()).isEqualTo("General");
    }

    @Test
    public void search_itShouldFindInstanceByPartialCategory() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setCategory("General");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression category = new org.catalytic.sdk.generated.model.StringSearchExpression();
        category.setContains("ener");
        InstanceSearchClause.setCategory(category);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.categoryContains("ener");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getCategory()).isEqualTo("General");
    }

    @Test
    public void search_itShouldFindInstanceBetweenCategories() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setCategory("General");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage InstancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        InstancesPage.setInstances(Arrays.asList(instance));
        InstancesPage.setCount(Arrays.asList(instance).size());
        InstancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression category = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange categoryRange = new org.catalytic.sdk.generated.model.StringRange();
        categoryRange.setLowerBoundInclusive("Ga");
        categoryRange.setUpperBoundInclusive("Gz");
        category.setBetween(categoryRange);
        InstanceSearchClause.setCategory(category);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(InstancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.categoryBetween("Ga", "Gz");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getCategory()).isEqualTo("General");
    }

    @Test
    public void search_itShouldFindInstanceByStartDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setStartDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression startDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        startDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        InstanceSearchClause.setStartDate(startDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.startDate("2020-01-01T00:00:00.000-06:00");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstanceByStartDate() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setStartDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression startDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        startDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        InstanceSearchClause.setStartDate(startDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.startDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstancesByStartDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setStartDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        InstanceSearchClause.setStartDate(createdDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.startDateBetween(
                "2020-01-01T00:00:00.000-06:00",
                "2020-12-01T00:00:00.000-06:00"
        );
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstancesByCreatedDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setStartDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange endDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        endDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDate.setBetween(endDateRange);
        InstanceSearchClause.setStartDate(endDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.startDateBetween(
                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
        );
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstanceByEndDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setEndDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);

        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        endDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        InstanceSearchClause.setEndDate(endDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.endDate("2020-01-01T00:00:00.000-06:00");
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstanceByEndDate() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setEndDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        endDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        InstanceSearchClause.setEndDate(endDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.endDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstancesByEndDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setEndDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange endDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        endDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDate.setBetween(endDateRange);
        InstanceSearchClause.setEndDate(endDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.endDateBetween(
                "2020-01-01T00:00:00.000-06:00",
                "2020-12-01T00:00:00.000-06:00"
        );
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstancesByEndDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setEndDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange endDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        endDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDate.setBetween(endDateRange);
        InstanceSearchClause.setEndDate(endDate);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.endDateBetween(
                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
        );
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindInstanceByNameAndDescription() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance = new org.catalytic.sdk.generated.model.Instance();
        instance.setName("My instance name");
        instance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance.setVisibility(InstanceVisibility.OPEN);
        instance.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(instance));
        instancesPage.setCount(Arrays.asList(instance).size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();

        List<org.catalytic.sdk.generated.model.InstanceSearchClause> and = new ArrayList<>();
        org.catalytic.sdk.generated.model.InstanceSearchClause nameClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.InstanceSearchClause fullNameClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("My instance name");
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setIsEqualTo("My first instance");
        nameClause.setName(name);
        fullNameClause.setDescription(description);
        and.add(nameClause);
        and.add(fullNameClause);

        InstanceSearchClause.setAnd(and);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        org.catalytic.sdk.search.InstanceSearchClause nameSearchClause = InstancesWhere.name("My instance name");
        org.catalytic.sdk.search.InstanceSearchClause fullNameSearchClause = InstancesWhere.description("My first instance");
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.and(nameSearchClause, fullNameSearchClause);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My instance name");
    }

    @Test
    public void search_itShouldFindInstanceByNameOrName() throws Exception {
        org.catalytic.sdk.generated.model.Instance instance1 = new org.catalytic.sdk.generated.model.Instance();
        org.catalytic.sdk.generated.model.Instance instance2 = new org.catalytic.sdk.generated.model.Instance();
        instance1.setName("Instance one");
        instance1.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance1.setVisibility(InstanceVisibility.OPEN);
        instance1.setFieldVisibility(FieldVisibility.PUBLIC);
        instance2.setName("Instance two");
        instance2.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        instance2.setVisibility(InstanceVisibility.OPEN);
        instance2.setFieldVisibility(FieldVisibility.PUBLIC);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        List<org.catalytic.sdk.generated.model.Instance> instances = Arrays.asList(instance1, instance2);
        instancesPage.setInstances(instances);
        instancesPage.setCount(instances.size());
        instancesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceSearchClause InstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();

        List<org.catalytic.sdk.generated.model.InstanceSearchClause> or = new ArrayList<>();
        org.catalytic.sdk.generated.model.InstanceSearchClause nameInstance1Clause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.InstanceSearchClause nameInstance2Clause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression nameInstance1 = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringSearchExpression nameInstance2 = new org.catalytic.sdk.generated.model.StringSearchExpression();
        nameInstance1.setIsEqualTo("Instance one");
        nameInstance2.setIsEqualTo("Instance two");
        nameInstance1Clause.setName(nameInstance1);
        nameInstance2Clause.setName(nameInstance2);
        or.add(nameInstance1Clause);
        or.add(nameInstance2Clause);

        InstanceSearchClause.setOr(or);
        when(instancesApi.searchInstances(null, null, InstanceSearchClause))
                .thenReturn(instancesPage);

        org.catalytic.sdk.search.InstanceSearchClause instance1Name = InstancesWhere.name("Instance one");
        org.catalytic.sdk.search.InstanceSearchClause instance2Name = InstancesWhere.name("Instance two");
        org.catalytic.sdk.search.InstanceSearchClause searchCriteria = InstancesWhere.or(instance1Name, instance2Name);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstancesPage results = InstancesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("Instance one");
        assertThat(results.getInstances().get(1).getName()).isEqualTo("Instance two");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void startInstance_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances instancesClient = new Instances(null);
        instancesClient.start("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void startInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void startInstance_itShouldThrowInstanceNotFoundException() throws Exception {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void startInstance_itShouldThrowInternalErrorException() throws Exception {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void startInstance_itShouldStartInstance() throws Exception {
        org.catalytic.sdk.generated.model.Instance internalInstance = new org.catalytic.sdk.generated.model.Instance();
        internalInstance.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalInstance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        internalInstance.setVisibility(InstanceVisibility.OPEN);
        internalInstance.setFieldVisibility(FieldVisibility.PUBLIC);
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenReturn(internalInstance);

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        Instance instance = instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(instance.getWorkflowId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void stopInstance_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances instancesClient = new Instances(null);
        instancesClient.stop("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void stopInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InstanceNotFoundException.class)
    public void stopInstance_itShouldThrowInstanceNotFoundException() throws Exception {
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void stopInstance_itShouldThrowInternalErrorException() throws Exception {
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void stopInstance_itShouldStopInstance() throws Exception {
        org.catalytic.sdk.generated.model.Instance internalInstance = new org.catalytic.sdk.generated.model.Instance();
        internalInstance.workflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalInstance.setStatus(org.catalytic.sdk.generated.model.InstanceStatus.RUNNING);
        internalInstance.setVisibility(InstanceVisibility.OPEN);
        internalInstance.setFieldVisibility(FieldVisibility.PUBLIC);
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenReturn(internalInstance);

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        Instance instance = instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(instance.getWorkflowId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getStep_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances instancesClient = new Instances(null);
        instancesClient.getStep("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getStep_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InstanceStepNotFoundException.class)
    public void getStep_itShouldThrowInstanceStepNotFoundException() throws Exception {
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void getStep_itShouldThrowInternalErrorException() throws Exception {
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void getStep_itShouldGetInstanceStep() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep internalStep = new org.catalytic.sdk.generated.model.InstanceStep();
        internalStep.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        internalStep.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenReturn(internalStep);

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstanceStep instanceStep = instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(instanceStep.getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getSteps_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances instancesClient = new Instances(null);
        instancesClient.getSteps("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = UnauthorizedException.class)
    public void getSteps_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        instanceId.setIsEqualTo(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
        internalSearchClause.setInstanceId(instanceId);

        when(instanceStepsApi.searchInstanceSteps("2b4362d6-5e46-494c-846f-c53184c8d124", null, null, internalSearchClause))
            .thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.getSteps("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void getSteps_itShouldThrowInternalErrorException() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        instanceId.setIsEqualTo(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
        internalSearchClause.setInstanceId(instanceId);

        when(instanceStepsApi.searchInstanceSteps("2b4362d6-5e46-494c-846f-c53184c8d124", null, null, internalSearchClause))
            .thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.getSteps("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void getSteps_itShouldReturnAllInstanceSteps() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        step.setInstanceId(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        instanceId.setIsEqualTo(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
        internalSearchClause.setInstanceId(instanceId);
        when(instanceStepsApi.searchInstanceSteps("2b4362d6-5e46-494c-846f-c53184c8d124", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstanceStepsPage results = instancesClient.getSteps("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void completeStep_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances instancesClient = new Instances(null);
        instancesClient.completeStep("1234", null);
    }

    @Test
    public void completeStep_itShouldCompleteAnInstanceStep() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep internalStep = new org.catalytic.sdk.generated.model.InstanceStep();
        internalStep.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        CompleteStepRequest completeStepRequest = new CompleteStepRequest();
        completeStepRequest.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        completeStepRequest.setStepOutputFields(new ArrayList<FieldUpdateRequest>());
        internalStep.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalStep.setInstanceId(UUID.fromString("98950bf5-1cae-4359-b09a-1cdc13f6b1b6"));
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenReturn(internalStep);
        when(instanceStepsApi.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", "98950bf5-1cae-4359-b09a-1cdc13f6b1b6", completeStepRequest))
                .thenReturn(internalStep);

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstanceStep instanceStep = instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
        assertThat(instanceStep).isNotNull();
        assertThat(instanceStep.getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void completeInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
    }

    @Test(expected = InstanceStepNotFoundException.class)
    public void completeInstance_itShouldThrowInstanceNotFoundExceptionIfInstanceDoesNotExist() throws Exception {
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
    }

    @Test(expected = InternalErrorException.class)
    public void completeInstance_itShouldThrowInternalErrorException() throws Exception {
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
    }

    @Test(expected = UnauthorizedException.class)
    public void searchSteps_itShouldReturnUnauthorizedException() throws Exception {
        when(instanceStepsApi.searchInstanceSteps("-", null, null, new org.catalytic.sdk.generated.model.InstanceStepSearchClause()))
                .thenThrow(new ApiException(401, null));

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstancesClient.searchSteps(new InstanceStepSearchClause());
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void searchSteps_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Instances InstancesClient = new Instances(null);
        InstancesClient.searchSteps(new InstanceStepSearchClause());
    }

    @Test(expected = InternalErrorException.class)
    public void searchSteps_itShouldReturnInternalErrorException() throws Exception {
        when(instanceStepsApi.searchInstanceSteps("-", null, null, new org.catalytic.sdk.generated.model.InstanceStepSearchClause()))
                .thenThrow(new ApiException(500, null));

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        InstancesClient.searchSteps(new InstanceStepSearchClause());
    }

    @Test
    public void searchSteps_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");
        when(instanceStepsApi.searchInstanceSteps("-", "abc123", null, new org.catalytic.sdk.generated.model.InstanceStepSearchClause()))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(new InstanceStepSearchClause(), "abc123", null);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsById() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression id = new GuidSearchExpression();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        id.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        instanceId.setIsEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalSearchClause.setId(id);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.id("00f0e0af-7029-47b5-b38b-bb636fc1bd63"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByInstanceId() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setWorkflowId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        instanceId.setIsEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalSearchClause.setInstanceId(instanceId);
        when(instanceStepsApi.searchInstanceSteps("ac14952a-a331-457c-ac7d-9a284258b65a", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.instanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a")),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getWorkflowId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByInstanceIdAsString() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setWorkflowId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        instanceId.setIsEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalSearchClause.setInstanceId(instanceId);
        when(instanceStepsApi.searchInstanceSteps("ac14952a-a331-457c-ac7d-9a284258b65a", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.instanceId("ac14952a-a331-457c-ac7d-9a284258b65a"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getWorkflowId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByWorkflowId() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setWorkflowId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression workflowId = new GuidSearchExpression();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        workflowId.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        instanceId.setIsEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalSearchClause.setWorkflowId(workflowId);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.workflowId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63")),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getWorkflowId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByWorkflowIdAsString() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setWorkflowId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression workflowId = new GuidSearchExpression();
        org.catalytic.sdk.generated.model.GuidSearchExpression instanceId = new GuidSearchExpression();
        workflowId.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        instanceId.setIsEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalSearchClause.setWorkflowId(workflowId);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.workflowId("00f0e0af-7029-47b5-b38b-bb636fc1bd63"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getWorkflowId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByName() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new StringSearchExpression();
        name.setIsEqualTo("My instance step");
        internalSearchClause.setName(name);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.name("My instance step"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByNameContains() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new StringSearchExpression();
        name.setContains("my");
        internalSearchClause.setName(name);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.nameContains("my"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsBetweenNames() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange nameRange = new org.catalytic.sdk.generated.model.StringRange();
        nameRange.setLowerBoundInclusive("Ma");
        nameRange.setUpperBoundInclusive("Mz");
        name.setBetween(nameRange);
        internalSearchClause.setName(name);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.nameBetween("Ma", "Mz"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByDescription() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setDescription("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new StringSearchExpression();
        description.setIsEqualTo("My instance step");
        internalSearchClause.setDescription(description);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.description("My instance step"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getDescription()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByDescriptionContains() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setDescription("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new StringSearchExpression();
        description.setContains("my");
        internalSearchClause.setDescription(description);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.descriptionContains("my"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getDescription()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsBetweenDescriptions() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setDescription("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange descriptionRange = new org.catalytic.sdk.generated.model.StringRange();
        descriptionRange.setLowerBoundInclusive("Ma");
        descriptionRange.setUpperBoundInclusive("Mz");
        description.setBetween(descriptionRange);
        internalSearchClause.setDescription(description);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.descriptionBetween("Ma", "Mz"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getDescription()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByStatus() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setDescription("My instance step");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.InstanceStepStatusSearchExpression status = new InstanceStepStatusSearchExpression();
        status.setIsEqualTo(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        internalSearchClause.setStatus(status);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.status(InstanceStepStatus.ACTIVE),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getDescription()).isEqualTo("My instance step");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByAssignedToEmail() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setAssignedToEmail("bob@aol.com");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression assignedToEmail = new ExactStringSearchExpression();
        assignedToEmail.setIsEqualTo("bob@aol.com");
        internalSearchClause.setAssignedToEmail(assignedToEmail);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.assignedToEmail("bob@aol.com"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getAssignedToEmail()).isEqualTo("bob@aol.com");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByCompletedByEmail() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setCompletedByEmail("bob@aol.com");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression completedByEmail = new ExactStringSearchExpression();
        completedByEmail.setIsEqualTo("bob@aol.com");
        internalSearchClause.setCompletedByEmail(completedByEmail);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.completedByEmail("bob@aol.com"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getCompletedByEmail()).isEqualTo("bob@aol.com");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByStartDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setStartDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression startDate = new DateTimeSearchExpression();
        startDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        internalSearchClause.setStartDate(startDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.startDate("2020-01-01T00:00:00.000-06:00"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByStartDate() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setStartDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression startDate = new DateTimeSearchExpression();
        startDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        internalSearchClause.setStartDate(startDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.startDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByStartDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setStartDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause instanceStepSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression startDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange startDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        startDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        startDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        startDate.setBetween(startDateRange);
        instanceStepSearchClause.setStartDate(startDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, instanceStepSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.startDateBetween(
                        OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                        OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
                ),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByStarDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setStartDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause instanceStepSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression startDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange startDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        startDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        startDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        startDate.setBetween(startDateRange);
        instanceStepSearchClause.setStartDate(startDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, instanceStepSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.startDateBetween(
                        "2020-01-01T00:00:00.000-06:00",
                        "2020-12-01T00:00:00.000-06:00"
                ),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getStartDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByEndDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setEndDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new DateTimeSearchExpression();
        endDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        internalSearchClause.setEndDate(endDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.endDate("2020-01-01T00:00:00.000-06:00"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByEndDate() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setEndDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new DateTimeSearchExpression();
        endDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        internalSearchClause.setEndDate(endDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.endDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

    }

    @Test
    public void searchSteps_itShouldFindInstancesByEndDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setEndDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause instanceStepSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange endDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        endDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDate.setBetween(endDateRange);
        instanceStepSearchClause.setEndDate(endDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, instanceStepSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.endDateBetween(
                        OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                        OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
                ),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

    }

    @Test
    public void searchSteps_itShouldFindInstancesByEndDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setEndDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause instanceStepSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression endDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange endDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        endDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        endDate.setBetween(endDateRange);
        instanceStepSearchClause.setEndDate(endDate);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, instanceStepSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.endDateBetween(
                        "2020-01-01T00:00:00.000-06:00",
                        "2020-12-01T00:00:00.000-06:00"
                ),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getEndDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByActionTypeId() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setName("My instance step");
        step.setActionTypeId("my/app/id");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression actionTypeId = new StringSearchExpression();
        actionTypeId.setIsEqualTo("my/app/id");
        internalSearchClause.setActionTypeId(actionTypeId);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.actionTypeId("my/app/id"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getActionTypeId()).isEqualTo("my/app/id");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsByActionTypeIdContains() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setActionTypeId("my/app/id");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression actionTypeId = new StringSearchExpression();
        actionTypeId.setContains("my/app/id");
        internalSearchClause.setActionTypeId(actionTypeId);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.actionTypeIdContains("my/app/id"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getActionTypeId()).isEqualTo("my/app/id");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void searchSteps_itShouldFindInstanceStepsBetweenActionTypeIds() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep step = new org.catalytic.sdk.generated.model.InstanceStep();
        step.setActionTypeId("my/app/id");
        step.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        step.setStatus(org.catalytic.sdk.generated.model.InstanceStepStatus.ACTIVE);
        org.catalytic.sdk.generated.model.InstanceStepsPage InstanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        InstanceStepsPage.setSteps(Arrays.asList(step));
        InstanceStepsPage.setCount(Arrays.asList(step).size());
        InstanceStepsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.InstanceStepSearchClause internalSearchClause = new org.catalytic.sdk.generated.model.InstanceStepSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression actionTypeId = new StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange actionTypeIdRange = new org.catalytic.sdk.generated.model.StringRange();
        actionTypeIdRange.setLowerBoundInclusive("Ma");
        actionTypeIdRange.setUpperBoundInclusive("Mz");
        actionTypeId.setBetween(actionTypeIdRange);
        internalSearchClause.setActionTypeId(actionTypeId);
        when(instanceStepsApi.searchInstanceSteps("-", null, null, internalSearchClause))
                .thenReturn(InstanceStepsPage);

        Instances InstancesClient = new Instances("1234", instancesApi, instanceStepsApi);
        org.catalytic.sdk.entities.InstanceStepsPage results = InstancesClient.searchSteps(
                InstanceStepsWhere.actionTypeIdBetween("Ma", "Mz"),
                null,
                null
        );

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getInstanceSteps().get(0).getActionTypeId()).isEqualTo("my/app/id");
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }
}
