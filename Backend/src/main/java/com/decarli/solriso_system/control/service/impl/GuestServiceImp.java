package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.GuestRepository;
import com.decarli.solriso_system.control.service.GuestService;
import com.decarli.solriso_system.model.entities.GuestEntity;
import com.decarli.solriso_system.model.exceptions.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImp implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public GuestEntity findById(Long id) {
        return guestRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Não foi possível localizar o hóspede pelo id: " + id)
        );
    }

    @Override
    public GuestEntity findByCpf(String cpf) {
        return guestRepository.findByCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException("Não foi possível localizar o hóspede pelo CPF: " + cpf)
        );
    }

    public GuestEntity existByCpf(String cpf) {
        return guestRepository.existsByCpf(cpf);
    }

    @Override
    public void createGuest(GuestEntity guest) {
        if(guest == null) {
            throw new IllegalArgumentException("Não é possível registrar um hóspede null");
        }
        guestRepository.save(guest);
    }

    @Override
    public GuestEntity existByEmail(String email) {
        return guestRepository.existsByEmail(email);
    }

    @Override
    public List<GuestEntity> autocompleteResponsibleByName(String name) {
        List<GuestEntity> guests = guestRepository.autocompleteGuestsByName(name);
        if(guests.isEmpty()) {
           throw new EntityNotFoundException("Nenhum hospede foi encontrado com esse nome");
        }
        return guests;
    }
}
