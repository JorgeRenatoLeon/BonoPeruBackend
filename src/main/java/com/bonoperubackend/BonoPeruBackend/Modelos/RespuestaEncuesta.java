package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="respuestaencuesta")
public class RespuestaEncuesta implements Serializable {

    /** Primary key. */
    protected static final String PK = "idRespuestaencuesta";

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
    @Column(name="id_respuestaencuesta", unique=true, nullable=false, precision=10)
    private int idRespuestaencuesta;
    @Column(nullable=false, length=3)
    private String estado;
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_beneficiario", nullable=false)
    private Beneficiario beneficiario;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_horario", nullable=false)
    private Horario horario;
    @OneToMany(mappedBy="respuestaencuesta")
    private Set<RespuestaIndividual> respuestaindividual;

    /** Default constructor. */
    public RespuestaEncuesta() {
        super();
    }

    /**
     * Access method for idRespuestaencuesta.
     *
     * @return the current value of idRespuestaencuesta
     */
    public int getIdRespuestaencuesta() {
        return idRespuestaencuesta;
    }

    /**
     * Setter method for idRespuestaencuesta.
     *
     * @param aIdRespuestaencuesta the new value for idRespuestaencuesta
     */
    public void setIdRespuestaencuesta(int aIdRespuestaencuesta) {
        idRespuestaencuesta = aIdRespuestaencuesta;
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
     * Access method for beneficiario.
     *
     * @return the current value of beneficiario
     */
    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    /**
     * Setter method for beneficiario.
     *
     * @param aBeneficiario the new value for beneficiario
     */
    public void setBeneficiario(Beneficiario aBeneficiario) {
        beneficiario = aBeneficiario;
    }

    /**
     * Access method for horario.
     *
     * @return the current value of horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Setter method for horario.
     *
     * @param aHorario the new value for horario
     */
    public void setHorario(Horario aHorario) {
        horario = aHorario;
    }

    /**
     * Access method for respuestaindividual.
     *
     * @return the current value of respuestaindividual
     */
    public Set<RespuestaIndividual> getRespuestaindividual() {
        return respuestaindividual;
    }

    /**
     * Setter method for respuestaindividual.
     *
     * @param aRespuestaIndividual the new value for respuestaindividual
     */
    public void setRespuestaindividual(Set<RespuestaIndividual> aRespuestaIndividual) {
        respuestaindividual = aRespuestaIndividual;
    }

    /**
     * Compares the key for this instance with another Respuestaencuesta.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Respuestaencuesta and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RespuestaEncuesta)) {
            return false;
        }
        RespuestaEncuesta that = (RespuestaEncuesta) other;
        if (this.getIdRespuestaencuesta() != that.getIdRespuestaencuesta()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Respuestaencuesta.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RespuestaEncuesta)) return false;
        return this.equalKeys(other) && ((RespuestaEncuesta)other).equalKeys(this);
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
        i = getIdRespuestaencuesta();
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
        StringBuffer sb = new StringBuffer("[Respuestaencuesta |");
        sb.append(" idRespuestaencuesta=").append(getIdRespuestaencuesta());
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
        ret.put("idRespuestaencuesta", Integer.valueOf(getIdRespuestaencuesta()));
        return ret;
    }

}
