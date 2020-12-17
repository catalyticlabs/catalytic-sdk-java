package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class used to build queries for searching for Users
 */
public class UsersWhere {

    /**
     * Search by multiple criteria
     *
     * @param and   Search criteria to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause and(UserSearchClause... and) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setAnd(and);
        return userSearchClause;
    }

    /**
     * Search by multiple criteria
     *
     * @param or    Search criteria to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause or(UserSearchClause... or) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setOr(or);
        return userSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause id(UUID id) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setId(id);
        return userSearchClause;
    }

    /**
     * Search by id
     *
     * @param id    The id to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause id(String id) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setId(UUID.fromString(id));
        return userSearchClause;
    }

    /**
     * Search by email
     *
     * @param email The email to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause email(String email) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setEmail(email);
        return userSearchClause;
    }

    /**
     * Search by partial email
     *
     * @param email The partial email to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause emailContains(String email) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setEmailContains(email);
        return userSearchClause;
    }

    /**
     * Search by a range of emails
     *
     * @param start The inclusive start email to search for Users by
     * @param end   The inclusive end email to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause emailBetween(String start, String end) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setEmailBetween(start, end);
        return userSearchClause;
    }

    /**
     * Search by full name
     *
     * @param fullName  The full name to search for Users by
     * @return          The search clause
     */
    public static UserSearchClause fullName(String fullName) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setFullName(fullName);
        return userSearchClause;
    }

    /**
     * Search by partial full name
     *
     * @param fullName  The partial full name to search for Users by
     * @return          The search clause
     */
    public static UserSearchClause fullNameContains(String fullName) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setFullNameContains(fullName);
        return userSearchClause;
    }

    /**
     * Search by a range of full name's
     *
     * @param start The inclusive start full name to search for Users by
     * @param end   The inclusive end full name to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause fullNameBetween(String start, String end) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setFullNameBetween(start, end);
        return userSearchClause;
    }

    /**
     * Search by team admin
     *
     * @param isTeamAdmin   The team admin to search for Users by
     * @return              The search clause
     */
    public static UserSearchClause isTeamAdmin(Boolean isTeamAdmin) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setIsTeamAdmin(isTeamAdmin);
        return userSearchClause;
    }

    /**
     * Search by deactivated
     *
     * @param isDeactivated The deactivated to search for Users by
     * @return              The search clause
     */
    public static UserSearchClause isDeactivated(Boolean isDeactivated) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setIsDeactivated(isDeactivated);
        return userSearchClause;
    }

    /**
     * Search by locked out
     *
     * @param isLockedOut   The locked out to search or Users by
     * @return              The search clause
     */
    public static UserSearchClause isLockedOut(Boolean isLockedOut) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setIsLockedOut(isLockedOut);
        return userSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for Users by
     * @return              The search clause
     */
    public static UserSearchClause createdDate(OffsetDateTime createdDate) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setCreatedDate(createdDate);
        return userSearchClause;
    }

    /**
     * Search by created date
     *
     * @param createdDate   The created date to search for Users by
     * @return              The search clause
     */
    public static UserSearchClause createdDate(String createdDate) {
        UserSearchClause userSearchClause = new UserSearchClause();
        OffsetDateTime createdDateAsDate = OffsetDateTime.parse(createdDate);
        userSearchClause.setCreatedDate(createdDateAsDate);
        return userSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for Users by
     * @param end   The inclusive end date to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause createdDateBetween(OffsetDateTime start, OffsetDateTime end) {
        UserSearchClause userSearchClause = new UserSearchClause();
        userSearchClause.setCreatedDateBetween(start, end);
        return userSearchClause;
    }

    /**
     * Search by a range of created dates
     *
     * @param start The inclusive start date to search for Users by
     * @param end   The inclusive end date to search for Users by
     * @return      The search clause
     */
    public static UserSearchClause createdDateBetween(String start, String end) {
        UserSearchClause userSearchClause = new UserSearchClause();
        OffsetDateTime startAsDate = OffsetDateTime.parse(start);
        OffsetDateTime endAsDate = OffsetDateTime.parse(end);
        userSearchClause.setCreatedDateBetween(startAsDate, endAsDate);
        return userSearchClause;
    }
}
