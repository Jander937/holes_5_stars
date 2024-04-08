package cl.javadevs.springsecurityjwt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_reservas")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "fecha_ingreso")
    private Date checkInDate;

    @Column(name = "fecha_salida")
    private Date checkOutDate;

    @Column(name = "estado")
    private String status;

    @OneToOne
    @JoinColumn(name = "tbl_habitaciones_id")
    private SuiteEntity suitesEntity;

    @OneToOne
    @JoinColumn(name = "tbl_usuarios_id")
    private UserEntity usersEntity;

}
