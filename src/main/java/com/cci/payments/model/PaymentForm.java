package com.cci.payments.model;

import com.cci.payments.model.dictionaries.DictCountryRegion;
import com.cci.payments.model.dictionaries.DictPaymentAction;
import com.cci.payments.model.dictionaries.DictRegisterName;
import com.cci.payments.model.dictionaries.DictTypeOfPayment;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class PaymentForm extends LongIdObject implements DeletableMarker  {

    @NotBlank
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_itn")
    private String clientITN;
    @ManyToOne
    @JoinColumn(name = "ref_dict_type_of_payment_id", referencedColumnName = "id")
    private DictTypeOfPayment dictTypeOfPayment;
    @ManyToOne
    @JoinColumn(name = "ref_dict_register_name_id", referencedColumnName = "id")
    private DictRegisterName dictRegisterName;
    @ManyToOne
    @JoinColumn(name = "ref_dict_payment_action_id", referencedColumnName = "id")
    private DictPaymentAction dictPaymentAction;
    @ManyToOne
    @JoinColumn(name = "ref_dict_country_region_id", referencedColumnName = "id")
    private DictCountryRegion dictCountryRegion;
    @Column(name = "custom_payee_details")
    private String customPayeeDetails;
    @NotBlank
    @Column(name = "recipient")
    private String recipient;
    @Column(name="amount", nullable = false)
    private BigDecimal amount;
    @Column(name="amount_total")
    private BigDecimal amountTotal;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public PaymentForm() {
    }

    //TODO delete toString()
    @Override
    public String toString() {
        return "PaymentForm{" +
                "clientName='" + clientName + '\'' +
                ", clientITN='" + clientITN + '\'' +
                ", dictTypeOfPayment=" + dictTypeOfPayment +
                ", dictRegisterName=" + dictRegisterName +
                ", dictPaymentAction=" + dictPaymentAction +
                ", dictCountryRegion=" + dictCountryRegion +
                ", customPayeeDetails='" + customPayeeDetails + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                ", amountTotal=" + amountTotal +
                ", deleted=" + deleted +
                '}';
    }

    public String getCustomPayeeDetails() {
        return customPayeeDetails;
    }

    public void setCustomPayeeDetails(String customPayeeDetails) {
        this.customPayeeDetails = customPayeeDetails;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean isDeleted() {
        return this.deleted;
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

    public DictTypeOfPayment getDictTypeOfPayment() {
        return dictTypeOfPayment;
    }

    public void setDictTypeOfPayment(DictTypeOfPayment dictTypeOfPayment) {
        this.dictTypeOfPayment = dictTypeOfPayment;
    }

    public DictRegisterName getDictRegisterName() {
        return dictRegisterName;
    }

    public void setDictRegisterName(DictRegisterName dictRegisterName) {
        this.dictRegisterName = dictRegisterName;
    }

    public DictPaymentAction getDictPaymentAction() {
        return dictPaymentAction;
    }

    public void setDictPaymentAction(DictPaymentAction dictPaymentAction) {
        this.dictPaymentAction = dictPaymentAction;
    }

    public DictCountryRegion getDictCountryRegion() {
        return dictCountryRegion;
    }

    public void setDictCountryRegion(DictCountryRegion dictCountryRegion) {
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
}
