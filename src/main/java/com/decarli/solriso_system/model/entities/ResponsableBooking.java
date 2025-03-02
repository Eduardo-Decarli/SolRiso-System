package com.decarli.solriso_system.model.entities;

import lombok.Data;

@Data
public class ResponsableBooking {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String cpf;
    private Address address;
}
