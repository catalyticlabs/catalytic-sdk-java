package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.Instance;
import org.catalytic.sdk.entities.InstanceStep;
import org.catalytic.sdk.entities.InstanceStepsPage;
import org.catalytic.sdk.entities.InstancesPage;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.InstanceStepsApi;
import org.catalytic.sdk.generated.api.InstancesApi;
import org.catalytic.sdk.generated.model.CompleteStepRequest;
import org.catalytic.sdk.generated.model.FieldUpdateRequest;
import org.catalytic.sdk.generated.model.InstanceStatus;
import org.catalytic.sdk.generated.model.StartInstanceRequest;
import org.catalytic.sdk.search.Where;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void getInstance_itShouldGetAInstance() throws Exception {
        org.catalytic.sdk.generated.model.Instance sdkInstance = new org.catalytic.sdk.generated.model.Instance();
        sdkInstance.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenReturn(sdkInstance);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Instance instance = instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(instance).isNotNull();
        assertThat(instance.getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InstanceNotFoundException.class)
    public void getInstance_itShouldThrowInstanceNotFoundExceptionIfInstanceDoesNotExist() throws Exception {
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void getInstance_itShouldThrowInternalErrorException() throws Exception {
        when(instancesApi.getInstance("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = UnauthorizedException.class)
    public void findInstances_itShouldReturnUnauthorizedException() throws Exception {
        when(instancesApi.findInstances(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findInstances_itShouldReturnInternalErrorException() throws Exception {
        when(instancesApi.findInstances(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.find();
    }

    @Test
    public void findInstances_itShouldReturnAllInstances() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances(null, null, null, null, null, null, null, null, null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        InstancesPage results = instancesClient.find();
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void findInstances_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances(null, null, null, null, null, null, null, "25", null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        InstancesPage results = instancesClient.find("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void findInstances_itShouldFindInstanceByName() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setName("My Instance");
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances("My Instance", null, null, null, null, null, null, null, null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstancesPage results = instancesClient.find(where.text().is("My Instance"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My Instance");
    }

    @Test
    public void findInstances_itShouldFindInstanceByOwner() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setOwner("alice@example.com");
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances(null, null, null, null, "alice@example.com", null, null, null, null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstancesPage results = instancesClient.find(where.owner().is("alice@example.com"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getOwner()).isEqualTo("alice@example.com");
    }

    @Test
    public void findInstances_itShouldFindInstanceByStatus() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setStatus(InstanceStatus.RUNNING);
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances(null, InstanceStatus.RUNNING.getValue(), null, null, null, null, null, null, null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstancesPage results = instancesClient.find(where.status().is(InstanceStatus.RUNNING.getValue()));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getStatus()).isEqualTo(InstanceStatus.RUNNING.getValue());
    }

    @Test
    public void findInstances_itShouldFindInstanceByWorkflowId() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setWorkflowId(UUID.fromString("b7b99c88-3ab6-4c92-bfce-bf3ff7d78026"));
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances(null, null, "b7b99c88-3ab6-4c92-bfce-bf3ff7d78026", null, null, null, null, null, null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstancesPage results = instancesClient.find(where.workflowId().is("b7b99c88-3ab6-4c92-bfce-bf3ff7d78026"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getWorkflowId()).isEqualTo(UUID.fromString("b7b99c88-3ab6-4c92-bfce-bf3ff7d78026"));
    }

    @Test
    public void findInstances_itShouldFindInstanceByNameAndPage() throws Exception {
        org.catalytic.sdk.generated.model.Instance myInstance = new org.catalytic.sdk.generated.model.Instance();
        myInstance.setName("My Instance");
        org.catalytic.sdk.generated.model.InstancesPage instancesPage = new org.catalytic.sdk.generated.model.InstancesPage();
        instancesPage.setInstances(Arrays.asList(myInstance));
        instancesPage.setCount(Arrays.asList(myInstance).size());
        instancesPage.setNextPageToken(null);
        when(instancesApi.findInstances("My Instance", null, null, null, null, null, null, "25", null))
                .thenReturn(instancesPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstancesPage results = instancesClient.find(where.text().is("My Instance"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstances().get(0).getName()).isEqualTo("My Instance");
    }

    @Test(expected = UnauthorizedException.class)
    public void startInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void startInstance_itShouldThrowWorkflowNotFoundException() throws Exception {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void startInstance_itShouldThrowInternalErrorException() throws Exception {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void startInstance_itShouldStartInstance() throws Exception {
        org.catalytic.sdk.generated.model.Instance internalInstance = new org.catalytic.sdk.generated.model.Instance();
        internalInstance.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        startInstanceRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.startInstance(startInstanceRequest)).thenReturn(internalInstance);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Instance instance = instancesClient.start("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(instance.getWorkflowId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void stopInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InstanceNotFoundException.class)
    public void stopInstance_itShouldThrowInstanceNotFoundException() throws Exception {
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void stopInstance_itShouldThrowInternalErrorException() throws Exception {
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void stopInstance_itShouldStopInstance() throws Exception {
        org.catalytic.sdk.generated.model.Instance internalInstance = new org.catalytic.sdk.generated.model.Instance();
        internalInstance.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instancesApi.stopInstance("2b4362d6-5e46-494c-846f-c53184c8d124")).thenReturn(internalInstance);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Instance instance = instancesClient.stop("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(instance.getWorkflowId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getInstanceStep_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InstanceStepNotFoundException.class)
    public void getInstanceStep_itShouldThrowInstanceStepNotFoundException() throws Exception {
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void getInstanceStep_itShouldThrowInternalErrorException() throws Exception {
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void getInstanceStep_itShouldGetInstanceStep() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep internalInstance = new org.catalytic.sdk.generated.model.InstanceStep();
        internalInstance.setInstanceId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(instanceStepsApi.getInstanceStep("2b4362d6-5e46-494c-846f-c53184c8d124", "-")).thenReturn(internalInstance);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        InstanceStep instanceStep = instancesClient.getStep("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(instanceStep.getInstanceId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getInstanceSteps_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instanceStepsApi.findInstanceSteps("2b4362d6-5e46-494c-846f-c53184c8d124", null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.getSteps("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test(expected = InternalErrorException.class)
    public void getInstanceSteps_itShouldThrowInternalErrorException() throws Exception {
        when(instanceStepsApi.findInstanceSteps("2b4362d6-5e46-494c-846f-c53184c8d124", null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.getSteps("2b4362d6-5e46-494c-846f-c53184c8d124");
    }

    @Test
    public void getInstanceSteps_itShouldReturnAllInstanceSteps() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep internalStep = new org.catalytic.sdk.generated.model.InstanceStep();
        internalStep.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalStep.setInstanceId(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
        org.catalytic.sdk.generated.model.InstanceStepsPage instanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        instanceStepsPage.setSteps(Arrays.asList(internalStep));
        instanceStepsPage.setCount(Arrays.asList(internalStep).size());
        instanceStepsPage.setNextPageToken(null);
        when(instanceStepsApi.findInstanceSteps("2b4362d6-5e46-494c-846f-c53184c8d124", null, null, null, null, null, null, null, null, null))
                .thenReturn(instanceStepsPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        InstanceStepsPage results = instancesClient.getSteps("2b4362d6-5e46-494c-846f-c53184c8d124");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstanceSteps().get(0).getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        assertThat(results.getInstanceSteps().get(0).getInstanceId()).isEqualTo(UUID.fromString("2b4362d6-5e46-494c-846f-c53184c8d124"));
    }

    @Test(expected = UnauthorizedException.class)
    public void findSteps_itShouldReturnUnauthorizedException() throws Exception {
        when(instanceStepsApi.findInstanceSteps("-", null, null, null, null, null, null, null, "25", null))
                .thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.findSteps("25");
    }

    @Test(expected = InternalErrorException.class)
    public void findSteps_itShouldReturnInternalErrorException() throws Exception {
        when(instanceStepsApi.findInstanceSteps("-", null, null, null, null, null, null, null, "25", null))
                .thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.findSteps("25");
    }

    @Test
    public void findSteps_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep instanceStep = new org.catalytic.sdk.generated.model.InstanceStep();
        instanceStep.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        org.catalytic.sdk.generated.model.InstanceStepsPage instanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        instanceStepsPage.setSteps(Arrays.asList(instanceStep));
        instanceStepsPage.setCount(Arrays.asList(instanceStep).size());
        instanceStepsPage.setNextPageToken(null);
        when(instanceStepsApi.findInstanceSteps("-", null, null, null, null, null, null, null, "25", null))
                .thenReturn(instanceStepsPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        InstanceStepsPage results = instancesClient.findSteps("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstanceSteps().get(0).getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void findSteps_itShouldFindInstanceStepsByText() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep instanceStep = new org.catalytic.sdk.generated.model.InstanceStep();
        instanceStep.setName("My InstanceStep");
        org.catalytic.sdk.generated.model.InstanceStepsPage instanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        instanceStepsPage.setSteps(Arrays.asList(instanceStep));
        instanceStepsPage.setCount(Arrays.asList(instanceStep).size());
        instanceStepsPage.setNextPageToken(null);
        when(instanceStepsApi.findInstanceSteps("-", "My InstanceStep", null, null, null, null, null, null, null, null))
                .thenReturn(instanceStepsPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstanceStepsPage results = instancesClient.findSteps(where.text().is("My InstanceStep"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My InstanceStep");
    }

    @Test
    public void findSteps_itShouldFindInstanceStepsByWorkflowId() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep instanceStep = new org.catalytic.sdk.generated.model.InstanceStep();
        instanceStep.setWorkflowId(UUID.fromString("b7b99c88-3ab6-4c92-bfce-bf3ff7d78026"));
        org.catalytic.sdk.generated.model.InstanceStepsPage instanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        instanceStepsPage.setSteps(Arrays.asList(instanceStep));
        instanceStepsPage.setCount(Arrays.asList(instanceStep).size());
        instanceStepsPage.setNextPageToken(null);
        when(instanceStepsApi.findInstanceSteps("-", null, null, "b7b99c88-3ab6-4c92-bfce-bf3ff7d78026", null, null, null, null, null, null))
                .thenReturn(instanceStepsPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstanceStepsPage results = instancesClient.findSteps(where.workflowId().is("b7b99c88-3ab6-4c92-bfce-bf3ff7d78026"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstanceSteps().get(0).getWorkflowId()).isEqualTo(UUID.fromString("b7b99c88-3ab6-4c92-bfce-bf3ff7d78026"));
    }

    @Test
    public void findSteps_itShouldFindInstanceStepsByAssignee() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep instanceStep = new org.catalytic.sdk.generated.model.InstanceStep();
        instanceStep.setAssignedTo("alice@example.com");
        org.catalytic.sdk.generated.model.InstanceStepsPage instanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        instanceStepsPage.setSteps(Arrays.asList(instanceStep));
        instanceStepsPage.setCount(Arrays.asList(instanceStep).size());
        instanceStepsPage.setNextPageToken(null);
        when(instanceStepsApi.findInstanceSteps("-", null, null, null, null, null, null, "alice@example.com", null, null))
                .thenReturn(instanceStepsPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstanceStepsPage results = instancesClient.findSteps(where.assignee().is("alice@example.com"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstanceSteps().get(0).getAssignedTo()).isEqualTo("alice@example.com");
    }

    @Test
    public void findSteps_itShouldFindInstanceStepsByNameAndPage() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep instanceStep = new org.catalytic.sdk.generated.model.InstanceStep();
        instanceStep.setName("My InstanceStep");
        org.catalytic.sdk.generated.model.InstanceStepsPage instanceStepsPage = new org.catalytic.sdk.generated.model.InstanceStepsPage();
        instanceStepsPage.setSteps(Arrays.asList(instanceStep));
        instanceStepsPage.setCount(Arrays.asList(instanceStep).size());
        instanceStepsPage.setNextPageToken(null);
        when(instanceStepsApi.findInstanceSteps("-", "My InstanceStep", null, null, null, null, null, null, "25",  null))
                .thenReturn(instanceStepsPage);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        Where where = new Where();
        InstanceStepsPage results = instancesClient.findSteps(where.text().is("My InstanceStep"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getInstanceSteps().get(0).getName()).isEqualTo("My InstanceStep");
    }

    @Test
    public void completeInstance_itShouldCompleteAnInstanceStep() throws Exception {
        org.catalytic.sdk.generated.model.InstanceStep internalstep = new org.catalytic.sdk.generated.model.InstanceStep();
        CompleteStepRequest completeStepRequest = new CompleteStepRequest();
        completeStepRequest.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        completeStepRequest.setStepOutputFields(new ArrayList<FieldUpdateRequest>());
        internalstep.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        internalstep.setInstanceId(UUID.fromString("98950bf5-1cae-4359-b09a-1cdc13f6b1b6"));
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenReturn(internalstep);
        when(instanceStepsApi.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", "98950bf5-1cae-4359-b09a-1cdc13f6b1b6", completeStepRequest))
                .thenReturn(internalstep);

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        InstanceStep instanceStep = instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
        assertThat(instanceStep).isNotNull();
        assertThat(instanceStep.getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void completeInstance_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenThrow(new ApiException(401, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
    }

    @Test(expected = InstanceStepNotFoundException.class)
    public void completeInstance_itShouldThrowInstanceNotFoundExceptionIfInstanceDoesNotExist() throws Exception {
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenThrow(new ApiException(404, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
    }

    @Test(expected = InternalErrorException.class)
    public void completeInstance_itShouldThrowInternalErrorException() throws Exception {
        when(instanceStepsApi.getInstanceStep("ac14952a-a331-457c-ac7d-9a284258b65a", "-")).thenThrow(new ApiException(500, null));

        Instances instancesClient = new Instances(instancesApi, instanceStepsApi);
        instancesClient.completeStep("ac14952a-a331-457c-ac7d-9a284258b65a", null);
    }
}
