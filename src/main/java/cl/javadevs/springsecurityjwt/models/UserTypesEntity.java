package cl.javadevs.springsecurityjwt.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_tipos_usuarios")
public class UserTypesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<UserEntity> users;

}
