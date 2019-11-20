package com.cci.payments.dto;

import java.math.BigDecimal;

public class PaymentFormInDTO implements DtoObject {

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
    private boolean addToBasket;

    public PaymentFormInDTO() {
    }

    @Override
    public String toString() {
        return "PaymentFormInDTO{" +
                "clientName='" + clientName + '\'' +
                ", clientITN='" + clientITN + '\'' +
                ", dictTypeOfPayment='" + dictTypeOfPayment + '\'' +
                ", dictRegisterName='" + dictRegisterName + '\'' +
                ", dictPaymentAction='" + dictPaymentAction + '\'' +
                ", dictCountryRegion='" + dictCountryRegion + '\'' +
                ", customPayeeDetails='" + customPayeeDetails + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                ", amountTotal=" + amountTotal +
                ", addToBasket=" + addToBasket +
                '}';
    }

    public String getCustomPayeeDetails() {
        return customPayeeDetails;
    }

    public void setCustomPayeeDetails(String customPayeeDetails) {
        this.customPayeeDetails = customPayeeDetails;
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

    public boolean isAddToBasket() {
        return addToBasket;
    }

    public void setAddToBasket(boolean addToBasket) {
        this.addToBasket = addToBasket;
    }
}
