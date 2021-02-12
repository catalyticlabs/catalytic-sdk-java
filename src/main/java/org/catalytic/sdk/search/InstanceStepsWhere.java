package org.catalytic.sdk.search;

import org.catalytic.sdk.entities.InstanceStepStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class used to build queries for searching for Instance Steps
 */
public class InstanceStepsWhere {

    /**
     * Search by multiple criteria
     *
     * @param and   Search criteria to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause and(InstanceStepSearchClause... and) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setAnd(and);
        return instanceStepSearchClause;
    }

    /**
     * Search by multiple criteria
     *
     * @param or    Search criteria to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause or(InstanceStepSearchClause... or) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setOr(or);
        return instanceStepSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause id(UUID id) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setId(id);
        return instanceStepSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause id(String id) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setId(UUID.fromString(id));
        return instanceStepSearchClause;
    }

    /**
     * Search by instanceId
     *
     * @param instanceId    The id to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause instanceId(UUID instanceId) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setInstanceId(instanceId);
        return instanceStepSearchClause;
    }

    /**
     * Search by instanceId
     *
     * @param instanceId    The id to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause instanceId(String instanceId) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setInstanceId(UUID.fromString(instanceId));
        return instanceStepSearchClause;
    }

    /**
     * Search by workflowId
     *
     * @param workflowId    The workflowId to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause workflowId(UUID workflowId) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setWorkflowId(workflowId);
        return instanceStepSearchClause;
    }

    /**
     * Search by workflowId
     *
     * @param workflowId    The workflowId to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause workflowId(String workflowId) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setWorkflowId(UUID.fromString(workflowId));
        return instanceStepSearchClause;
    }

    /**
     * Search by name
     *
     * @param name  The name to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause name(String name) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setName(name);
        return instanceStepSearchClause;
    }

    /**
     * Search by partial name
     *
     * @param name  The partial name to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause nameContains(String name) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setNameContains(name);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of names
     *
     * @param start The inclusive start name to search for InstanceSteps by
     * @param end   The inclusive end name to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause nameBetween(String start, String end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setNameBetween(start, end);
        return instanceStepSearchClause;
    }

    /**
     * Search by description
     *
     * @param description   The description to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause description(String description) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setDescription(description);
        return instanceStepSearchClause;
    }

    /**
     * Search by partial description
     *
     * @param description   The partial description to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause descriptionContains(String description) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setDescriptionContains(description);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of descriptions
     *
     * @param start The inclusive start description to search for InstanceSteps by
     * @param end   The inclusive end description to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause descriptionBetween(String start, String end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setDescriptionBetween(start, end);
        return instanceStepSearchClause;
    }

    /**
     * Search by status
     *
     * @param status    The status to search for InstanceSteps by
     * @return          The search clause
     */
    public static InstanceStepSearchClause status(InstanceStepStatus status) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setStatus(status);
        return instanceStepSearchClause;
    }

    /**
     * Search by assignedToEmail
     *
     * @param assignedToEmail   The assignedToEmail to search for InstanceSteps by
     * @return                  The search clause
     */
    public static InstanceStepSearchClause assignedToEmail(String assignedToEmail) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setAssignedToEmail(assignedToEmail);
        return instanceStepSearchClause;
    }

    /**
     * Search by completedByEmail
     *
     * @param completedByEmail  The completedByEmail to search for InstanceSteps by
     * @return                  The search clause
     */
    public static InstanceStepSearchClause completedByEmail(String completedByEmail) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setCompletedByEmail(completedByEmail);
        return instanceStepSearchClause;
    }

    /**
     * Search by start date
     *
     * @param startDate The start date to search for InstanceSteps by
     * @return          The search clause
     */
    public static InstanceStepSearchClause startDate(OffsetDateTime startDate) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setStartDate(startDate);
        return instanceStepSearchClause;
    }

    /**
     * Search by start date
     *
     * @param startDate The start date to search for InstanceSteps by
     * @return          The search clause
     */
    public static InstanceStepSearchClause startDate(String startDate) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        OffsetDateTime startDateAsDate = OffsetDateTime.parse(startDate);
        instanceStepSearchClause.setStartDate(startDateAsDate);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of start dates
     *
     * @param start The inclusive start date to search for InstanceSteps by
     * @param end   The inclusive end date to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause startDateBetween(OffsetDateTime start, OffsetDateTime end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setStartDateBetween(start, end);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of start dates
     *
     * @param start The inclusive start date to search for InstanceSteps by
     * @param end   The inclusive end date to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause startDateBetween(String start, String end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        instanceStepSearchClause.setStartDateBetween(startAsDate, endAsDate);
        return instanceStepSearchClause;
    }

    /**
     * Search by end date
     *
     * @param endDate   The end date to search for InstanceSteps by
     * @return          The search clause
     */
    public static InstanceStepSearchClause endDate(OffsetDateTime endDate) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setEndDate(endDate);
        return instanceStepSearchClause;
    }

    /**
     * Search by end date
     *
     * @param endDate   The end date to search for InstanceSteps by
     * @return          The search clause
     */
    public static InstanceStepSearchClause endDate(String endDate) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        OffsetDateTime endDateAsDate = OffsetDateTime.parse(endDate);
        instanceStepSearchClause.setEndDate(endDateAsDate);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of end dates
     *
     * @param start The inclusive start date to search for InstanceSteps by
     * @param end   The inclusive end date to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause endDateBetween(OffsetDateTime start, OffsetDateTime end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setEndDateBetween(start, end);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of end dates
     *
     * @param start The inclusive start date to search for InstanceSteps by
     * @param end   The inclusive end date to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause endDateBetween(String start, String end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        instanceStepSearchClause.setEndDateBetween(startAsDate, endAsDate);
        return instanceStepSearchClause;
    }

    /**
     * Search by actionTypeId
     *
     * @param actionTypeId  The actionTypeId to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause actionTypeId(String actionTypeId) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setActionTypeId(actionTypeId);
        return instanceStepSearchClause;
    }

    /**
     * Search by partial actionTypeId
     *
     * @param actionTypeId  The partial actionTypeId to search for InstanceSteps by
     * @return              The search clause
     */
    public static InstanceStepSearchClause actionTypeIdContains(String actionTypeId) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setActionTypeIdContains(actionTypeId);
        return instanceStepSearchClause;
    }

    /**
     * Search by a range of actionTypeId's
     *
     * @param start The inclusive start actionTypeId to search for InstanceSteps by
     * @param end   The inclusive end actionTypeId to search for InstanceSteps by
     * @return      The search clause
     */
    public static InstanceStepSearchClause actionTypeIdBetween(String start, String end) {
        InstanceStepSearchClause instanceStepSearchClause = new InstanceStepSearchClause();
        instanceStepSearchClause.setActionTypeIdBetween(start, end);
        return instanceStepSearchClause;
    }
}
