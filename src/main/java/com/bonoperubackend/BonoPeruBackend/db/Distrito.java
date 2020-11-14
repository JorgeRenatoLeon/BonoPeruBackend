// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.db;

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

@Entity(name="distrito")
public class Distrito implements Serializable {

    /** Primary key. */
    protected static final String PK = "idDistrito";

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
    @Column(name="id_distrito", unique=true, nullable=false, precision=10)
    private int idDistrito;
    @Column(nullable=false, length=100)
    private String nombre;
    @Column(name="zona_riesgo", nullable=false, precision=10)
    private int zonaRiesgo;
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
    @OneToMany(mappedBy="distrito")
    private Set<Beneficiario> beneficiario;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_provincia", nullable=false)
    private Provincia provincia;
    @OneToMany(mappedBy="distrito")
    private Set<Lugarentrega> lugarentrega;

    /** Default constructor. */
    public Distrito() {
        super();
    }

    /**
     * Access method for idDistrito.
     *
     * @return the current value of idDistrito
     */
    public int getIdDistrito() {
        return idDistrito;
    }

    /**
     * Setter method for idDistrito.
     *
     * @param aIdDistrito the new value for idDistrito
     */
    public void setIdDistrito(int aIdDistrito) {
        idDistrito = aIdDistrito;
    }

    /**
     * Access method for nombre.
     *
     * @return the current value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter method for nombre.
     *
     * @param aNombre the new value for nombre
     */
    public void setNombre(String aNombre) {
        nombre = aNombre;
    }

    /**
     * Access method for zonaRiesgo.
     *
     * @return the current value of zonaRiesgo
     */
    public int getZonaRiesgo() {
        return zonaRiesgo;
    }

    /**
     * Setter method for zonaRiesgo.
     *
     * @param aZonaRiesgo the new value for zonaRiesgo
     */
    public void setZonaRiesgo(int aZonaRiesgo) {
        zonaRiesgo = aZonaRiesgo;
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
     * Access method for beneficiario.
     *
     * @return the current value of beneficiario
     */
    public Set<Beneficiario> getBeneficiario() {
        return beneficiario;
    }

    /**
     * Setter method for beneficiario.
     *
     * @param aBeneficiario the new value for beneficiario
     */
    public void setBeneficiario(Set<Beneficiario> aBeneficiario) {
        beneficiario = aBeneficiario;
    }

    /**
     * Access method for provincia.
     *
     * @return the current value of provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Setter method for provincia.
     *
     * @param aProvincia the new value for provincia
     */
    public void setProvincia(Provincia aProvincia) {
        provincia = aProvincia;
    }

    /**
     * Access method for lugarentrega.
     *
     * @return the current value of lugarentrega
     */
    public Set<Lugarentrega> getLugarentrega() {
        return lugarentrega;
    }

    /**
     * Setter method for lugarentrega.
     *
     * @param aLugarentrega the new value for lugarentrega
     */
    public void setLugarentrega(Set<Lugarentrega> aLugarentrega) {
        lugarentrega = aLugarentrega;
    }

    /**
     * Compares the key for this instance with another Distrito.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Distrito and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Distrito)) {
            return false;
        }
        Distrito that = (Distrito) other;
        if (this.getIdDistrito() != that.getIdDistrito()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Distrito.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Distrito)) return false;
        return this.equalKeys(other) && ((Distrito)other).equalKeys(this);
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
        i = getIdDistrito();
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
        StringBuffer sb = new StringBuffer("[Distrito |");
        sb.append(" idDistrito=").append(getIdDistrito());
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
        ret.put("idDistrito", Integer.valueOf(getIdDistrito()));
        return ret;
    }

}
