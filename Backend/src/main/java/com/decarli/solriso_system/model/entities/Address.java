package com.decarli.solriso_system.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
}
