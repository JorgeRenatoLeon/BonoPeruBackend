// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity(name="horario")
@Table(name = "horario")
public class Horario implements Serializable {

    /** Primary key. */
    protected static final String PK = "idHorario";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idhorario", unique=true, nullable=false, precision=10)
    private int idHorario;
    @Column(name="horainicio", nullable=false)
    private LocalTime horaInicio;
    @Column(name="horafin", nullable=false)
    private LocalTime horaFin;
    @Column(nullable=false)
    private LocalDate fecha;
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
    @ManyToOne(optional=false)
    @JoinColumn(name="fidcronograma", nullable=false)
    private Cronograma cronograma;
    @ManyToOne(optional=false)
    @JoinColumn(name="fidbeneficiario", nullable=false)
    private Beneficiario beneficiario;
    @ManyToOne(optional=false)
    @JoinColumn(name="fidhorariolugarentrega", nullable=false)
    private Horariolugarentrega horariolugarentrega;
//    @OneToMany(mappedBy="horario")
//    private Set<Quejas> quejas;
//    @OneToMany(mappedBy="horario")
//    private Set<RespuestaEncuesta> respuestaencuesta;

    /** Default constructor. */
    public Horario() {
        super();
    }

    /**
     * Access method for idHorario.
     *
     * @return the current value of idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * Setter method for idHorario.
     *
     * @param aIdHorario the new value for idHorario
     */
    public void setIdHorario(int aIdHorario) {
        idHorario = aIdHorario;
    }

    /**
     * Access method for horaInicio.
     *
     * @return the current value of horaInicio
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Setter method for horaInicio.
     *
     * @param aHoraInicio the new value for horaInicio
     */
    public void setHoraInicio(LocalTime aHoraInicio) {
        horaInicio = aHoraInicio;
    }

    /**
     * Access method for horaFin.
     *
     * @return the current value of horaFin
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Setter method for horaFin.
     *
     * @param aHoraFin the new value for horaFin
     */
    public void setHoraFin(LocalTime aHoraFin) {
        horaFin = aHoraFin;
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
     * Access method for cronograma.
     *
     * @return the current value of cronograma
     */
    public Cronograma getCronograma() {
        return cronograma;
    }

    /**
     * Setter method for cronograma.
     *
     * @param aCronograma the new value for cronograma
     */
    public void setCronograma(Cronograma aCronograma) {
        cronograma = aCronograma;
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
     * Access method for horariolugarentrega.
     *
     * @return the current value of horariolugarentrega
     */
    public Horariolugarentrega getHorariolugarentrega() {
        return horariolugarentrega;
    }

    /**
     * Setter method for horariolugarentrega.
     *
     * @param aHorariolugarentrega the new value for horariolugarentrega
     */
    public void setHorariolugarentrega(Horariolugarentrega aHorariolugarentrega) {
        horariolugarentrega = aHorariolugarentrega;
    }

    /**
     * Access method for quejas.
     *
     * @return the current value of quejas
     */
//    public Set<Quejas> getQuejas() {
//        return quejas;
//    }
//
//    /**
//     * Setter method for quejas.
//     *
//     * @param aQuejas the new value for quejas
//     */
//    public void setQuejas(Set<Quejas> aQuejas) {
//        quejas = aQuejas;
//    }
//
//    /**
//     * Access method for respuestaencuesta.
//     *
//     * @return the current value of respuestaencuesta
//     */
//    public Set<RespuestaEncuesta> getRespuestaencuesta() {
//        return respuestaencuesta;
//    }
//
//    /**
//     * Setter method for respuestaencuesta.
//     *
//     * @param aRespuestaencuesta the new value for respuestaencuesta
//     */
//    public void setRespuestaencuesta(Set<RespuestaEncuesta> aRespuestaencuesta) {
//        respuestaencuesta = aRespuestaencuesta;
//    }

    /**
     * Compares the key for this instance with another Horario.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Horario and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Horario)) {
            return false;
        }
        Horario that = (Horario) other;
        if (this.getIdHorario() != that.getIdHorario()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Horario.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Horario)) return false;
        return this.equalKeys(other) && ((Horario)other).equalKeys(this);
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
        i = getIdHorario();
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
        StringBuffer sb = new StringBuffer("[Horario |");
        sb.append(" idHorario=").append(getIdHorario());
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
        ret.put("idHorario", Integer.valueOf(getIdHorario()));
        return ret;
    }

}
