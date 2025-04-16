package com.decarli.solriso_system.model.enums;

public enum Role {
    ADMIN("Admin"),
    USER("User");

    private final String description;


    Role(String description) {
        this.description = description;
    }

    public String getDescription(Role role) {
        return this.description;
    }
}
