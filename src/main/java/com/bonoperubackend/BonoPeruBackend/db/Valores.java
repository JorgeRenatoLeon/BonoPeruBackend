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

@Entity(name="valores")
public class Valores implements Serializable {

    /** Primary key. */
    protected static final String PK = "idValores";

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
    @Column(name="id_valores", unique=true, nullable=false, precision=10)
    private int idValores;
    @Column(nullable=false, length=100)
    private String tabla;
    @Column(nullable=false, length=3)
    private String abreviatura;
    @Column(nullable=false, length=100)
    private String nombre;
    @Column(length=500)
    private String descripcion;
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    /** Default constructor. */
    public Valores() {
        super();
    }

    /**
     * Access method for idValores.
     *
     * @return the current value of idValores
     */
    public int getIdValores() {
        return idValores;
    }

    /**
     * Setter method for idValores.
     *
     * @param aIdValores the new value for idValores
     */
    public void setIdValores(int aIdValores) {
        idValores = aIdValores;
    }

    /**
     * Access method for tabla.
     *
     * @return the current value of tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * Setter method for tabla.
     *
     * @param aTabla the new value for tabla
     */
    public void setTabla(String aTabla) {
        tabla = aTabla;
    }

    /**
     * Access method for abreviatura.
     *
     * @return the current value of abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Setter method for abreviatura.
     *
     * @param aAbreviatura the new value for abreviatura
     */
    public void setAbreviatura(String aAbreviatura) {
        abreviatura = aAbreviatura;
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
     * Compares the key for this instance with another Valores.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Valores and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Valores)) {
            return false;
        }
        Valores that = (Valores) other;
        if (this.getIdValores() != that.getIdValores()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Valores.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Valores)) return false;
        return this.equalKeys(other) && ((Valores)other).equalKeys(this);
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
        i = getIdValores();
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
        StringBuffer sb = new StringBuffer("[Valores |");
        sb.append(" idValores=").append(getIdValores());
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
        ret.put("idValores", Integer.valueOf(getIdValores()));
        return ret;
    }

}
