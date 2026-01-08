package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.UserService;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.mapper.ResponsibleBookingMapper;
import com.decarli.solriso_system.model.dto.request.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.request.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.ReservationEntity;
import com.decarli.solriso_system.model.exceptions.DateReservationException;
import com.decarli.solriso_system.model.exceptions.EntityNotFoundException;
import com.decarli.solriso_system.model.exceptions.RoomReservationException;
import com.decarli.solriso_system.utils.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired private ReservationRepository repository;
    @Autowired private UserService userService;
    @Autowired private ReservationMapper reservationMapper;
    @Autowired private ResponsibleBookingMapper responsibleBookingMapper;

    @Transactional
    @Override
    public void createReservation(ReservationCreateDto create) {

        logger.info("Criando uma nova reserva: ", create);

        validateReservationDates(create.getCheckin(), create.getCheckout());
        validateRoomViability(create.getRoom(), create.getCheckin(), create.getCheckout());

        if(create.getResponsible().getCpf() != null) {
            AppUtil.validateCPF(create.getResponsible().getCpf());
        }

        ReservationEntity entity = reservationMapper.toReservation(create);
        entity.setUserEntity(userService.getAdminByEmail(create.getAdminEmail()));
        log.info("{}", create.getPaid());
        repository.save(entity);
        log.info("Nova reserva salva");
    }

    private void validateReservationDates(LocalDate checkin, LocalDate checkout) {
        if(checkin == null || checkout == null) throw new IllegalArgumentException("A data de checkin e checkout não podem ser vazias");

        if(checkin.isAfter(checkout) || checkin.isEqual(checkout)) throw new DateReservationException("A data de checkin precisa ser antes da data de checkout");

        // if(checkin.isBefore(LocalDate.now())) throw new DateReservationException("Date of checkin can't be before today");

        logger.info("Validate dates successfully from checkin {} to checkout {} ", checkin, checkout);
    }

    private void validateRoomViability(int room, LocalDate checkin, LocalDate checkout) {
        List<ReservationEntity> reservationEntities = getReservationsBetween(checkin, checkout);

        for(ReservationEntity current : reservationEntities) {
            if(current.getRoom() == room) {
                throw new RoomReservationException("Esse quarto já está ocupado");
            }
        }

        logger.info("Validate successfully room {} viability from {} to {}", room, checkin, checkout);
    }

    @Override
    public ReservationEntity getReservationById(Long id) {
        if(id.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("O ID não pode ser vazio");
        }
        ReservationEntity reservationEntity = repository.findReservationById(id);
        if(reservationEntity == null) {
            throw new EntityNotFoundException("Reserva não encontrada");
        }

        logger.info("Found reservation {} by id {}", reservationEntity, id);
        return reservationEntity;
    }

    @Override
    public List<ReservationEntity> getReservationsToday() {
        List<ReservationEntity> reservationEntities = repository.findReservationsToday(LocalDate.now());
        if(reservationEntities.isEmpty()) {
            logger.error("Fail to find reservations today");
            throw new EntityNotFoundException("Não há nenhuma reserva hoje");
        }

        logger.info("Found reservations today");
        return reservationEntities;
    }

    @Override
    public List<ReservationEntity> getReservationsByRoom(int room) {
        List<ReservationEntity> reservationEntities = repository.findReservationByRoom(room);
        if(reservationEntities.isEmpty()) {
            throw new EntityNotFoundException("Não há nenhuma reserva para esse quarto");
        }
        logger.info("Found reservations by room {}", room);
        return reservationEntities;
    }

    @Override
    public List<ReservationEntity> getReservationsByResponsibleName(String name) {
        List<ReservationEntity> reservationEntities = repository.findReservationByResponsibleName(name);
        if(reservationEntities.isEmpty()) {
            throw new EntityNotFoundException("Não há nenhuma reserva associada a esse hospede");
        }

        logger.info("Found reservations by responsible name {}", name);
        return reservationEntities;
    }

    @Override
    public List<ReservationEntity> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        logger.info("Looking for reservations from checkin {} to checkout {}", checkin, checkout);
        return repository.findReservationsBetween(checkin, checkout);
    }


    @Transactional
    @Override
    public ReservationEntity updateReservation(ReservationUpdateDto update) {
        ReservationEntity reservationEntity = getReservationById(update.getId());

        logger.info("Making update about reservation {}", reservationEntity);

        validateReservationDates(update.getCheckin(), update.getCheckout());
        validateRoomViability(update.getRoom(), update.getCheckin(), update.getCheckout());

        reservationEntity.setRoom(update.getRoom());
        reservationEntity.setQuantGuests(update.getQuantGuests());
        reservationEntity.setTypeReservation(update.getTypeReservation());
        reservationEntity.setStatus(update.getStatus());
        reservationEntity.setCheckin(update.getCheckin());
        reservationEntity.setCheckout(update.getCheckout());
        reservationEntity.setEntryValue(update.getEntryValue());
        reservationEntity.setTotalValue(update.getTotalValue());
        reservationEntity.setUserEntity(userService.getAdminByEmail(update.getAdminEmail()));
        reservationEntity.setResponsible(responsibleBookingMapper.toResponsibleBooking(update.getResponsible()));
        reservationEntity.setParkingEntity(update.getParkingEntity());

        logger.info("Finish update reservation to {}", reservationEntity);

        return repository.save(reservationEntity);
    }

    @Transactional
    public void deleteReservation(Long id) {

        if(id == null || id.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("O ID não pode ser null ou Vazio");
        }

        if(repository.findReservationById(id) == null) {
            throw new EntityNotFoundException("Reserva não encontrada");
        }

        repository.deleteById(id);
        logger.warn("Reservation with id {} deleted", id);
    }

    @Override
    public List<ReservationEntity> getAllReservations() {
        List<ReservationEntity> reservationEntities = repository.findAll();
        if(reservationEntities.isEmpty()) {
            throw new EntityNotFoundException("Não foi possível encontrar nenhuma reserva no sistema");
        }
        logger.info("Got all reservations");
        return reservationEntities;
    }
}
