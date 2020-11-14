package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name="respuestaindividual")
public class RespuestaIndividual implements Serializable {

    /** Primary key. */
    protected static final String PK = "idRespuestaindividual";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name="LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_respuestaindividual", unique=true, nullable=false, precision=10)
    private int idRespuestaindividual;
    @Column(length=500)
    private String respuesta;
    @Column(precision=12)
    private float puntaje;
    @Column(nullable=false, length=3)
    private String estado;
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_respuestaencuesta", nullable=false)
    private RespuestaEncuesta respuestaencuesta;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_pregunta", nullable=false)
    private Pregunta pregunta;

    /** Default constructor. */
    public RespuestaIndividual() {
        super();
    }

    /**
     * Access method for idRespuestaindividual.
     *
     * @return the current value of idRespuestaindividual
     */
    public int getIdRespuestaindividual() {
        return idRespuestaindividual;
    }

    /**
     * Setter method for idRespuestaindividual.
     *
     * @param aIdRespuestaindividual the new value for idRespuestaindividual
     */
    public void setIdRespuestaindividual(int aIdRespuestaindividual) {
        idRespuestaindividual = aIdRespuestaindividual;
    }

    /**
     * Access method for respuesta.
     *
     * @return the current value of respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Setter method for respuesta.
     *
     * @param aRespuesta the new value for respuesta
     */
    public void setRespuesta(String aRespuesta) {
        respuesta = aRespuesta;
    }

    /**
     * Access method for puntaje.
     *
     * @return the current value of puntaje
     */
    public float getPuntaje() {
        return puntaje;
    }

    /**
     * Setter method for puntaje.
     *
     * @param aPuntaje the new value for puntaje
     */
    public void setPuntaje(float aPuntaje) {
        puntaje = aPuntaje;
    }

    /**
     * Access method for estado.
     *
     * @return the current value of estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Setter method for estado.
     *
     * @param aEstado the new value for estado
     */
    public void setEstado(String aEstado) {
        estado = aEstado;
    }

    /**
     * Access method for fechaCreacion.
     *
     * @return the current value of fechaCreacion
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Setter method for fechaCreacion.
     *
     * @param aFechaCreacion the new value for fechaCreacion
     */
    public void setFechaCreacion(LocalDateTime aFechaCreacion) {
        fechaCreacion = aFechaCreacion;
    }

    /**
     * Access method for fechaActualizacion.
     *
     * @return the current value of fechaActualizacion
     */
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * Setter method for fechaActualizacion.
     *
     * @param aFechaActualizacion the new value for fechaActualizacion
     */
    public void setFechaActualizacion(LocalDateTime aFechaActualizacion) {
        fechaActualizacion = aFechaActualizacion;
    }

    /**
     * Access method for respuestaencuesta.
     *
     * @return the current value of respuestaencuesta
     */
    public RespuestaEncuesta getRespuestaencuesta() {
        return respuestaencuesta;
    }

    /**
     * Setter method for respuestaencuesta.
     *
     * @param aRespuestaencuesta the new value for respuestaencuesta
     */
    public void setRespuestaencuesta(RespuestaEncuesta aRespuestaencuesta) {
        respuestaencuesta = aRespuestaencuesta;
    }

    /**
     * Access method for pregunta.
     *
     * @return the current value of pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Setter method for pregunta.
     *
     * @param aPregunta the new value for pregunta
     */
    public void setPregunta(Pregunta aPregunta) {
        pregunta = aPregunta;
    }

    /**
     * Compares the key for this instance with another Respuestaindividual.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Respuestaindividual and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RespuestaIndividual)) {
            return false;
        }
        RespuestaIndividual that = (RespuestaIndividual) other;
        if (this.getIdRespuestaindividual() != that.getIdRespuestaindividual()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Respuestaindividual.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RespuestaIndividual)) return false;
        return this.equalKeys(other) && ((RespuestaIndividual)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getIdRespuestaindividual();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Respuestaindividual |");
        sb.append(" idRespuestaindividual=").append(getIdRespuestaindividual());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("idRespuestaindividual", Integer.valueOf(getIdRespuestaindividual()));
        return ret;
    }

}
