package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_departamento;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    private Integer zona_riesgo;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuario_creacion;

    @Null
    private Integer usuario_actualizacion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

}
