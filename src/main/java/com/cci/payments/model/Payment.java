package com.cci.payments.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment extends LongIdObject implements DeletableMarker{

    @OneToOne
    @JoinColumn(name = "ref_form_id", referencedColumnName = "id")
    private PaymentForm form;
    @Column(name="creation_date")
    private LocalDateTime creationDate;
    @Column(name="postponed_date")
    private LocalDateTime postponedDate;
    @Column(name="completed_date")
    private LocalDateTime completedDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_user_id", referencedColumnName = "id")
    private User user;

    public Payment() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentForm getForm() {
        return form;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }

    public void setForm(PaymentForm form) {
        this.form = form;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPostponedDate() {
        return postponedDate;
    }

    public void setPostponedDate(LocalDateTime postponedDate) {
        this.postponedDate = postponedDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean isDeleted() {
        return this.deleted;
    }
}
