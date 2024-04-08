package cl.javadevs.springsecurityjwt.repositories;

import cl.javadevs.springsecurityjwt.models.SuiteTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISuiteTypesRepository extends JpaRepository<SuiteTypesEntity, Long> {
}
