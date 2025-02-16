package cl.javadevs.springsecurityjwt.repositories;

import cl.javadevs.springsecurityjwt.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    //Método para poder buscar un usuario mediante su nombre
    Optional<UserEntity> findByUsername(String username);

    //Método para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByUsername(String username);
}
