package com.bonoperubackend.BonoPeruBackend.Modelos;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "preguntasfrecuentes")
public class PreguntasFrecuentes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_preguntasfrecuentes;
    @NotBlank
    @Size(max = 500)
    private String pregunta;
    @NotBlank
    @Size(max = 500)
    private String respuesta;
    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuario_creacion;

    private Integer usuario_actualizacion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

}
