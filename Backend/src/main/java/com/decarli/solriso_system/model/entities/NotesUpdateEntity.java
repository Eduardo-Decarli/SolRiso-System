package com.decarli.solriso_system.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "HISTORIC")
public class NotesUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORIC")
    private Long id;

    @Column(name = "VERSION", nullable = false, unique = true)
    private String version;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
