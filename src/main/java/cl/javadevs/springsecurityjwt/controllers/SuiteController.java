package cl.javadevs.springsecurityjwt.controllers;

import cl.javadevs.springsecurityjwt.dtos.SuiteDTO;
import cl.javadevs.springsecurityjwt.services.Impl.SuitesServiceImpl;
import cl.javadevs.springsecurityjwt.services.SuitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SuiteController {
    private final SuitesService suitesService;

    public SuiteController(SuitesService suitesService) {
        this.suitesService = suitesService;
    }

    // Petición para crear un celular
    @PostMapping("crear")
    public ResponseEntity<?> save(@RequestBody SuiteDTO smart) {

        Boolean result = suitesService.add(smart);

        if (result) {
            return ResponseEntity.ok(smart);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping(value = "/suites/{id}")
    public Optional<ResponseEntity<?>> delete(@PathVariable int id){

        Boolean result = suitesService.delete(id);

        if(result){
            return Optional.of(ResponseEntity.noContent().build());
        }
        return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    @GetMapping("/moreReserved")
    public ResponseEntity<?>  moreReservaction (){
        List<Object[]> result = suitesService.moreReservaction ();

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Mapear los resultados a un DTO o clase de respuesta según sea necesario
        // Aquí puedes procesar los datos obtenidos del query y devolverlos en el cuerpo de la respuesta
        // Por ejemplo, podrías crear un DTO para representar los datos de tipo de habitación más reservado
        // y mapear los datos del array de objetos a ese DTO.

        return ResponseEntity.ok().body(result);
    }

}
