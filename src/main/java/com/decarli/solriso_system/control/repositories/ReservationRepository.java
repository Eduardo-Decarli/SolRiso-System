package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ReservationRepository extends MongoRepository<Reservation, UUID> {
}
