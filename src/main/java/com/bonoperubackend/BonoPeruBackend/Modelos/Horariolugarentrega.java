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

@Entity(name="horariolugarentrega")
@Table(name = "horariolugarentrega")
public class Horariolugarentrega implements Serializable {

    /** Primary key. */
    protected static final String PK = "idHorariolugarentrega";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idhorariolugarentrega", unique=true, nullable=false, precision=10)
    private int idHorariolugarentrega;
    @Column(name="horaaperturaturnoma", nullable=false)
    private LocalTime horaaperturaturnoma;
    @Column(name="horacierreturnoma", nullable=false)
    private LocalTime horacierreturnoma;
    @Column(name="horaaperturaturnotar", nullable=false)
    private LocalTime horaaperturaturnotar;
    @Column(name="horacierreturnotar", nullable=false)
    private LocalTime horacierreturnotar;
    @Column(nullable=false, length=30)
    private String dia;
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
    private Integer diabinario;
//    @OneToMany(mappedBy="horariolugarentrega")
//    private Set<Horario> horario;
    @ManyToOne(optional=false)
    @JoinColumn(name="fidlugarentrega", nullable=false)
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

    public LocalTime getHoraaperturaturnoma() {
        return horaaperturaturnoma;
    }

    public void setHoraaperturaturnoma(LocalTime horaaperturaturnoma) {
        this.horaaperturaturnoma = horaaperturaturnoma;
    }

    public LocalTime getHoracierreturnoma() {
        return horacierreturnoma;
    }

    public void setHoracierreturnoma(LocalTime horacierreturnoma) {
        this.horacierreturnoma = horacierreturnoma;
    }

    public LocalTime getHoraaperturaturnotar() {
        return horaaperturaturnotar;
    }

    public void setHoraaperturaturnotar(LocalTime horaaperturaturnotar) {
        this.horaaperturaturnotar = horaaperturaturnotar;
    }

    public LocalTime getHoracierreturnotar() {
        return horacierreturnotar;
    }

    public void setHoracierreturnotar(LocalTime horacierreturnotar) {
        this.horacierreturnotar = horacierreturnotar;
    }


    /**
     * Access method for dia.
     *
     * @return the current value of dia
     */

    public String getDia() {
        return dia;
    }
    /**
     * Setter method for dia.
     *
     * @param aDia the new value for dia
     */
    public void setDia(String aDia) {
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

    public Integer getDiabinario() {
        return diabinario;
    }

    public void setDiabinario(Integer diabinario) {
        this.diabinario = diabinario;
    }
}
