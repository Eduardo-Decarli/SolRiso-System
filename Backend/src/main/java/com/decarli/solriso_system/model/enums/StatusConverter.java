package com.decarli.solriso_system.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Long> {

    @Override
    public Long convertToDatabaseColumn(Status status) {
        return status.getId();
    }

    @Override
    public Status convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : Status.fromId(dbData);
    }
}