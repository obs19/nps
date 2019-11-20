package com.cci.payments.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class User extends LongIdObject implements DeletableMarker {
    @Column(nullable = false)
    private String login;
    private String pass;
    private String fullName;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;
    @Enumerated(EnumType.STRING)
    private UserActivityStatus status;

    public UserActivityStatus getStatus() {
        return status;
    }

    public void setStatus(UserActivityStatus status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    @Override
    public boolean isDeleted() {
        return this.deleted;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
