package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.response.NotesUpdateRespondeDto;
import com.decarli.solriso_system.model.entities.NotesUpdateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotesUpdateMapper {

    NotesUpdateRespondeDto toDto(NotesUpdateEntity notesUpdateEntity);
    List<NotesUpdateRespondeDto> toListDto(List<NotesUpdateEntity> notesUpdateEntity);

}
