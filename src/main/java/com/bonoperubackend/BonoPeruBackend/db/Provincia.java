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

@Entity(name="provincia")
public class Provincia implements Serializable {

    /** Primary key. */
    protected static final String PK = "idProvincia";
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_provincia", unique=true, nullable=false, precision=10)
    private int idProvincia;
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
    @OneToMany(mappedBy="provincia")
    private Set<Distrito> distrito;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_departamento", nullable=false)
    private Departamento departamento;

    /** Default constructor. */
    public Provincia() {
        super();
    }

    /**
     * Access method for idProvincia.
     *
     * @return the current value of idProvincia
     */
    public int getIdProvincia() {
        return idProvincia;
    }

    /**
     * Setter method for idProvincia.
     *
     * @param aIdProvincia the new value for idProvincia
     */
    public void setIdProvincia(int aIdProvincia) {
        idProvincia = aIdProvincia;
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
     * Access method for distrito.
     *
     * @return the current value of distrito
     */
    public Set<Distrito> getDistrito() {
        return distrito;
    }

    /**
     * Setter method for distrito.
     *
     * @param aDistrito the new value for distrito
     */
    public void setDistrito(Set<Distrito> aDistrito) {
        distrito = aDistrito;
    }

    /**
     * Access method for departamento.
     *
     * @return the current value of departamento
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Setter method for departamento.
     *
     * @param aDepartamento the new value for departamento
     */
    public void setDepartamento(Departamento aDepartamento) {
        departamento = aDepartamento;
    }

    /**
     * Compares the key for this instance with another Provincia.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Provincia and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Provincia)) {
            return false;
        }
        Provincia that = (Provincia) other;
        if (this.getIdProvincia() != that.getIdProvincia()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Provincia.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Provincia)) return false;
        return this.equalKeys(other) && ((Provincia)other).equalKeys(this);
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
        i = getIdProvincia();
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
        StringBuffer sb = new StringBuffer("[Provincia |");
        sb.append(" idProvincia=").append(getIdProvincia());
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
        ret.put("idProvincia", Integer.valueOf(getIdProvincia()));
        return ret;
    }

}
