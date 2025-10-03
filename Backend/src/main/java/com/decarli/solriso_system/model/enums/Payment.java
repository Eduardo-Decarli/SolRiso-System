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

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
