package com.cci.payments.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentHistoryDTO implements DtoObject {
    private Long id;
    private String recipientFullName ;
    private String clientName ;
    private Date creationDate;
    private Date completedDate;
    private String status;
    private BigDecimal amount;

    public PaymentHistoryDTO() {
    }

    @Override
    public String toString() {
        return "PaymentHistoryDTO{" +
                "id=" + id +
                ", recipientFullName='" + recipientFullName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", creationDate=" + creationDate +
                ", completedDate=" + completedDate +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
