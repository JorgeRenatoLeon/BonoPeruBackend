package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "distrito")
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_DISTRITO;
    @NotBlank
    private int FID_PROVINCIA;
    @NotBlank
    @Size(max = 100)
    private String NOMBRE;
    @NotBlank
    private Integer ZONA_RIESGO;
    @NotBlank
    @Size(max = 3)
    private String ESTADO;
    private int USUARIO_CREACION;
    private int USUARIO_ACTUALIZACION;

    public Distrito(){}

}
