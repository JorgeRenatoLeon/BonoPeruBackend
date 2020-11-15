// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.db;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name="preguntasfrecuentes")
public class Preguntasfrecuentes implements Serializable {

    /** Primary key. */
    protected static final String PK = "idPreguntasfrecuentes";

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
    @Column(name="id_preguntasfrecuentes", unique=true, nullable=false, precision=10)
    private int idPreguntasfrecuentes;
    @Column(nullable=false, length=500)
    private String pregunta;
    @Column(nullable=false, length=500)
    private String respuesta;
    @Column(nullable=false, length=3)
    private String estado;
    @Column(name="usuario_creacion", precision=10)
    private int usuarioCreacion;
    @Column(name="usuario_actualizacion", precision=10)
    private int usuarioActualizacion;
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    /** Default constructor. */
    public Preguntasfrecuentes() {
        super();
    }

    /**
     * Access method for idPreguntasfrecuentes.
     *
     * @return the current value of idPreguntasfrecuentes
     */
    public int getIdPreguntasfrecuentes() {
        return idPreguntasfrecuentes;
    }

    /**
     * Setter method for idPreguntasfrecuentes.
     *
     * @param aIdPreguntasfrecuentes the new value for idPreguntasfrecuentes
     */
    public void setIdPreguntasfrecuentes(int aIdPreguntasfrecuentes) {
        idPreguntasfrecuentes = aIdPreguntasfrecuentes;
    }

    /**
     * Access method for pregunta.
     *
     * @return the current value of pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Setter method for pregunta.
     *
     * @param aPregunta the new value for pregunta
     */
    public void setPregunta(String aPregunta) {
        pregunta = aPregunta;
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
     * Access method for usuarioCreacion.
     *
     * @return the current value of usuarioCreacion
     */
    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    /**
     * Setter method for usuarioCreacion.
     *
     * @param aUsuarioCreacion the new value for usuarioCreacion
     */
    public void setUsuarioCreacion(int aUsuarioCreacion) {
        usuarioCreacion = aUsuarioCreacion;
    }

    /**
     * Access method for usuarioActualizacion.
     *
     * @return the current value of usuarioActualizacion
     */
    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    /**
     * Setter method for usuarioActualizacion.
     *
     * @param aUsuarioActualizacion the new value for usuarioActualizacion
     */
    public void setUsuarioActualizacion(int aUsuarioActualizacion) {
        usuarioActualizacion = aUsuarioActualizacion;
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
     * Compares the key for this instance with another Preguntasfrecuentes.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Preguntasfrecuentes and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Preguntasfrecuentes)) {
            return false;
        }
        Preguntasfrecuentes that = (Preguntasfrecuentes) other;
        if (this.getIdPreguntasfrecuentes() != that.getIdPreguntasfrecuentes()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Preguntasfrecuentes.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Preguntasfrecuentes)) return false;
        return this.equalKeys(other) && ((Preguntasfrecuentes)other).equalKeys(this);
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
        i = getIdPreguntasfrecuentes();
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
        StringBuffer sb = new StringBuffer("[Preguntasfrecuentes |");
        sb.append(" idPreguntasfrecuentes=").append(getIdPreguntasfrecuentes());
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
        ret.put("idPreguntasfrecuentes", Integer.valueOf(getIdPreguntasfrecuentes()));
        return ret;
    }

}
