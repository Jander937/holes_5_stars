package cl.javadevs.springsecurityjwt.repositories;

import cl.javadevs.springsecurityjwt.models.UserTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserTypesRepository extends JpaRepository<UserTypesEntity, Long> {
    //Método para buscar un role por su nombre en nuestra base de datos
    Optional<UserTypesEntity> findByName(String name);
}
