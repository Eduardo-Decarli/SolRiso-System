package com.decarli.solriso_system.model.enums;

public enum TypeReservation {
    TELEFONE(1L, "Telefone"),
    EMAIL(2L, "Email"),
    BOOKING(3L, "Booking"),
    AIRBNB(4L, "Airbnb"),
    EXPEDIA(5L, "Expedia");
    
    private final Long id;
    private final String description;

    TypeReservation(Long id, String description) {
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
