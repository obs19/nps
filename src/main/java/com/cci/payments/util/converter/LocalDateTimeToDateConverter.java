package com.cci.payments.util.converter;

import org.dozer.CustomConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class LocalDateTimeToDateConverter implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }
        if (sourceFieldValue instanceof java.time.LocalDateTime) {
            LocalDateTime ldt = (LocalDateTime) sourceFieldValue;
            ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
            return Date.from(zdt.toInstant());
        }
        return null;
    }
}
