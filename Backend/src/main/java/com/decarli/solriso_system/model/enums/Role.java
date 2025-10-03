package com.decarli.solriso_system.model.enums;

public enum Role {
    ROOT(1L, "Root"),
    ADMINISTRADOR(2L, "Administrador"),
    USUARIO(3L, "Usuario");

    private final Long id;
    private final String description;

    Role(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Role getRole(Long id) {
        for(Role role : Role.values()) {
            if(role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
