package cl.javadevs.springsecurityjwt.repositories;

import cl.javadevs.springsecurityjwt.models.SuiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISuiteRepository extends JpaRepository<SuiteEntity, Integer> {
    @Query("SELECT COUNT(h.suiteTypesEntity.id) AS totalHabitaciones, th.id AS codigo, th.name AS tipoHabitacion " +
            "FROM SuiteEntity h " +
            "JOIN h.suiteTypesEntity th " +
            "WHERE h.status = 'RESERVADA' " +
            "GROUP BY th.id, th.name " +
            "ORDER BY COUNT(h.suiteTypesEntity.id)  DESC")
    List<Object[]> findTypeRoomMostReserved();

}
