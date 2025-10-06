package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.entities.NotesUpdateEntity;

import java.util.List;

public interface NotesUpdateService {
    List<NotesUpdateEntity> findAllNotesUpdate();
}
