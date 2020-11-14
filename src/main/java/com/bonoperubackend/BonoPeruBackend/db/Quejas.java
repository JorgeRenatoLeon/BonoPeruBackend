// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.db;

import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;

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

@Entity(name="quejas")
public class Quejas implements Serializable {

    /** Primary key. */
    protected static final String PK = "idQuejas";

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
    @Column(name="id_quejas", unique=true, nullable=false, precision=10)
    private int idQuejas;
    @Column(nullable=false, length=500)
    private String descripcion;
    @Column(name="tipo_queja", nullable=false, length=30)
    private String tipoQueja;
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
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_lugarentrega", nullable=false)
    private Lugarentrega lugarentrega;

    /** Default constructor. */
    public Quejas() {
        super();
    }

    /**
     * Access method for idQuejas.
     *
     * @return the current value of idQuejas
     */
    public int getIdQuejas() {
        return idQuejas;
    }

    /**
     * Setter method for idQuejas.
     *
     * @param aIdQuejas the new value for idQuejas
     */
    public void setIdQuejas(int aIdQuejas) {
        idQuejas = aIdQuejas;
    }

    /**
     * Access method for descripcion.
     *
     * @return the current value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter method for descripcion.
     *
     * @param aDescripcion the new value for descripcion
     */
    public void setDescripcion(String aDescripcion) {
        descripcion = aDescripcion;
    }

    /**
     * Access method for tipoQueja.
     *
     * @return the current value of tipoQueja
     */
    public String getTipoQueja() {
        return tipoQueja;
    }

    /**
     * Setter method for tipoQueja.
     *
     * @param aTipoQueja the new value for tipoQueja
     */
    public void setTipoQueja(String aTipoQueja) {
        tipoQueja = aTipoQueja;
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
     * Access method for lugarentrega.
     *
     * @return the current value of lugarentrega
     */
    public Lugarentrega getLugarentrega() {
        return lugarentrega;
    }

    /**
     * Setter method for lugarentrega.
     *
     * @param aLugarentrega the new value for lugarentrega
     */
    public void setLugarentrega(Lugarentrega aLugarentrega) {
        lugarentrega = aLugarentrega;
    }

    /**
     * Compares the key for this instance with another Quejas.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Quejas and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Quejas)) {
            return false;
        }
        Quejas that = (Quejas) other;
        if (this.getIdQuejas() != that.getIdQuejas()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Quejas.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Quejas)) return false;
        return this.equalKeys(other) && ((Quejas)other).equalKeys(this);
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
        i = getIdQuejas();
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
        StringBuffer sb = new StringBuffer("[Quejas |");
        sb.append(" idQuejas=").append(getIdQuejas());
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
        ret.put("idQuejas", Integer.valueOf(getIdQuejas()));
        return ret;
    }

}
