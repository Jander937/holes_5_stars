package cl.javadevs.springsecurityjwt.repositories;

import cl.javadevs.springsecurityjwt.models.HotelEntity;
import cl.javadevs.springsecurityjwt.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelsRepository extends JpaRepository<HotelEntity, Integer> {
}
