// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity(name="cronograma")
@Table(name = "cronograma")
public class Cronograma implements Serializable {

    /** Primary key. */
    protected static final String PK = "idCronograma";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idcronograma", unique=true, nullable=false, precision=10)
    private Integer idCronograma;
    @Column(length=100)
    private String nombre;
    @Column(name="fechainicio", nullable=false)
    private LocalDate fechaInicio;
    @Column(name="fechafin", nullable=false)
    private LocalDate fechaFin;
    @Column(nullable=false, length=3)
    private String estado;
    @Column(name="usuariocreacion", precision=10)
    private Integer usuarioCreacion;
    @Column(name="usuarioactualizacion", precision=10)
    private Integer usuarioActualizacion;
    @Column(name="fechacreacion")
    private LocalDateTime fechaCreacion;
    @Column(name="fechaactualizacion")
    private LocalDateTime fechaActualizacion;
//    @OneToMany(mappedBy="cronograma")
//    private Set<Horario> horario;

    /** Default constructor. */
    public Cronograma() {
        super();
    }

    /**
     * Access method for idCronograma.
     *
     * @return the current value of idCronograma
     */
    public Integer getIdCronograma() {
        return idCronograma;
    }

    /**
     * Setter method for idCronograma.
     *
     * @param aIdCronograma the new value for idCronograma
     */
    public void setIdCronograma(Integer aIdCronograma) {
        idCronograma = aIdCronograma;
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
     * Access method for fechaInicio.
     *
     * @return the current value of fechaInicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Setter method for fechaInicio.
     *
     * @param aFechaInicio the new value for fechaInicio
     */
    public void setFechaInicio(LocalDate aFechaInicio) {
        fechaInicio = aFechaInicio;
    }

    /**
     * Access method for fechaFin.
     *
     * @return the current value of fechaFin
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Setter method for fechaFin.
     *
     * @param aFechaFin the new value for fechaFin
     */
    public void setFechaFin(LocalDate aFechaFin) {
        fechaFin = aFechaFin;
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
     * Access method for horario.
     *
     * @return the current value of horario
     */
//    public Set<Horario> getHorario() {
//        return horario;
//    }
//
//    /**
//     * Setter method for horario.
//     *
//     * @param aHorario the new value for horario
//     */
//    public void setHorario(Set<Horario> aHorario) {
//        horario = aHorario;
//    }

    /**
     * Compares the key for this instance with another Cronograma.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Cronograma and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Cronograma)) {
            return false;
        }
        Cronograma that = (Cronograma) other;
        if (this.getIdCronograma() != that.getIdCronograma()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Cronograma.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cronograma)) return false;
        return this.equalKeys(other) && ((Cronograma)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        Integer i;
        Integer result = 17;
        i = getIdCronograma();
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
        StringBuffer sb = new StringBuffer("[Cronograma |");
        sb.append(" idCronograma=").append(getIdCronograma());
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
        ret.put("idCronograma", Integer.valueOf(getIdCronograma()));
        return ret;
    }

}
