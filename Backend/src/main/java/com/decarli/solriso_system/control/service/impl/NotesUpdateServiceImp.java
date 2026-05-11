package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.NotesUpdateRepository;
import com.decarli.solriso_system.control.service.NotesUpdateService;
import com.decarli.solriso_system.model.entities.NotesUpdateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesUpdateServiceImp implements NotesUpdateService {

    @Autowired
    private NotesUpdateRepository notesUpdateRepository;

    @Override
    public List<NotesUpdateEntity> findAllNotesUpdate() {
        return notesUpdateRepository.findAll();
    }
}
