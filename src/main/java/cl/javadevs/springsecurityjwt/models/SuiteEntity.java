package cl.javadevs.springsecurityjwt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_habitaciones")
public class SuiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "numero")
    private Integer number;

    @Column(name = "banio_privado")
    private Boolean privateBathroom;

    @Column(name = "telefono")
    private Boolean phone;

    @Column(name = "calefaccion")
    private Boolean heating;

    @Column(name = "estado")
    private String status;

    @ManyToOne
    @JoinColumn(name = "tbl_sedes_hotel_id")
    private HotelEntity hotelsEntity;

    @ManyToOne
    @JoinColumn(name = "tbl_tipos_habitaciones_id")
    private SuiteTypesEntity suiteTypesEntity;
}
