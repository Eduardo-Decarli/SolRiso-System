package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    @Query("{'checkin': {$eq: ?0}}")
    List<Reservation> findReservationByCheckin(LocalDate checkinAfter);
    List<Reservation> findReservationByRoomNumber(int roomNumber);
    List<Reservation> findReservationByCheckinAfterAndCheckoutBefore(LocalDate checkin, LocalDate checkout);
    List<Reservation> findReservationByResponsibleName(String responsibleName);
}
