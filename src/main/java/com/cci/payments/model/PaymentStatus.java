package com.cci.payments.model;

public enum PaymentStatus {
    CREATED(PaymentStatus.CREATED_VAL, "enumPaymentStatusCreated"), //In Basket
    POSTPONED(PaymentStatus.POSTPONED_VAL, "enumPaymentStatusPostponed"),
    DELETED(PaymentStatus.DELETED_VAL, "enumPaymentStatusDeleted"),
    COMPLETED(PaymentStatus.COMPLETED_VAL,"enumPaymentStatusCompleted");

    private final String name;
    private final String translation;

    public String getName() {
        return this.name;
    }

    public String getTranslation() {
        return translation;
    }

    PaymentStatus(String name, String translation) {
        this.name = name;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return getName();
    }

    private static final String CREATED_VAL = "created";
    private static final String POSTPONED_VAL = "postponed";
    private static final String DELETED_VAL = "deleted";
    private static final String COMPLETED_VAL = "completed";
}
