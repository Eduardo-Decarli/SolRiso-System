package com.decarli.solriso_system.model.enums;

public enum Role {
    ADMIN("Administrador"),
    USER("Usuário");

    private final String description;


    Role(String description) {
        this.description = description;
    }

    public String getDescription(Role role) {
        return description;
    }
}
