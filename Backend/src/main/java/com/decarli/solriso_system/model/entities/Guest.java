package com.decarli.solriso_system.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "GUESTS")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GUEST")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "CPF", unique = true, nullable = false)
    private String cpf;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADDRESS",  nullable = false)
    private Address address;
}
