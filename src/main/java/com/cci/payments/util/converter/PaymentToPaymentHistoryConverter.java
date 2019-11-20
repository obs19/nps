package com.cci.payments.util.converter;

import com.cci.payments.dto.PaymentHistoryDTO;
import com.cci.payments.model.Dictionary;
import com.cci.payments.model.Payment;
import com.cci.payments.model.PaymentForm;
import com.cci.payments.services.DictionaryService;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class PaymentToPaymentHistoryConverter implements CustomConverter {
    private final DictionaryService dictionaryService;

    @Autowired
    public PaymentToPaymentHistoryConverter(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public Object convert(Object dest, Object src, Class<?> destinationClass, Class<?> sourceClass) {
        if (src == null) {
            return null;
        }
        if (src instanceof com.cci.payments.model.Payment){
            Payment payment = (Payment) src;
            PaymentHistoryDTO dto = new PaymentHistoryDTO();
            dto.setId(payment.getId());
            dto.setClientName(payment.getForm().getClientName());
            dto.setCreationDate(localDateTimeToDate(payment.getCreationDate()));
            dto.setCompletedDate(localDateTimeToDate(payment.getCompletedDate()));
            dto.setAmount(payment.getForm().getAmount());
            dto.setStatus(payment.getStatus().getTranslation());
            dto.setRecipientFullName(createFullRecipientName(payment.getForm()));
            return dto;
        }
        throw new MappingException("PaymentToPaymentHistoryConverter exception during mapping src = " + src);
    }


    private Date localDateTimeToDate(LocalDateTime ldt){
        if (ldt == null){
            return null;
        }
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    private String createFullRecipientName(PaymentForm form){
        StringBuilder sb = new StringBuilder();
        sb.append(form.getDictTypeOfPayment().getName())
                .append("; ");
        if (form.getDictRegisterName() != null) {
            sb.append(form.getDictRegisterName().getName())
                .append("; ");
        }
        if (form.getDictPaymentAction() != null) {
            sb.append(form.getDictPaymentAction().getName())
                    .append("; ");
        }
        if (form.getDictCountryRegion() != null) {
            sb.append(form.getDictCountryRegion().getName())
                    .append("; ");
        }
        if (form.getCustomPayeeDetails() != null) {
            sb.append(form.getCustomPayeeDetails())
                    .append("; ");
        }
        sb.append(form.getRecipient());
        return sb.toString();
    }
}
