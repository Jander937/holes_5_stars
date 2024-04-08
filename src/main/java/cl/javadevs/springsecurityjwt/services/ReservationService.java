package cl.javadevs.springsecurityjwt.services;

import cl.javadevs.springsecurityjwt.dtos.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAll();
}
