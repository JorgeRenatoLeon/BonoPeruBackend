package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "beneficiario")
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_beneficiario;

    @NotBlank
    private Integer fid_distrito;

    @Size(max = 10)
    private String codigo_familia;

    @Size(max = 1)
    private String genero;

    @NotBlank
    private Boolean es_discapacitado;

    private float puntuacion;

    private float penalidad;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuario_creacion;

    private Integer usuario_actualizacion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;



}
