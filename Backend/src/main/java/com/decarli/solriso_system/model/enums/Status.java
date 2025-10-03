package com.decarli.solriso_system.model.enums;

public enum Status {
    ATIVO(1L, "Ativo"),
    NAO_COMPARECEU(2L, "Não Compareceu"),
    CANCELADO(3L, "Cancelado");
    
    private final Long id;
    private final String description;

    Status(Long id, String description) {
        this.id = id;
        this.description = description;
    }
    
    public Long getid() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
