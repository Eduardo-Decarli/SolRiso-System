package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.entities.GuestEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface GuestService {
    GuestEntity findById(Long id);

    GuestEntity findByCpf(String cpf);
    GuestEntity existByCpf(String cpf);

    void createGuest(GuestEntity guest);
    List<GuestEntity> autocompleteResponsibleByName(String name);

    GuestEntity existByEmail(String email);
}
