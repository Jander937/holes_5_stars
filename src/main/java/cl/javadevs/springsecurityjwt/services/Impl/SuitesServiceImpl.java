package cl.javadevs.springsecurityjwt.services.Impl;

import cl.javadevs.springsecurityjwt.dtos.SuiteDTO;
import cl.javadevs.springsecurityjwt.models.SuiteEntity;
import cl.javadevs.springsecurityjwt.repositories.ISuiteRepository;
import cl.javadevs.springsecurityjwt.services.SuitesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuitesServiceImpl implements SuitesService {
    private ISuiteRepository iSuiteRepository;

    @Autowired
    public SuitesServiceImpl(ISuiteRepository iSuiteRepository) {
        this.iSuiteRepository = iSuiteRepository;
    }

    @Transactional
    @Override
    public Boolean add(SuiteDTO login){

        SuiteEntity suite = new SuiteEntity();

        suite.setId((((int) iSuiteRepository.count()) +1));

        suite.setPhone(login.getPhone());
        suite.setHeating(login.getHeating());


        SuiteEntity result = iSuiteRepository.save(suite);

        return (result != null);
    }
    @Transactional
    @Override
    public Boolean delete(Integer id){
        // Verifica si el hotel fue eliminado correctamente
        Optional<SuiteEntity> deleteSuites = iSuiteRepository.findById(id);

        if(deleteSuites.isEmpty())
            return false;

        //TODO:Controlar la excepcion cuando no se puede eliminar la excepcion.

        // Elimina el hotel con el ID proporcionado
        iSuiteRepository.deleteById(id);

        return true;
    }
    @Transactional
    @Override
    public List<Object[]> moreReservaction(){
        return iSuiteRepository.findTypeRoomMostReserved();
    }
}
