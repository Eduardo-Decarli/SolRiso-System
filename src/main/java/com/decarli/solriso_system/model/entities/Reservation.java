package com.decarli.solriso_system.model.entities;

import com.decarli.solriso_system.model.enums.Status;
import com.decarli.solriso_system.model.enums.TypeReservation;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "reservations")
public class Reservation {

    @Id
    private String id;
    private int roomNumber;
    private int quantGuests;
    private Status status;
    private TypeReservation typeReservation;
    private LocalDate checkin;
    private LocalDate checkout;
    private double entryValue;
    private double totalValue;

    private ResponsibleBooking responsible;
    private Parking parking;
}
