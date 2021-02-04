package org.catalytic.sdk.search;

import org.catalytic.sdk.entities.InstanceStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class used to build queries for searching for Instances
 */
public class InstancesWhere {
    /**
     * Search by multiple criteria
     *
     * @param and   Search criteria to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause and(InstanceSearchClause... and) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setAnd(and);
        return instanceSearchClause;
    }

    /**
     * Search by multiple criteria
     *
     * @param or    Search criteria to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause or(InstanceSearchClause... or) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setOr(or);
        return instanceSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause id(UUID id) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setId(id);
        return instanceSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause id(String id) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setId(UUID.fromString(id));
        return instanceSearchClause;
    }

    /**
     * Search by workflowId
     *
     * @param workflowId    The workflowId to search for Instances by
     * @return              The search clause
     */
    public static InstanceSearchClause workflowId(UUID workflowId) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setWorkflowId(workflowId);
        return instanceSearchClause;
    }

    /**
     * Search by workflowId
     *
     * @param workflowId    The workflowId to search for Instances by
     * @return              The search clause
     */
    public static InstanceSearchClause workflowId(String workflowId) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setWorkflowId(UUID.fromString(workflowId));
        return instanceSearchClause;
    }

    /**
     * Search by rootInstanceId
     *
     * @param rootInstanceId    The rootInstanceId to search for Instances by
     * @return                  The search clause
     */
    public static InstanceSearchClause rootInstanceId(String rootInstanceId) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setRootInstanceId(UUID.fromString(rootInstanceId));
        return instanceSearchClause;
    }

    /**
     * Search by rootInstanceId
     *
     * @param rootInstanceId    The rootInstanceId to search for Instances by
     * @return                  The search clause
     */
    public static InstanceSearchClause rootInstanceId(UUID rootInstanceId) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setRootInstanceId(rootInstanceId);
        return instanceSearchClause;
    }

    /**
     * Search by owner email
     *
     * @param ownerEmail    The email of the owner to search for Instances by
     * @return              The search clause
     */
    public static InstanceSearchClause ownerEmail(String ownerEmail) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setOwnerEmail(ownerEmail);
        return instanceSearchClause;
    }

    /**
     * Search by isTest
     *
     * @param isTest    The isTest to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause isTest(Boolean isTest) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setIsTest(isTest);
        return instanceSearchClause;
    }

    /**
     * Search by name
     *
     * @param name  The name to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause name(String name) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setName(name);
        return instanceSearchClause;
    }

    /**
     * Search by partial name
     *
     * @param name  The partial name to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause nameContains(String name) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setNameContains(name);
        return instanceSearchClause;
    }

    /**
     * Search by a range of names
     *
     * @param start The inclusive start name to search for Instances by
     * @param end   The inclusive end name to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause nameBetween(String start, String end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setNameBetween(start, end);
        return instanceSearchClause;
    }

    /**
     * Search by status
     *
     * @param status    The status to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause status(InstanceStatus status) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setStatus(status);
        return instanceSearchClause;
    }

    /**
     * Search by description
     *
     * @param description   The description to search for Instances by
     * @return              The search clause
     */
    public static InstanceSearchClause description(String description) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setDescription(description);
        return instanceSearchClause;
    }

    /**
     * Search by partial description
     *
     * @param description   The partial description to search for Instances by
     * @return              The search clause
     */
    public static InstanceSearchClause descriptionContains(String description) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setDescriptionContains(description);
        return instanceSearchClause;
    }

    /**
     * Search by a range of descriptions
     *
     * @param start The inclusive start description to search for Instances by
     * @param end   The inclusive end description to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause descriptionBetween(String start, String end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setDescriptionBetween(start, end);
        return instanceSearchClause;
    }

    /**
     * Search by category
     *
     * @param category  The category to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause category(String category) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setCategory(category);
        return instanceSearchClause;
    }

    /**
     * Search by partial category
     *
     * @param category  The partial category to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause categoryContains(String category) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setCategoryContains(category);
        return instanceSearchClause;
    }

    /**
     * Search by a range of categories
     *
     * @param start The inclusive start category to search for Instances by
     * @param end   The inclusive end category to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause categoryBetween(String start, String end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setCategoryBetween(start, end);
        return instanceSearchClause;
    }

    /**
     * Search by start date
     *
     * @param startDate The start date to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause startDate(OffsetDateTime startDate) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setStartDate(startDate);
        return instanceSearchClause;
    }

    /**
     * Search by start date
     *
     * @param startDate The start date to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause startDate(String startDate) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        OffsetDateTime startDateAsDate = OffsetDateTime.parse(startDate);
        instanceSearchClause.setStartDate(startDateAsDate);
        return instanceSearchClause;
    }

    /**
     * Search by a range of start dates
     *
     * @param start The inclusive start date to search for Instances by
     * @param end   The inclusive end date to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause startDateBetween(OffsetDateTime start, OffsetDateTime end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setStartDateBetween(start, end);
        return instanceSearchClause;
    }

    /**
     * Search by a range of start dates
     *
     * @param start The inclusive start date to search for Instances by
     * @param end   The inclusive end date to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause startDateBetween(String start, String end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        instanceSearchClause.setStartDateBetween(startAsDate, endAsDate);
        return instanceSearchClause;
    }

    /**
     * Search by end date
     *
     * @param endDate   The end date to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause endDate(OffsetDateTime endDate) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setEndDate(endDate);
        return instanceSearchClause;
    }

    /**
     * Search by end date
     *
     * @param endDate   The end date to search for Instances by
     * @return          The search clause
     */
    public static InstanceSearchClause endDate(String endDate) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        OffsetDateTime endDateAsDate = OffsetDateTime.parse(endDate);
        instanceSearchClause.setEndDate(endDateAsDate);
        return instanceSearchClause;
    }

    /**
     * Search by a range of end dates
     *
     * @param start The inclusive start date to search for Instances by
     * @param end   The inclusive end date to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause endDateBetween(OffsetDateTime start, OffsetDateTime end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        instanceSearchClause.setEndDateBetween(start, end);
        return instanceSearchClause;
    }

    /**
     * Search by a range of end dates
     *
     * @param start The inclusive start date to search for Instances by
     * @param end   The inclusive end date to search for Instances by
     * @return      The search clause
     */
    public static InstanceSearchClause endDateBetween(String start, String end) {
        InstanceSearchClause instanceSearchClause = new InstanceSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        instanceSearchClause.setEndDateBetween(startAsDate, endAsDate);
        return instanceSearchClause;
    }
}
