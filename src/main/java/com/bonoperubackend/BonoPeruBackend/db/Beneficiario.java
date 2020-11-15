// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.db;

import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Incidente;
import com.bonoperubackend.BonoPeruBackend.Modelos.Quejas;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;

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

@Entity(name="beneficiario")
public class Beneficiario implements Serializable {

    /** Primary key. */
    protected static final String PK = "idBeneficiario";

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
    @Column(name="id_beneficiario", unique=true, nullable=false, precision=10)
    private int idBeneficiario;
    @Column(name="codigo_familia", nullable=false, length=10)
    private String codigoFamilia;
    @Column(nullable=false, length=1)
    private String genero;
    @Column(name="es_discapacitado", nullable=false, length=3)
    private boolean esDiscapacitado;
    @Column(precision=12)
    private float puntuacion;
    @Column(precision=12)
    private float penalidad;
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
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_distrito", nullable=false)
    private Distrito distrito;
    @OneToMany(mappedBy="beneficiario")
    private Set<Horario> horario;
    @OneToMany(mappedBy="beneficiario")
    private Set<Incidente> incidente;
    @OneToMany(mappedBy="beneficiario")
    private Set<Quejas> quejas;
    @OneToMany(mappedBy="beneficiario")
    private Set<RespuestaEncuesta> respuestaencuesta;

    /** Default constructor. */
    public Beneficiario() {
        super();
    }

    /**
     * Access method for idBeneficiario.
     *
     * @return the current value of idBeneficiario
     */
    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * Setter method for idBeneficiario.
     *
     * @param aIdBeneficiario the new value for idBeneficiario
     */
    public void setIdBeneficiario(int aIdBeneficiario) {
        idBeneficiario = aIdBeneficiario;
    }

    /**
     * Access method for codigoFamilia.
     *
     * @return the current value of codigoFamilia
     */
    public String getCodigoFamilia() {
        return codigoFamilia;
    }

    /**
     * Setter method for codigoFamilia.
     *
     * @param aCodigoFamilia the new value for codigoFamilia
     */
    public void setCodigoFamilia(String aCodigoFamilia) {
        codigoFamilia = aCodigoFamilia;
    }

    /**
     * Access method for genero.
     *
     * @return the current value of genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Setter method for genero.
     *
     * @param aGenero the new value for genero
     */
    public void setGenero(String aGenero) {
        genero = aGenero;
    }

    /**
     * Access method for esDiscapacitado.
     *
     * @return true if and only if esDiscapacitado is currently true
     */
    public boolean getEsDiscapacitado() {
        return esDiscapacitado;
    }

    /**
     * Setter method for esDiscapacitado.
     *
     * @param aEsDiscapacitado the new value for esDiscapacitado
     */
    public void setEsDiscapacitado(boolean aEsDiscapacitado) {
        esDiscapacitado = aEsDiscapacitado;
    }

    /**
     * Access method for puntuacion.
     *
     * @return the current value of puntuacion
     */
    public float getPuntuacion() {
        return puntuacion;
    }

    /**
     * Setter method for puntuacion.
     *
     * @param aPuntuacion the new value for puntuacion
     */
    public void setPuntuacion(float aPuntuacion) {
        puntuacion = aPuntuacion;
    }

    /**
     * Access method for penalidad.
     *
     * @return the current value of penalidad
     */
    public float getPenalidad() {
        return penalidad;
    }

    /**
     * Setter method for penalidad.
     *
     * @param aPenalidad the new value for penalidad
     */
    public void setPenalidad(float aPenalidad) {
        penalidad = aPenalidad;
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
     * Access method for distrito.
     *
     * @return the current value of distrito
     */
    public Distrito getDistrito() {
        return distrito;
    }

    /**
     * Setter method for distrito.
     *
     * @param aDistrito the new value for distrito
     */
    public void setDistrito(Distrito aDistrito) {
        distrito = aDistrito;
    }

    /**
     * Access method for horario.
     *
     * @return the current value of horario
     */
    public Set<Horario> getHorario() {
        return horario;
    }

    /**
     * Setter method for horario.
     *
     * @param aHorario the new value for horario
     */
    public void setHorario(Set<Horario> aHorario) {
        horario = aHorario;
    }

    /**
     * Access method for incidente.
     *
     * @return the current value of incidente
     */
    public Set<Incidente> getIncidente() {
        return incidente;
    }

    /**
     * Setter method for incidente.
     *
     * @param aIncidente the new value for incidente
     */
    public void setIncidente(Set<Incidente> aIncidente) {
        incidente = aIncidente;
    }

    /**
     * Access method for quejas.
     *
     * @return the current value of quejas
     */
    public Set<Quejas> getQuejas() {
        return quejas;
    }

    /**
     * Setter method for quejas.
     *
     * @param aQuejas the new value for quejas
     */
    public void setQuejas(Set<Quejas> aQuejas) {
        quejas = aQuejas;
    }

    /**
     * Access method for respuestaencuesta.
     *
     * @return the current value of respuestaencuesta
     */
    public Set<RespuestaEncuesta> getRespuestaencuesta() {
        return respuestaencuesta;
    }

    /**
     * Setter method for respuestaencuesta.
     *
     * @param aRespuestaencuesta the new value for respuestaencuesta
     */
    public void setRespuestaencuesta(Set<RespuestaEncuesta> aRespuestaencuesta) {
        respuestaencuesta = aRespuestaencuesta;
    }

    /**
     * Compares the key for this instance with another Beneficiario.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Beneficiario and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Beneficiario)) {
            return false;
        }
        Beneficiario that = (Beneficiario) other;
        if (this.getIdBeneficiario() != that.getIdBeneficiario()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Beneficiario.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Beneficiario)) return false;
        return this.equalKeys(other) && ((Beneficiario)other).equalKeys(this);
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
        i = getIdBeneficiario();
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
        StringBuffer sb = new StringBuffer("[Beneficiario |");
        sb.append(" idBeneficiario=").append(getIdBeneficiario());
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
        ret.put("idBeneficiario", Integer.valueOf(getIdBeneficiario()));
        return ret;
    }

}
