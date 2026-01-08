package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "SELECT * FROM RESERVATIONS r WHERE r.checkin = :today", nativeQuery = true)
    List<ReservationEntity> findReservationsToday(@Param("today") LocalDate today);
    List<ReservationEntity> findReservationByRoom(int room);

    @Query(value = "SELECT * FROM RESERVATIONS r WHERE r.checkin <= r.checkout", nativeQuery = true)
    List<ReservationEntity> findReservationsBetween(LocalDate checkin, LocalDate checkout);
    List<ReservationEntity> findReservationByResponsibleName(String responsibleName);
    ReservationEntity findReservationById(Long id);
}
