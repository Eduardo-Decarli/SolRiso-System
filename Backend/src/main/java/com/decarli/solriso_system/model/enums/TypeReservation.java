package com.decarli.solriso_system.model.enums;

public enum TypeReservation {
    TELEFONE(1L, "Telefone"),
    BOOKING(2L, "Booking"),
    EXPEDIA(3L, "Expedia"),
    PRESENCIAL(3L, "Presencial"),
    AIRBNB(4L, "Airbnb"),
    EMAIL(5L, "Email");

    private final Long id;
    private final String description;

    TypeReservation(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TypeReservation fromId(long id) {
        for (TypeReservation typeReservation : values()) {
            if (typeReservation.getId().equals(id)) return typeReservation;
        }
        throw new IllegalArgumentException("Invalid Type Reservation id: " + id);
    }

    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
}
