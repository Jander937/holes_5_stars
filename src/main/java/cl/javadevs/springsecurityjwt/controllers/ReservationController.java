package cl.javadevs.springsecurityjwt.controllers;

import cl.javadevs.springsecurityjwt.dtos.ReservationDTO;
import cl.javadevs.springsecurityjwt.repositories.ISuiteRepository;
import cl.javadevs.springsecurityjwt.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    private ReservationService reservationsService;

    @Autowired
    private ISuiteRepository suitesRepository;

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    // ... Autowired services

    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDTO>> all() {
        List<ReservationDTO> result = reservationsService.getAll();
        log.debug("Returning reservations with count: {}", result.size());

        if (!result.isEmpty()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.noContent().build();
    }
    //rf6

}
