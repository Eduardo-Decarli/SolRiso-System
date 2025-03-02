package com.decarli.solriso_system.model.entities;

import com.decarli.solriso_system.model.enums.TypeReservation;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Reservation {

    private UUID id;
    private int roomNumber;
    private int quantGuests;
    private TypeReservation typeReservation;
    private LocalDate checkin;
    private LocalDate checkout;
    private double entryValue;
    private double totalValue;

    private ResponsableBooking responsable;
    private Parking parking;


}
