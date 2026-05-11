package com.decarli.solriso_system.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentConverter implements AttributeConverter<Payment, Long> {

    @Override
    public Long convertToDatabaseColumn(Payment payment) {
        return payment == null ? null : payment.getId();
    }

    @Override
    public Payment convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : Payment.fromId(dbData);
    }
}
