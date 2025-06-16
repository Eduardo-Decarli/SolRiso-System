package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    @Query("{'checkin': {$lte: ?0}, 'checkout':  {'$gt':  ?0}}")
    List<Reservation> findReservationsToday(LocalDate today);
    List<Reservation> findReservationByRoom(int room);
    @Query("{'checkin': {'$lte': ?1}, 'checkout': {'$gte': ?0}}")
    List<Reservation> findReservationsBetween(LocalDate checkin, LocalDate checkout);
    List<Reservation> findReservationByResponsibleName(String responsibleName);

    Reservation findReservationById(String id);
}
