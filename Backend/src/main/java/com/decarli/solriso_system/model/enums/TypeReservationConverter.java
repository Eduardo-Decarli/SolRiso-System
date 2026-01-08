package com.decarli.solriso_system.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeReservationConverter implements AttributeConverter<TypeReservation, Long> {

    @Override
    public Long convertToDatabaseColumn(TypeReservation typeReservation) {
        return typeReservation.getId();
    }

    @Override
    public TypeReservation convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : TypeReservation.fromId(dbData);
    }
}