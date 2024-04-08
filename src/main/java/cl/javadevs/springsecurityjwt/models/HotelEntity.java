package cl.javadevs.springsecurityjwt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_sedes_hotel")
public class HotelEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "barrio")
    private String neighborhood;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefonos")
    private String phones;

    @ManyToOne
    @JoinColumn (name = "tbl_ciudades_codigo")
    private CitiesEntity citiesEntity;
}
