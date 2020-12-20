// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name="lugarentrega")
@Table(name = "lugarentrega")
public class Lugarentrega implements Serializable {

    /** Primary key. */
    protected static final String PK = "idLugarentrega";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idlugarentrega", unique=true, nullable=false, precision=10)
    private int idLugarentrega;
    @Column(nullable=false, length=200)
    private String direccion;
    @Column(nullable=false, length=10)
    private String codigo;
    @Column(nullable=false, length=200)
    private String nombre;
    @Column(name="ratioatencion", precision=12)
    private float ratioAtencion;
    @Column(nullable=false, precision=10)
    private int aforo;
    @Column(nullable=false, length=3)
    private String tipo;
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
//    @OneToMany(mappedBy="lugarentrega")
//    private Set<Horariolugarentrega> horariolugarentrega;
//    @OneToMany(mappedBy="lugarentrega")
//    private Set<Incidente> incidente;
    @ManyToOne(optional=false)
    @JoinColumn(name="fiddistrito", nullable=false)
    private Distrito distrito;
//    @OneToMany(mappedBy="lugarentrega")
//    private Set<Quejas> quejas;

    /** Default constructor. */
    public Lugarentrega() {
        super();
    }

    public Lugarentrega( String nombre, String codigo, String direccion,Float ratioAtencion, Integer aforo, String tipo, Integer usuarioCreacion, @NotBlank @Size(max = 3) String estado, Distrito distrito) {
        this.direccion = direccion;
        this.estado = estado;
        this.nombre = nombre;
        this.codigo = codigo;
        this.ratioAtencion = ratioAtencion;
        this.aforo = aforo;
        this.tipo = tipo;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.distrito = distrito;
    }
    /**
     * Access method for idLugarentrega.
     *
     * @return the current value of idLugarentrega
     */
    public int getIdLugarentrega() {
        return idLugarentrega;
    }

    /**
     * Setter method for idLugarentrega.
     *
     * @param aIdLugarentrega the new value for idLugarentrega
     */
    public void setIdLugarentrega(int aIdLugarentrega) {
        idLugarentrega = aIdLugarentrega;
    }

    /**
     * Access method for direccion.
     *
     * @return the current value of direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Setter method for direccion.
     *
     * @param aDireccion the new value for direccion
     */
    public void setDireccion(String aDireccion) {
        direccion = aDireccion;
    }

    /**
     * Access method for codigo.
     *
     * @return the current value of codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Setter method for codigo.
     *
     * @param aCodigo the new value for codigo
     */
    public void setCodigo(String aCodigo) {
        codigo = aCodigo;
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
     * Access method for ratioAtencion.
     *
     * @return the current value of ratioAtencion
     */
    public float getRatioAtencion() {
        return ratioAtencion;
    }

    /**
     * Setter method for ratioAtencion.
     *
     * @param aRatioAtencion the new value for ratioAtencion
     */
    public void setRatioAtencion(float aRatioAtencion) {
        ratioAtencion = aRatioAtencion;
    }

    /**
     * Access method for aforo.
     *
     * @return the current value of aforo
     */
    public int getAforo() {
        return aforo;
    }

    /**
     * Setter method for aforo.
     *
     * @param aAforo the new value for aforo
     */
    public void setAforo(int aAforo) {
        aforo = aAforo;
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
     * Access method for horariolugarentrega.
     *
     * @return the current value of horariolugarentrega
     */
//    public Set<Horariolugarentrega> getHorariolugarentrega() {
//        return horariolugarentrega;
//    }
//
//    /**
//     * Setter method for horariolugarentrega.
//     *
//     * @param aHorariolugarentrega the new value for horariolugarentrega
//     */
//    public void setHorariolugarentrega(Set<Horariolugarentrega> aHorariolugarentrega) {
//        horariolugarentrega = aHorariolugarentrega;
//    }

    /**
     * Access method for incidente.
     *
     * @return the current value of incidente
     */
//    public Set<Incidente> getIncidente() {
//        return incidente;
//    }
//
//    /**
//     * Setter method for incidente.
//     *
//     * @param aIncidente the new value for incidente
//     */
//    public void setIncidente(Set<Incidente> aIncidente) {
//        incidente = aIncidente;
//    }

    /**
     * Access method for distrito.
     *
     * @return the current value of distrito
     */
    public Distrito getDistrito() {
        return distrito;
    }

    /**
     * Setter method for distrito.
     *
     * @param aDistrito the new value for distrito
     */
    public void setDistrito(Distrito aDistrito) {
        distrito = aDistrito;
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

    /**
     * Compares the key for this instance with another Lugarentrega.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Lugarentrega and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Lugarentrega)) {
            return false;
        }
        Lugarentrega that = (Lugarentrega) other;
        if (this.getIdLugarentrega() != that.getIdLugarentrega()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Lugarentrega.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Lugarentrega)) return false;
        return this.equalKeys(other) && ((Lugarentrega)other).equalKeys(this);
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
        i = getIdLugarentrega();
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
        StringBuffer sb = new StringBuffer("[Lugarentrega |");
        sb.append(" idLugarentrega=").append(getIdLugarentrega());
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
        ret.put("idLugarentrega", Integer.valueOf(getIdLugarentrega()));
        return ret;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
