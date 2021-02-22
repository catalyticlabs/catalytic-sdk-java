package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class used to build queries for searching for Actions
 */
public class ActionsWhere {

    /**
     * Search by multiple criteria
     *
     * @param and   Search criteria to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause and(ActionSearchClause... and) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setAnd(and);
        return actionSearchClause;
    }

    /**
     * Search by multiple criteria
     *
     * @param or    Search criteria to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause or(ActionSearchClause... or) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setOr(or);
        return actionSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause id(UUID id) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setId(id);
        return actionSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause id(String id) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setId(UUID.fromString(id));
        return actionSearchClause;
    }

    /**
     * Search by name
     *
     * @param name  The name to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause name(String name) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setName(name);
        return actionSearchClause;
    }

    /**
     * Search by partial name
     *
     * @param name  The partial name to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause nameContains(String name) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setNameContains(name);
        return actionSearchClause;
    }

    /**
     * Search by a range of names
     *
     * @param start The inclusive start name to search for Actions by
     * @param end   The inclusive end name to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause nameBetween(String start, String end) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setNameBetween(start, end);
        return actionSearchClause;
    }

    /**
     * Search by description
     *
     * @param description   The description to search for Actions by
     * @return              The search clause
     */
    public static ActionSearchClause description(String description) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setDescription(description);
        return actionSearchClause;
    }

    /**
     * Search by partial description
     *
     * @param description   The partial description to search for Actions by
     * @return              The search clause
     */
    public static ActionSearchClause descriptionContains(String description) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setDescriptionContains(description);
        return actionSearchClause;
    }

    /**
     * Search by a range of descriptions
     *
     * @param start The inclusive start description to search for Actions by
     * @param end   The inclusive end description to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause descriptionBetween(String start, String end) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setDescriptionBetween(start, end);
        return actionSearchClause;
    }

    /**
     * Search by originalActionId
     *
     * @param originalActionId  The original Action id to search for Actions by
     * @return                  The search clause
     */
    public static ActionSearchClause originalActionId(UUID originalActionId) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setOriginalActionId(originalActionId);
        return actionSearchClause;
    }

    /**
     * Search by originalActionId
     *
     * @param originalActionId  The original Action id to search for Actions by
     * @return                  The search clause
     */
    public static ActionSearchClause originalActionId(String originalActionId) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setOriginalActionId(UUID.fromString(originalActionId));
        return actionSearchClause;
    }

    /**
     * Search by created by email
     *
     * @param createdByEmail    The created by email to search for Actions by
     * @return                  The search clause
     */
    public static ActionSearchClause createdByEmail(String createdByEmail) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setCreatedByEmail(createdByEmail);
        return actionSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for Actions by
     * @return              The search clause
     */
    public static ActionSearchClause createdDate(OffsetDateTime createdDate) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setCreatedDate(createdDate);
        return actionSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for Actions by
     * @return              The search clause
     */
    public static ActionSearchClause createdDate(String createdDate) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        OffsetDateTime createdDateAsDate = OffsetDateTime.parse(createdDate);
        actionSearchClause.setCreatedDate(createdDateAsDate);
        return actionSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for Actions by
     * @param end   The inclusive end date to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause createdDateBetween(OffsetDateTime start, OffsetDateTime end) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        actionSearchClause.setCreatedDateBetween(start, end);
        return actionSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for Actions by
     * @param end   The inclusive end date to search for Actions by
     * @return      The search clause
     */
    public static ActionSearchClause createdDateBetween(String start, String end) {
        ActionSearchClause actionSearchClause = new ActionSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        actionSearchClause.setCreatedDateBetween(startAsDate, endAsDate);
        return actionSearchClause;
    }
}
