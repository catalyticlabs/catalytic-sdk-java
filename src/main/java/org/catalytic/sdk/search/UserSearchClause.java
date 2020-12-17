package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Class used to create search criteria for searching Users
 */
public class UserSearchClause {

    private List<UserSearchClause> and;
    private List<UserSearchClause> or;
    private GuidSearchExpression id;
    private StringSearchExpression email;
    private StringSearchExpression fullName;
    private BooleanSearchExpression isTeamAdmin;
    private BooleanSearchExpression isDeactivated;
    private BooleanSearchExpression isLockedOut;
    private DateTimeSearchExpression createdDate;

    public List<UserSearchClause> getAnd() {
        return and;
    }

    public void setAnd(UserSearchClause... and) {
        List<UserSearchClause> andExpression = new ArrayList<>();
        andExpression.addAll(Arrays.asList(and));
        this.and = andExpression;
    }

    public List<UserSearchClause> getOr() {
        return or;
    }

    public void setOr(UserSearchClause... or) {
        List<UserSearchClause> orExpression = new ArrayList<>();
        orExpression.addAll(Arrays.asList(or));
        this.or = orExpression;
    }

    public GuidSearchExpression getId() {
        return id;
    }

    public void setId(UUID id) {
        GuidSearchExpression idExpression = new GuidSearchExpression();
        idExpression.setIsEqualTo(id);
        this.id = idExpression;
    }

    public StringSearchExpression getEmail() {
        return email;
    }

    public void setEmail(String email) {
        StringSearchExpression emailExpression = new StringSearchExpression();
        emailExpression.setIsEqualTo(email);
        this.email = emailExpression;
    }

    public void setEmailContains(String email) {
        StringSearchExpression emailExpression = new StringSearchExpression();
        emailExpression.setContains(email);
        this.email = emailExpression;
    }

    public void setEmailBetween(String start, String end) {
        StringSearchExpression emailExpression = new StringSearchExpression();
        StringRange emailRange = new StringRange(start, end);
        emailExpression.setBetween(emailRange);
        this.email = emailExpression;
    }

    public StringSearchExpression getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        StringSearchExpression fullNameExpression = new StringSearchExpression();
        fullNameExpression.setIsEqualTo(fullName);
        this.fullName = fullNameExpression;
    }

    public void setFullNameContains(String fullName) {
        StringSearchExpression fullNameExpression = new StringSearchExpression();
        fullNameExpression.setContains(fullName);
        this.fullName = fullNameExpression;
    }

    public void setFullNameBetween(String start, String end) {
        StringSearchExpression fullNameExpression = new StringSearchExpression();
        StringRange fullNameRange = new StringRange(start, end);
        fullNameExpression.setBetween(fullNameRange);
        this.fullName = fullNameExpression;
    }

    public BooleanSearchExpression getIsTeamAdmin() {
        return isTeamAdmin;
    }

    public void setIsTeamAdmin(Boolean isTeamAdmin) {
        BooleanSearchExpression isTeamAdminExpression = new BooleanSearchExpression();
        isTeamAdminExpression.setIsEqualTo(isTeamAdmin);
        this.isTeamAdmin = isTeamAdminExpression;
    }

    public BooleanSearchExpression getIsDeactivated() {
        return isDeactivated;
    }

    public void setIsDeactivated(Boolean isDeactivated) {
        BooleanSearchExpression isDeactivatedExpression = new BooleanSearchExpression();
        isDeactivatedExpression.setIsEqualTo(isDeactivated);
        this.isDeactivated = isDeactivatedExpression;
    }

    public BooleanSearchExpression getIsLockedOut() {
        return isLockedOut;
    }

    public void setIsLockedOut(Boolean isLockedOut) {
        BooleanSearchExpression isLockedOutExpression = new BooleanSearchExpression();
        isLockedOutExpression.setIsEqualTo(isLockedOut);
        this.isLockedOut = isLockedOutExpression;
    }

    public DateTimeSearchExpression getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        DateTimeSearchExpression createdDateExpression = new DateTimeSearchExpression();
        createdDateExpression.setIsEqualTo(createdDate);
        this.createdDate = createdDateExpression;
    }

    public void setCreatedDateBetween(OffsetDateTime start, OffsetDateTime end) {
        DateTimeSearchExpression createdDateExpression = new DateTimeSearchExpression();
        DateTimeOffsetRange between = new DateTimeOffsetRange(start, end);
        createdDateExpression.setBetween(between);
        this.createdDate = createdDateExpression;
    }

    @Override
    public String toString() {
        return "UserSearchClause{" +
                "and=" + and +
                ", or=" + or +
                ", id=" + id +
                ", email=" + email +
                ", fullName=" + fullName +
                ", isTeamAdmin=" + isTeamAdmin +
                ", isDeactivated=" + isDeactivated +
                ", isLockedOut=" + isLockedOut +
                ", createdDate=" + createdDate +
                '}';
    }
}
