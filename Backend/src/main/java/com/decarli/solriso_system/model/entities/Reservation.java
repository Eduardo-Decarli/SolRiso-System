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
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVATION")
    private Long id;
    @Column(name = "ROOM", nullable = false)
    private Integer room;
    @Column(name = "QUANT_GUESTS", nullable = false)
    private Integer quantGuests;
    @Column(name = "CHECKIN", nullable = false)
    private LocalDate checkin;
    @Column(name = "CHECKOUT", nullable = false)
    private LocalDate checkout;
    @Column(name = "ENTRY_VALUE", nullable = false)
    private Double entryValue;
    @Column(name = "TOTAL_VALUE", nullable = false)
    private Double totalValue;
    @Column(name = "PAID", nullable = false)
    private Boolean paid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GUEST", nullable = false)
    private Guest responsible;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_PAYMENT_TYPE", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_TYPE_RESERVATION", nullable = false)
    private TypeReservation typeReservation;

    @Column(name = "ID_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PARKING", nullable = false)
    private Parking parking;
}
