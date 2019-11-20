package com.cci.payments.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentDTO implements DtoObject{

    //form info
    private Long id;
    private String clientName;
    private String clientITN;
    private String dictTypeOfPayment;
    private String dictRegisterName;
    private String dictPaymentAction;
    private String dictCountryRegion;
    private String customPayeeDetails;
    private String recipient;
    private BigDecimal amount;
    private BigDecimal amountTotal;
    // payment info
    private Date creationDate;
    private Date postponedDate;
    private Date completedDate;
    private String status;

    public PaymentDTO() {
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientITN='" + clientITN + '\'' +
                ", dictTypeOfPayment='" + dictTypeOfPayment + '\'' +
                ", dictRegisterName='" + dictRegisterName + '\'' +
                ", dictPaymentAction='" + dictPaymentAction + '\'' +
                ", dictCountryRegion='" + dictCountryRegion + '\'' +
                ", customPayeeDetails='" + customPayeeDetails + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                ", amountTotal=" + amountTotal +
                ", creationDate=" + creationDate +
                ", postponedDate=" + postponedDate +
                ", completedDate=" + completedDate +
                ", status='" + status + '\'' +
                '}';
    }

    public String getCustomPayeeDetails() {
        return customPayeeDetails;
    }

    public void setCustomPayeeDetails(String customPayeeDetails) {
        this.customPayeeDetails = customPayeeDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientITN() {
        return clientITN;
    }

    public void setClientITN(String clientITN) {
        this.clientITN = clientITN;
    }

    public String getDictTypeOfPayment() {
        return dictTypeOfPayment;
    }

    public void setDictTypeOfPayment(String dictTypeOfPayment) {
        this.dictTypeOfPayment = dictTypeOfPayment;
    }

    public String getDictRegisterName() {
        return dictRegisterName;
    }

    public void setDictRegisterName(String dictRegisterName) {
        this.dictRegisterName = dictRegisterName;
    }

    public String getDictPaymentAction() {
        return dictPaymentAction;
    }

    public void setDictPaymentAction(String dictPaymentAction) {
        this.dictPaymentAction = dictPaymentAction;
    }

    public String getDictCountryRegion() {
        return dictCountryRegion;
    }

    public void setDictCountryRegion(String dictCountryRegion) {
        this.dictCountryRegion = dictCountryRegion;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPostponedDate() {
        return postponedDate;
    }

    public void setPostponedDate(Date postponedDate) {
        this.postponedDate = postponedDate;
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
}
