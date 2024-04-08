package cl.javadevs.springsecurityjwt.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SuiteTypesDTO {

    private Integer id;

    private String name;

    private String description;
}
