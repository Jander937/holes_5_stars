package cl.javadevs.springsecurityjwt.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DocumentTypesDTO {

    private Integer id;

    private String name;

    private String initials;
}
