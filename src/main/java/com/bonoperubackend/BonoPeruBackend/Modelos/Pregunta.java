package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity(name="pregunta")
@Table(	name = "pregunta",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "idpregunta")
        })
public class Pregunta implements Serializable {

    /** Primary key. */
    protected static final String PK = "idPregunta";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idpregunta", unique=true, nullable=false, precision=10)
    private int idPregunta;
    @Column(nullable=false, length=500)
    private String pregunta;
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
//    @OneToMany(mappedBy="pregunta")
//    private Set<RespuestaIndividual> respuestaindividual;

    /** Default constructor. */
    public Pregunta() {
        super();
    }

    /**
     * Access method for idPregunta.
     *
     * @return the current value of idPregunta
     */
    public int getIdPregunta() {
        return idPregunta;
    }

    /**
     * Setter method for idPregunta.
     *
     * @param aIdPregunta the new value for idPregunta
     */
    public void setIdPregunta(int aIdPregunta) {
        idPregunta = aIdPregunta;
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
     * Access method for respuestaindividual.
     *
     * @return the current value of respuestaindividual
     */
//    public Set<RespuestaIndividual> getRespuestaindividual() {
//        return respuestaindividual;
//    }
//
//    /**
//     * Setter method for respuestaindividual.
//     *
//     * @param aRespuestaIndividual the new value for respuestaindividual
//     */
//    public void setRespuestaindividual(Set<RespuestaIndividual> aRespuestaIndividual) {
//        respuestaindividual = aRespuestaIndividual;
//    }

    /**
     * Compares the key for this instance with another Pregunta.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Pregunta and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Pregunta)) {
            return false;
        }
        Pregunta that = (Pregunta) other;
        if (this.getIdPregunta() != that.getIdPregunta()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Pregunta.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pregunta)) return false;
        return this.equalKeys(other) && ((Pregunta)other).equalKeys(this);
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
        i = getIdPregunta();
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
        StringBuffer sb = new StringBuffer("[Pregunta |");
        sb.append(" idPregunta=").append(getIdPregunta());
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
        ret.put("idPregunta", Integer.valueOf(getIdPregunta()));
        return ret;
    }

}
