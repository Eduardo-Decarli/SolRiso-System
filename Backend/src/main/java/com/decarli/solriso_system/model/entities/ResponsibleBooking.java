package com.decarli.solriso_system.model.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibleBooking {

    private String name;
    private String phoneNumber;
    private String email;
    private String cpf;
    private Address address;
}
