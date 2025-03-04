package com.decarli.solriso_system.model.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResponsibleBooking {
    @NotNull(message = "Responsible name can't be null")
    private String name;
    private String phoneNumber;
    @Email(message = "Insert a valid email")
    private String email;
    private String cpf;
    private Address address;
}
