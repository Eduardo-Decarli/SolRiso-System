package com.decarli.solriso_system.model.enums;

public enum Payment {
    DINHEIRO(1L, "Dinheiro"),
    DEBITO(2L, "Débito"),
    CREDITO(3L, "Crédito"),
    PIX(4L, "PIX");

    private final Long id;
    private final String description;

    Payment(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Payment fromId(Long id) {
        for (Payment p : values()) {
            if (p.getId().equals(id)) return p;
        }
        throw new IllegalArgumentException("Invalid Payment id: " + id);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
