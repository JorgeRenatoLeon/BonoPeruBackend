package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "valores")
public class Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_valores;

    @NotBlank
    @Size(max = 100)
    private String tabla;

    @NotBlank
    @Size(max = 3)
    private String abreviatura;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

}
