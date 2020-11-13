package com.bonoperubackend.BonoPeruBackend.Modelos;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
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

    @Null
    private Integer usuario_actualizacion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

    public PreguntasFrecuentes(@NotBlank @Size(max = 500) String pregunta, @NotBlank @Size(max = 500) String respuesta, @NotBlank @Size(max = 3) String estado, Integer usuario_creacion) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
    }

    public PreguntasFrecuentes(){}

    public Integer getId_preguntasfrecuentes() {
        return id_preguntasfrecuentes;
    }

    public void setId_preguntasfrecuentes(Integer id_preguntasfrecuentes) {
        this.id_preguntasfrecuentes = id_preguntasfrecuentes;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(Integer usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public Integer getUsuario_actualizacion() {
        return usuario_actualizacion;
    }

    public void setUsuario_actualizacion(Integer usuario_actualizacion) {
        this.usuario_actualizacion = usuario_actualizacion;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Timestamp getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Timestamp fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
