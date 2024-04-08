package cl.javadevs.springsecurityjwt.services.Impl;

import cl.javadevs.springsecurityjwt.dtos.HotelDTO;
import cl.javadevs.springsecurityjwt.models.CitiesEntity;
import cl.javadevs.springsecurityjwt.models.HotelEntity;
import cl.javadevs.springsecurityjwt.repositories.IHotelsRepository;
import cl.javadevs.springsecurityjwt.services.HotelsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelsServiceImpl implements HotelsService {
    @Autowired
    private IHotelsRepository hotelsRepository;

    @Transactional
    @Override
    //rf1
    public Boolean add(HotelDTO hotelData){

        HotelEntity hotelsEntity = new HotelEntity();

        hotelsEntity.setId(((int) hotelsRepository.count()) +1);

        hotelsEntity.setAddress(hotelData.getAddress());
        hotelsEntity.setPhones(hotelData.getPhones());
        hotelsEntity.setNeighborhood(hotelData.getNeighborhood());

        CitiesEntity citiesEntity = new CitiesEntity();

        citiesEntity.setId(hotelData.getCityId());
        hotelsEntity.setCitiesEntity(citiesEntity);



        HotelEntity result = hotelsRepository.save(hotelsEntity);

        return (result.getCitiesEntity().getName() != null);
    }

    //rf3
    @Transactional
    @Override
    public Boolean delete(Integer id){

        // Verifica si el hotel fue eliminado correctamente
        Optional<HotelEntity> deletedHotel = hotelsRepository.findById(id);

        if (deletedHotel.isEmpty())
            return false;

        // Elimina el hotel con el ID proporcionado
        hotelsRepository.deleteById(id);


        return true;
    }
}
