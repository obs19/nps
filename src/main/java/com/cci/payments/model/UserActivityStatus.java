package com.cci.payments.model;

public enum UserActivityStatus {
    ACTIVE(UserActivityStatus.ACTIVE_VAL),
    BLOCKED(UserActivityStatus.BLOCKED_VAL);
//    DELETED(UserActivityStatus.DELETED_VAL);

    private final String name;

    public String getName() {
        return this.name;
    }


    UserActivityStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    private static final String ACTIVE_VAL = "active";
    private static final String BLOCKED_VAL = "blocked";
//    private static final String DELETED_VAL = "deleted";

}
