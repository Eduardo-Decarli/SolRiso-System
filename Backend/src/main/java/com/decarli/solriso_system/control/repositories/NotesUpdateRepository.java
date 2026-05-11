package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.NotesUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotesUpdateRepository extends JpaRepository<NotesUpdateEntity, Long> {
}
