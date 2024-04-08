package cl.javadevs.springsecurityjwt.services;

import cl.javadevs.springsecurityjwt.dtos.SuiteDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SuitesService {
    @Transactional
    Boolean add(SuiteDTO login);

    @Transactional
    Boolean delete(Integer id);

    @Transactional
    List<Object[]> moreReservaction();
}
