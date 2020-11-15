// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDate;
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

@Entity(name="incidente")
public class Incidente implements Serializable {

    /** Primary key. */
    protected static final String PK = "idIncidente";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_incidente", unique=true, nullable=false, precision=10)
    private int idIncidente;
    @Column(nullable=false)
    private LocalDate fecha;
    @Column(name="tipo_incidente", nullable=false, length=30)
    private String tipoIncidente;
    @Column(nullable=false, length=3)
    private String estado;
    @Column(name="usuario_creacion", precision=10)
    private Integer usuarioCreacion;
    @Column(name="usuario_actualizacion", precision=10)
    private Integer usuarioActualizacion;
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_beneficiario", nullable=false)
    private Beneficiario beneficiario;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_lugarentrega", nullable=false)
    private Lugarentrega lugarentrega;

    /** Default constructor. */
    public Incidente() {
        super();
    }

    /**
     * Access method for idIncidente.
     *
     * @return the current value of idIncidente
     */
    public int getIdIncidente() {
        return idIncidente;
    }

    /**
     * Setter method for idIncidente.
     *
     * @param aIdIncidente the new value for idIncidente
     */
    public void setIdIncidente(int aIdIncidente) {
        idIncidente = aIdIncidente;
    }

    /**
     * Access method for fecha.
     *
     * @return the current value of fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Setter method for fecha.
     *
     * @param aFecha the new value for fecha
     */
    public void setFecha(LocalDate aFecha) {
        fecha = aFecha;
    }

    /**
     * Access method for tipoIncidente.
     *
     * @return the current value of tipoIncidente
     */
    public String getTipoIncidente() {
        return tipoIncidente;
    }

    /**
     * Setter method for tipoIncidente.
     *
     * @param aTipoIncidente the new value for tipoIncidente
     */
    public void setTipoIncidente(String aTipoIncidente) {
        tipoIncidente = aTipoIncidente;
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
    public Integer getUsuarioCreacion() {
        return usuarioCreacion;
    }

    /**
     * Setter method for usuarioCreacion.
     *
     * @param aUsuarioCreacion the new value for usuarioCreacion
     */
    public void setUsuarioCreacion(Integer aUsuarioCreacion) {
        usuarioCreacion = aUsuarioCreacion;
    }

    /**
     * Access method for usuarioActualizacion.
     *
     * @return the current value of usuarioActualizacion
     */
    public Integer getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    /**
     * Setter method for usuarioActualizacion.
     *
     * @param aUsuarioActualizacion the new value for usuarioActualizacion
     */
    public void setUsuarioActualizacion(Integer aUsuarioActualizacion) {
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
     * Compares the key for this instance with another Incidente.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Incidente and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Incidente)) {
            return false;
        }
        Incidente that = (Incidente) other;
        if (this.getIdIncidente() != that.getIdIncidente()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Incidente.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Incidente)) return false;
        return this.equalKeys(other) && ((Incidente)other).equalKeys(this);
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
        i = getIdIncidente();
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
        StringBuffer sb = new StringBuffer("[Incidente |");
        sb.append(" idIncidente=").append(getIdIncidente());
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
        ret.put("idIncidente", Integer.valueOf(getIdIncidente()));
        return ret;
    }

}
