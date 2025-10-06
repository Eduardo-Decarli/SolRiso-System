package com.decarli.solriso_system.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "STATE")
    private String state;
    @Column(name = "CITY")
    private String city;
    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;
    @Column(name = "STREET")
    private String street;
    @Column(name = "NUMBER")
    private String number;
}
