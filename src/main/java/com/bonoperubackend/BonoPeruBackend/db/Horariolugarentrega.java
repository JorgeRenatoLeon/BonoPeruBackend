// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.db;

import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

@Entity(name="horariolugarentrega")
public class Horariolugarentrega implements Serializable {

    /** Primary key. */
    protected static final String PK = "idHorariolugarentrega";

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
    @Column(name="id_horariolugarentrega", unique=true, nullable=false, precision=10)
    private int idHorariolugarentrega;
    @Column(name="hora_apertura_turno1", nullable=false)
    private LocalTime horaAperturaTurno1;
    @Column(name="hora_cierre_turno1", nullable=false)
    private LocalTime horaCierreTurno1;
    @Column(name="hora_apertura_turno2", nullable=false)
    private LocalTime horaAperturaTurno2;
    @Column(name="hora_cierre_turno2", nullable=false)
    private LocalTime horaCierreTurno2;
    @Column(nullable=false)
    private LocalDate dia;
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
    @OneToMany(mappedBy="horariolugarentrega")
    private Set<Horario> horario;
    @ManyToOne(optional=false)
    @JoinColumn(name="fid_lugarentrega", nullable=false)
    private Lugarentrega lugarentrega;

    /** Default constructor. */
    public Horariolugarentrega() {
        super();
    }

    /**
     * Access method for idHorariolugarentrega.
     *
     * @return the current value of idHorariolugarentrega
     */
    public int getIdHorariolugarentrega() {
        return idHorariolugarentrega;
    }

    /**
     * Setter method for idHorariolugarentrega.
     *
     * @param aIdHorariolugarentrega the new value for idHorariolugarentrega
     */
    public void setIdHorariolugarentrega(int aIdHorariolugarentrega) {
        idHorariolugarentrega = aIdHorariolugarentrega;
    }

    /**
     * Access method for horaAperturaTurno1.
     *
     * @return the current value of horaAperturaTurno1
     */
    public LocalTime getHoraAperturaTurno1() {
        return horaAperturaTurno1;
    }

    /**
     * Setter method for horaAperturaTurno1.
     *
     * @param aHoraAperturaTurno1 the new value for horaAperturaTurno1
     */
    public void setHoraAperturaTurno1(LocalTime aHoraAperturaTurno1) {
        horaAperturaTurno1 = aHoraAperturaTurno1;
    }

    /**
     * Access method for horaCierreTurno1.
     *
     * @return the current value of horaCierreTurno1
     */
    public LocalTime getHoraCierreTurno1() {
        return horaCierreTurno1;
    }

    /**
     * Setter method for horaCierreTurno1.
     *
     * @param aHoraCierreTurno1 the new value for horaCierreTurno1
     */
    public void setHoraCierreTurno1(LocalTime aHoraCierreTurno1) {
        horaCierreTurno1 = aHoraCierreTurno1;
    }

    /**
     * Access method for horaAperturaTurno2.
     *
     * @return the current value of horaAperturaTurno2
     */
    public LocalTime getHoraAperturaTurno2() {
        return horaAperturaTurno2;
    }

    /**
     * Setter method for horaAperturaTurno2.
     *
     * @param aHoraAperturaTurno2 the new value for horaAperturaTurno2
     */
    public void setHoraAperturaTurno2(LocalTime aHoraAperturaTurno2) {
        horaAperturaTurno2 = aHoraAperturaTurno2;
    }

    /**
     * Access method for horaCierreTurno2.
     *
     * @return the current value of horaCierreTurno2
     */
    public LocalTime getHoraCierreTurno2() {
        return horaCierreTurno2;
    }

    /**
     * Setter method for horaCierreTurno2.
     *
     * @param aHoraCierreTurno2 the new value for horaCierreTurno2
     */
    public void setHoraCierreTurno2(LocalTime aHoraCierreTurno2) {
        horaCierreTurno2 = aHoraCierreTurno2;
    }

    /**
     * Access method for dia.
     *
     * @return the current value of dia
     */
    public LocalDate getDia() {
        return dia;
    }

    /**
     * Setter method for dia.
     *
     * @param aDia the new value for dia
     */
    public void setDia(LocalDate aDia) {
        dia = aDia;
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
     * Compares the key for this instance with another Horariolugarentrega.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Horariolugarentrega and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Horariolugarentrega)) {
            return false;
        }
        Horariolugarentrega that = (Horariolugarentrega) other;
        if (this.getIdHorariolugarentrega() != that.getIdHorariolugarentrega()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Horariolugarentrega.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Horariolugarentrega)) return false;
        return this.equalKeys(other) && ((Horariolugarentrega)other).equalKeys(this);
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
        i = getIdHorariolugarentrega();
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
        StringBuffer sb = new StringBuffer("[Horariolugarentrega |");
        sb.append(" idHorariolugarentrega=").append(getIdHorariolugarentrega());
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
        ret.put("idHorariolugarentrega", Integer.valueOf(getIdHorariolugarentrega()));
        return ret;
    }

}
