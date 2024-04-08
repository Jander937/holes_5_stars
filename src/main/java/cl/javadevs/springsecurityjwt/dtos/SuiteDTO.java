package cl.javadevs.springsecurityjwt.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SuiteDTO {
    private Integer id;

    private Integer number;

    private Boolean privateBathroom;

    private Boolean phone;

    private Boolean heating;

    private String status;

    private Integer hotelId;

    private Integer suiteTypesId;
}
