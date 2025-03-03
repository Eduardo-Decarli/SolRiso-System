package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
