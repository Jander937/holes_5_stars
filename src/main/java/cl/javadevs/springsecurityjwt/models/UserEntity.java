package cl.javadevs.springsecurityjwt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "documento")
    private Long document;

    @Column(name = "nombres")
    private String names;

    @Column(name = "primer_apellido")
    private String surname;

    @Column(name = "segundo_apellido")
    private String secondSurname;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;


    @Column(name = "correo")
    private String email;

    @Column(name = "clave")
    private String password;


    @Column(name = "estado", columnDefinition = "TINYINT")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "tbl_tipos_documento_id")
    private DocumentTypesEntity documentTypesEntity;

    @ManyToOne
    @JoinColumn(name = "tbl_ciudades_codigo")
    private CitiesEntity citiesEntity;


    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tbl_tipos_usuarios", referencedColumnName = "id"))
    private List<UserTypesEntity> roles;

    public void initializeRoles() {
        roles.size(); // This triggers the loading of the roles collection
    }
// Now you can safely work with the roles collection
}
