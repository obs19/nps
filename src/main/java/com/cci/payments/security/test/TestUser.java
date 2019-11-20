package com.cci.payments.security.test;

import java.io.Serializable;
import java.util.List;

public class TestUser implements Serializable {
    private String userName;
    private String credentials;
    private List<AppRoleEnum> roles;

    public TestUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public List<AppRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRoleEnum> roles) {
        this.roles = roles;
    }

    public TestUser(String userName, String credentials, List<AppRoleEnum> roles) {
        this.userName = userName;
        this.credentials = credentials;

        this.roles = roles;
    }
}
