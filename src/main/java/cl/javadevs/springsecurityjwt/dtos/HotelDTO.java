package cl.javadevs.springsecurityjwt.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotelDTO {
    private Integer id;

    private String neighborhood;

    private String address;

    private String phones;

    private Integer cityId;
}
