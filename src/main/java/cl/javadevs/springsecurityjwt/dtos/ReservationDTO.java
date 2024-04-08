package cl.javadevs.springsecurityjwt.dtos;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO {
    private Integer id;

    private Date checkInDate;

    private Date checkOutDate;

    private String status;

    private SuiteDTO suite;

    private UserDTO user;

}
