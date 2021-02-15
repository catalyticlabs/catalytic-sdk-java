package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class used to build queries for searching Data Tables
 */
public class DataTablesWhere {

    /**
     * Search by multiple criteria
     *
     * @param and   Search criteria to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause and(DataTableSearchClause... and) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setAnd(and);
        return dataTableSearchClause;
    }

    /**
     * Search by multiple criteria
     *
     * @param or    Search criteria to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause or(DataTableSearchClause... or) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setOr(or);
        return dataTableSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause id(UUID id) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setId(id);
        return dataTableSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause id(String id) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setId(UUID.fromString(id));
        return dataTableSearchClause;
    }

    /**
     * Search by name
     *
     * @param name  The name to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause name(String name) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setName(name);
        return dataTableSearchClause;
    }

    /**
     * Search by partial name
     *
     * @param name  The partial name to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause nameContains(String name) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setNameContains(name);
        return dataTableSearchClause;
    }

    /**
     * Search by a range of names
     *
     * @param start The inclusive start name to search for DataTables by
     * @param end   The inclusive end name to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause nameBetween(String start, String end) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setNameBetween(start, end);
        return dataTableSearchClause;
    }

    /**
     * Search by isArchived
     *
     * @param isArchived    The isArchived to search for DataTables by
     * @return              The search clause
     */
    public static DataTableSearchClause ownerEmail(Boolean isArchived) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setIsArchived(isArchived);
        return dataTableSearchClause;
    }

    /**
     * Search by created by email
     *
     * @param createdByEmail    The email of the createdBy to search for DataTables by
     * @return                  The search clause
     */
    public static DataTableSearchClause createdByEmail(String createdByEmail) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setCreatedByEmail(createdByEmail);
        return dataTableSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for DataTables by
     * @return              The search clause
     */
    public static DataTableSearchClause createdDate(OffsetDateTime createdDate) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setCreatedDate(createdDate);
        return dataTableSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for DataTables by
     * @return              The search clause
     */
    public static DataTableSearchClause createdDate(String createdDate) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        OffsetDateTime createdDateAsDate = OffsetDateTime.parse(createdDate);
        dataTableSearchClause.setCreatedDate(createdDateAsDate);
        return dataTableSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for DataTables by
     * @param end   The inclusive end date to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause createdDateBetween(OffsetDateTime start, OffsetDateTime end) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        dataTableSearchClause.setCreatedDateBetween(start, end);
        return dataTableSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for DataTables by
     * @param end   The inclusive end date to search for DataTables by
     * @return      The search clause
     */
    public static DataTableSearchClause createdDateBetween(String start, String end) {
        DataTableSearchClause dataTableSearchClause = new DataTableSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        dataTableSearchClause.setCreatedDateBetween(startAsDate, endAsDate);
        return dataTableSearchClause;
    }
}
