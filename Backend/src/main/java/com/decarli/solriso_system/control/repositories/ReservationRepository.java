package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    @Query("{'checkin': {$eq: ?0}}")
    List<Reservation> findReservationByCheckin(LocalDate checkinAfter);
    List<Reservation> findReservationByRoom(int room);
    @Query("{'checkin': {'$lte': ?1}, 'checkout': {'$gte': ?0}}")
    List<Reservation> findReservationsBetween(LocalDate checkin, LocalDate checkout);
    List<Reservation> findReservationByResponsibleName(String responsibleName);

    Reservation findReservationById(String id);
}
