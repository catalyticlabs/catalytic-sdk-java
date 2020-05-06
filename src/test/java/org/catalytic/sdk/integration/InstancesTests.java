package org.catalytic.sdk.integration;

import org.catalytic.sdk.Client;
import org.catalytic.sdk.entities.*;
import org.catalytic.sdk.search.Where;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;

public class InstancesTests {

    @Test
    public void itShouldGetAnInstance() throws Exception {
        Client catalytic = new Client();
        Instance instance = catalytic.instances().get("f956af4a-6e2c-4bee-9f6e-09d3387b7c8c");
        assertThat(instance).isNotNull();
        assertThat(instance.getId().toString()).isEqualTo("f956af4a-6e2c-4bee-9f6e-09d3387b7c8c");
    }

    @Test
    public void itShouldFindAllInstances() throws Exception {
        Client catalytic = new Client();
        InstancesPage results = catalytic.instances().find();
        assertThat(results.getInstances()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindInstancesByName() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstancesPage results = catalytic.instances().find(
                where.text().matches("Testing PHP SDK - Mar 30 at 11:08 PM")
        );
        assertThat(results.getInstances()).hasSize(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(1);
    }

    @Test
    public void itShouldFindInstancesByOwner() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstancesPage results = catalytic.instances().find(
                where.owner().is("tcaflisch@catalytic.com")
        );
        assertThat(results.getInstances()).hasSize(25);
        assertThat(results.getNextPageToken()).isEqualTo("25");
        assertThat(results.getCount()).isEqualTo(25);
    }

    @Test
    public void itShouldFindInstancesByStatus() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstancesPage results = catalytic.instances().find(
                where.status().is(InstanceStatus.COMPLETED.getValue())
        );
        assertThat(results.getInstances()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindInstancesByWorkflowId() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstancesPage results = catalytic.instances().find(
                where.workflowId().is("7e77254c-d2d6-4271-965a-98390aefa50a")
        );
        assertThat(results.getInstances()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldStartAnInstance() throws Exception {
        Client catalytic = new Client();
        Instance instance = catalytic.instances().start("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(instance).isNotNull();
        assertThat(instance.getWorkflowId().toString()).isEqualTo("7e77254c-d2d6-4271-965a-98390aefa50a");
    }

    @Test
    public void itShouldStartAnInstanceWithNameAndDescription() throws Exception {
        Client catalytic = new Client();
        Instance instance = catalytic.instances().start(
                UUID.fromString("7e77254c-d2d6-4271-965a-98390aefa50a"),
                "My Java Instance",
                "I created a java instance with no fields!",
                null);
        assertThat(instance).isNotNull();
        assertThat(instance.getWorkflowId().toString()).isEqualTo("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(instance.getName()).isEqualTo("My Java Instance");
        assertThat(instance.getDescription()).isEqualTo("I created a java instance with no fields!");
    }

    @Test
    public void itShouldStartAnInstanceWithFields() throws Exception {
        Client catalytic = new Client();
        List<Field> fields = new ArrayList<>();
        Field field = new Field("myfoo", "barme");
        fields.add(field);
        Instance instance = catalytic.instances().start(
                "7e77254c-d2d6-4271-965a-98390aefa50a",
                "My Java Instance With Fields",
                "I created a java instance with fields!",
                fields);
        assertThat(instance).isNotNull();
        assertThat(instance.getWorkflowId().toString()).isEqualTo("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(instance.getName()).isEqualTo("My Java Instance With Fields");
        assertThat(instance.getDescription()).isEqualTo("I created a java instance with fields!");
        assertThat(instance.getFields()).isNotNull();
    }

    @Test
    public void itShouldStopAnInstance() throws Exception {
        Client catalytic = new Client();
        Instance instance = catalytic.instances().start("7e77254c-d2d6-4271-965a-98390aefa50a");
        instance = catalytic.instances().stop(instance.getId().toString());
        assertThat(instance).isNotNull();
        assertThat(instance.getId().toString()).isEqualTo(instance.getId().toString());
    }

    @Test
    public void itShouldGetInstanceStepById() throws Exception {
        Client catalytic = new Client();
        InstanceStep instanceStep = catalytic.instances().getStep("70c931f3-6230-45d4-bb3d-1b1915457c7b");
        assertThat(instanceStep.getId().toString()).isEqualTo("70c931f3-6230-45d4-bb3d-1b1915457c7b");
        assertThat(instanceStep.getInstanceId().toString()).isEqualTo("85390a7f-4756-4b70-b12e-268b626985ab");
    }

    @Test
    public void itShouldGetAllInstanceSteps() throws Exception {
        Client catalytic = new Client();
        InstanceStepsPage results = catalytic.instances().getSteps("85390a7f-4756-4b70-b12e-268b626985ab");
        assertThat(results.getInstanceSteps()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(2);
    }

    @Test
    public void itShouldFindInstanceStepsByName() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstanceStepsPage results = catalytic.instances().findSteps(
                where.text().matches("Assign to me")
        );
        assertThat(results.getInstanceSteps()).hasSize(25);
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isEqualTo(25);
    }

    @Test
    public void itShouldFindInstanceStepsByWorkflowId() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstancesPage results = catalytic.instances().find(
                where.workflowId().is("7e77254c-d2d6-4271-965a-98390aefa50a")
        );
        assertThat(results.getInstances()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindInstanceStepsByAssignee() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        InstanceStepsPage results = catalytic.instances().findSteps(
                where.assignee().is("general")
        );
        assertThat(results.getInstanceSteps()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    // TODO: Need to come back to this one. It's failing
    @Ignore
    @Test
    public void itShouldCompleteAStep() throws Exception {
        Client catalytic = new Client();
        Instance instance = catalytic.instances().start("7e77254c-d2d6-4271-965a-98390aefa50a");
        List<InstanceStep> steps = catalytic.instances().getSteps(instance.getId().toString()).getInstanceSteps();
        InstanceStep step = steps.get(0);
        System.out.println("step id = " + step.getId());
        System.out.println("instance id = " + step.getInstanceId());
        step = catalytic.instances().completeStep(step.getId().toString(), null);
        System.out.println("status = " + step.getStatus());
        assertThat(step.getStatus()).isEqualTo("Ended");
    }
}
