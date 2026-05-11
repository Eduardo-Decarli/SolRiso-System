package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.NotesUpdateService;
import com.decarli.solriso_system.model.dto.mapper.NotesUpdateMapper;
import com.decarli.solriso_system.model.dto.response.NotesUpdateRespondeDto;
import com.decarli.solriso_system.model.entities.NotesUpdateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesUpdateController {

    @Autowired private NotesUpdateService notesUpdateService;
    @Autowired private NotesUpdateMapper  notesUpdateMapper;

    @GetMapping
    public ResponseEntity<List<NotesUpdateRespondeDto>> findAllNotesUpdate() {
        List<NotesUpdateEntity> notesUpdateEntities = notesUpdateService.findAllNotesUpdate();
        return ResponseEntity.ok().body(notesUpdateMapper.toListDto(notesUpdateEntities));
    }
}
