package cl.javadevs.springsecurityjwt.dtos;

import cl.javadevs.springsecurityjwt.models.ReservationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class ObservationDTO {
    private Integer id;

    private Integer type;

    private String observation;

    private Timestamp date;

    private ReservationEntity reservationsEntity;
}
