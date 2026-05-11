package com.decarli.solriso_system.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Long> {

    @Override
    public Long convertToDatabaseColumn(Role role) {
        return role.getId();
    }

    @Override
    public Role convertToEntityAttribute(Long id) {
        for(Role role : Role.values()) {
            if(role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }
}
