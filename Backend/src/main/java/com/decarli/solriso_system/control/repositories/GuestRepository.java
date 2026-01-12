package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    @Query(value = "SELECT * FROM GUESTS g WHERE g.NAME LIKE CONCAT(:name, '%')", nativeQuery = true)
    List<GuestEntity> autocompleteGuestsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM GUESTS g WHERE g.CPF = :cpf", nativeQuery = true)
    Optional<GuestEntity> findByCpf(@Param("cpf") String cpf);
    @Query(value = "SELECT * FROM GUESTS g WHERE g.CPF = :cpf", nativeQuery = true)
    GuestEntity existsByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT * FROM GUESTS g WHERE g.ID = :id", nativeQuery = true)
    Optional<GuestEntity> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM GUESTS g WHERE g.EMAIL = :email", nativeQuery = true)
    GuestEntity existsByEmail(@Param("email") String email);
}
