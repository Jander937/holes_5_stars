package cl.javadevs.springsecurityjwt.dtos;

import cl.javadevs.springsecurityjwt.models.CitiesEntity;
import cl.javadevs.springsecurityjwt.models.DocumentTypesEntity;
import cl.javadevs.springsecurityjwt.models.UserTypesEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO {
    private Integer id;

    private Long document;

    private String names;

    private String surname;

    private String secondSurname;

    private String address;

    private String phone;

    private String email;

    private String password;

    private String status;

    private CitiesEntity citiesEntity;

    private DocumentTypesEntity documentTypesEntity;

    private List<UserTypesEntity> roles = new ArrayList<>();
}
