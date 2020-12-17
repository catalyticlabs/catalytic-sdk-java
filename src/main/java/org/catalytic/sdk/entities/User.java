package org.catalytic.sdk.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * A User object
 */
public class User {

    private UUID id;
    private String teamName;
    private String username;
    private String email;
    private String fullName;
    private Boolean isTeamAdmin;
    private Boolean isDeactivated;
    private Boolean isLockedOut;
    private OffsetDateTime createdDate;

    public User(UUID id, String teamName, String username, String email, String fullName, Boolean isTeamAdmin, Boolean isDeactivated, Boolean isLockedOut, OffsetDateTime createdDate) {
        this.id = id;
        this.teamName = teamName;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.isTeamAdmin = isTeamAdmin;
        this.isDeactivated = isDeactivated;
        this.isLockedOut = isLockedOut;
        this.createdDate = createdDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Boolean getIsTeamAdmin() {
        return isTeamAdmin;
    }

    public void setIsTeamAdmin(Boolean isTeamAdmin) {
        isTeamAdmin = isTeamAdmin;
    }

    public Boolean getIsDeactivated() {
        return isDeactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        isDeactivated = deactivated;
    }

    public Boolean getIsLockedOut() {
        return isLockedOut;
    }

    public void setIsLockedOut(Boolean isLockedOut) {
        isLockedOut = isLockedOut;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", isTeamAdmin=" + isTeamAdmin +
                ", isDeactivated=" + isDeactivated +
                ", isLockedOut=" + isLockedOut +
                ", createdDate=" + createdDate +
                '}';
    }
}