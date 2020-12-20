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
    private Integer idpreguntasfrecuentes;

    @NotBlank
    @Size(max = 500)
    private String pregunta;

    @NotBlank
    @Size(max = 500)
    private String respuesta;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuariocreacion;

    private Integer usuarioactualizacion;

    private Timestamp fechacreacion;

    private Timestamp fechaactualizacion;

    public PreguntasFrecuentes(@NotBlank @Size(max = 500) String pregunta, @NotBlank @Size(max = 500) String respuesta, @NotBlank @Size(max = 3) String estado, Integer usuariocreacion) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
    }

    public PreguntasFrecuentes(){}

    public Integer getIdpreguntasfrecuentes() {
        return idpreguntasfrecuentes;
    }

    public void setIdpreguntasfrecuentes(Integer idpreguntasfrecuentes) {
        this.idpreguntasfrecuentes = idpreguntasfrecuentes;
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

    public Integer getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(Integer usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Integer getUsuarioactualizacion() {
        return usuarioactualizacion;
    }

    public void setUsuarioactualizacion(Integer usuarioactualizacion) {
        this.usuarioactualizacion = usuarioactualizacion;
    }

    public Timestamp getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Timestamp fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Timestamp getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Timestamp fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }
}
