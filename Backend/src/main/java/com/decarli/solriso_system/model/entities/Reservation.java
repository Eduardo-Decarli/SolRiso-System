package com.decarli.solriso_system.model.entities;

import com.decarli.solriso_system.model.enums.Payment;
import com.decarli.solriso_system.model.enums.Status;
import com.decarli.solriso_system.model.enums.TypeReservation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_reservation")
    private UUID id;
    @Column(nullable = false)
    private int room;
    @Column(nullable = false)
    private int quantGuests;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private TypeReservation typeReservation;
    @Column(nullable = false)
    private LocalDate checkin;
    @Column(nullable = false)
    private LocalDate checkout;

    @Column(nullable = false)
    private Payment payment;
    @Column(nullable = false)
    private double entryValue;
    @Column(nullable = false)
    private double totalValue;
    @Column(nullable = false)
    private Boolean paid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_guest")
    private Guest responsible;
    @Embedded
    private Parking parking;
}
