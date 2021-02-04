package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class used to build queries for searching for Workflows
 */
public class WorkflowsWhere {

    /**
     * Search by multiple criteria
     *
     * @param and   Search criteria to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause and(WorkflowSearchClause... and) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setAnd(and);
        return workflowSearchClause;
    }

    /**
     * Search by multiple criteria
     *
     * @param or    Search criteria to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause or(WorkflowSearchClause... or) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setOr(or);
        return workflowSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause id(UUID id) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setId(id);
        return workflowSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause id(String id) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setId(UUID.fromString(id));
        return workflowSearchClause;
    }

    /**
     * Search by name
     *
     * @param name  The name to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause name(String name) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setName(name);
        return workflowSearchClause;
    }

    /**
     * Search by partial name
     *
     * @param name  The partial name to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause nameContains(String name) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setNameContains(name);
        return workflowSearchClause;
    }

    /**
     * Search by a range of names
     *
     * @param start The inclusive start name to search for Workflows by
     * @param end   The inclusive end name to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause nameBetween(String start, String end) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setNameBetween(start, end);
        return workflowSearchClause;
    }

    /**
     * Search by description
     *
     * @param description   The description to search for Workflows by
     * @return              The search clause
     */
    public static WorkflowSearchClause description(String description) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setDescription(description);
        return workflowSearchClause;
    }

    /**
     * Search by partial description
     *
     * @param description   The partial description to search for Workflows by
     * @return              The search clause
     */
    public static WorkflowSearchClause descriptionContains(String description) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setDescriptionContains(description);
        return workflowSearchClause;
    }

    /**
     * Search by a range of descriptions
     *
     * @param start The inclusive start description to search for Workflows by
     * @param end   The inclusive end description to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause descriptionBetween(String start, String end) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setDescriptionBetween(start, end);
        return workflowSearchClause;
    }

    /**
     * Search by owner email
     *
     * @param ownerEmail    The email of the owner to search for Workflows by
     * @return              The search clause
     */
    public static WorkflowSearchClause ownerEmail(String ownerEmail) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setOwnerEmail(ownerEmail);
        return workflowSearchClause;
    }

    /**
     * Search by category
     *
     * @param category  The category to search for Workflows by
     * @return          The search clause
     */
    public static WorkflowSearchClause category(String category) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setCategory(category);
        return workflowSearchClause;
    }

    /**
     * Search by partial category
     *
     * @param category  The partial category to search for Workflows by
     * @return          The search clause
     */
    public static WorkflowSearchClause categoryContains(String category) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setCategoryContains(category);
        return workflowSearchClause;
    }

    /**
     * Search by a range of categories
     *
     * @param start The inclusive start category to search for Workflows by
     * @param end   The inclusive end category to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause categoryBetween(String start, String end) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setCategoryBetween(start, end);
        return workflowSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for Workflows by
     * @return              The search clause
     */
    public static WorkflowSearchClause createdDate(OffsetDateTime createdDate) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setCreatedDate(createdDate);
        return workflowSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for Workflows by
     * @return              The search clause
     */
    public static WorkflowSearchClause createdDate(String createdDate) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        OffsetDateTime createdDateAsDate = OffsetDateTime.parse(createdDate);
        workflowSearchClause.setCreatedDate(createdDateAsDate);
        return workflowSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for Workflows by
     * @param end   The inclusive end date to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause createdDateBetween(OffsetDateTime start, OffsetDateTime end) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        workflowSearchClause.setCreatedDateBetween(start, end);
        return workflowSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for Workflows by
     * @param end   The inclusive end date to search for Workflows by
     * @return      The search clause
     */
    public static WorkflowSearchClause createdDateBetween(String start, String end) {
        WorkflowSearchClause workflowSearchClause = new WorkflowSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        workflowSearchClause.setCreatedDateBetween(startAsDate, endAsDate);
        return workflowSearchClause;
    }
}
