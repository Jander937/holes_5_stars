package cl.javadevs.springsecurityjwt.controllers;

import cl.javadevs.springsecurityjwt.dtos.HotelDTO;
import cl.javadevs.springsecurityjwt.services.HotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HotelsController {
    @Autowired
    private HotelsService hotelsService;


    @PostMapping("/hotels")
    public Optional<HotelDTO> save(@RequestBody HotelDTO hotelData) {

        Boolean result = hotelsService.add(hotelData);

        if (result){
            return Optional.of(hotelData);
        }

        return Optional.empty();

    }

    @DeleteMapping(value = "/hotels/{id}")
    public Optional<ResponseEntity<?>> delete(@PathVariable int id){

        Boolean result = hotelsService.delete(id);

        if(result){
            return Optional.of(ResponseEntity.noContent().build());
        }
        return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

}
