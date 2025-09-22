package com.decarli.solriso_system.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_guest")
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String phoneNumber;
    private String email;
    private String cpf;
    @Embedded
    private Address address;
}
