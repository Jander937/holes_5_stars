package cl.javadevs.springsecurityjwt.services.Impl;

import cl.javadevs.springsecurityjwt.controllers.ReservationController;
import cl.javadevs.springsecurityjwt.dtos.ReservationDTO;
import cl.javadevs.springsecurityjwt.dtos.SuiteDTO;
import cl.javadevs.springsecurityjwt.dtos.UserDTO;
import cl.javadevs.springsecurityjwt.models.ReservationEntity;
import cl.javadevs.springsecurityjwt.models.SuiteEntity;
import cl.javadevs.springsecurityjwt.repositories.IReservationRepository;
import cl.javadevs.springsecurityjwt.repositories.ISuiteRepository;
import cl.javadevs.springsecurityjwt.services.ReservationService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private IReservationRepository reservationsRepository;

    @Autowired
    private ISuiteRepository suitesRepository;

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Transactional
    @Override
    public List<ReservationDTO> getAll() {

        List<ReservationDTO> result = new ArrayList<>();

        List<ReservationEntity> reservationsEntities = reservationsRepository.findAll();

        if (reservationsEntities.isEmpty())
            return result;

        for (ReservationEntity dato : reservationsEntities) {
            ReservationDTO reservation = new ReservationDTO();
            // ... set other fields

            SuiteDTO suitesDTO = new SuiteDTO();
            SuiteEntity suiteEntity = dato.getSuitesEntity();

            // Check if suiteEntity is not null before accessing its methods
            if (suiteEntity != null) {
                suitesDTO.setHotelId(suiteEntity.getId());
                suitesDTO.setStatus(suiteEntity.getStatus());
                // Check if the suiteTypesEntity is not null before trying to access it
                if (suiteEntity.getSuiteTypesEntity() != null) {
                    suitesDTO.setSuiteTypesId(suiteEntity.getSuiteTypesEntity().getId());
                } else {
                    // Handle the null case appropriately, e.g., by logging a warning or setting a default value
                    log.warn("SuiteTypesEntity is null for SuiteEntity id: {}", suiteEntity.getId());
                    suitesDTO.setSuiteTypesId(null); // or some default value
                }
                // ... set other SuiteEntity fields

                reservation.setSuite(suitesDTO);
                // ... set UserDTO fields

                result.add(reservation);
            } else {
                log.warn("SuiteEntity is null for ReservationEntity id: {}", dato.getId());
            }
        }

        return result;
    }

}

