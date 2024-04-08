package cl.javadevs.springsecurityjwt.dtos;

import cl.javadevs.springsecurityjwt.models.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Getter
@Setter
@ToString
public class UserTypesDTO {

    private Integer id;

    private String name;

    private List<UserEntity> users;
}
