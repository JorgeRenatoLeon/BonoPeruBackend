package com.bonoperubackend.BonoPeruBackend.Modelos;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "preguntasfrecuentes")
public class PreguntasFrecuentes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_PREGUNTASFRECUENTES;
    @NotBlank
    @Size(max = 500)
    private String PREGUNTA;
    @NotBlank
    @Size(max = 500)
    private String RESPUESTA;
    @NotBlank
    @Size(max = 3)
    private String ESTADO;
    private int USUARIO_CREACION;
    private int USUARIO_ACTUALIZACION;

    public PreguntasFrecuentes(Integer ID_PREGUNTASFRECUENTES, @NotBlank @Size(max = 500) String PREGUNTA, @NotBlank @Size(max = 500) String RESPUESTA, @NotBlank @Size(max = 3) String ESTADO) {
        this.ID_PREGUNTASFRECUENTES = ID_PREGUNTASFRECUENTES;
        this.PREGUNTA = PREGUNTA;
        this.RESPUESTA = RESPUESTA;
        this.ESTADO = ESTADO;
    }

    public PreguntasFrecuentes(){}

    public Integer getID_PREGUNTASFRECUENTES() {
        return ID_PREGUNTASFRECUENTES;
    }

    public void setID_PREGUNTASFRECUENTES(Integer ID_PREGUNTASFRECUENTES) {
        this.ID_PREGUNTASFRECUENTES = ID_PREGUNTASFRECUENTES;
    }

    public String getPREGUNTA() {
        return PREGUNTA;
    }

    public void setPREGUNTA(String PREGUNTA) {
        this.PREGUNTA = PREGUNTA;
    }

    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public int getUSUARIO_CREACION() {
        return USUARIO_CREACION;
    }

    public void setUSUARIO_CREACION(int USUARIO_CREACION) {
        this.USUARIO_CREACION = USUARIO_CREACION;
    }

    public int getUSUARIO_ACTUALIZACION() {
        return USUARIO_ACTUALIZACION;
    }

    public void setUSUARIO_ACTUALIZACION(int USUARIO_ACTUALIZACION) {
        this.USUARIO_ACTUALIZACION = USUARIO_ACTUALIZACION;
    }
}
