package com.collicode.propertytracker.service.dto;


import com.collicode.propertytracker.infrastructure.model.enums.UserRole;
import lombok.Builder;

@Builder
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String msisdn;
    private String username;
    private UserRole userRole;
    private Boolean showImportantInfo;
    AgentDTO agent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getShowImportantInfo() {
        return showImportantInfo;
    }
    public void setShowImportantInfo(Boolean showImportantInfo) {
        this.showImportantInfo = showImportantInfo;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", username='" + username + '\'' +
                ", userRole=" + userRole +
                ", showImportantInfo=" + showImportantInfo +
                ", agent=" + agent +
                '}';
    }
}
