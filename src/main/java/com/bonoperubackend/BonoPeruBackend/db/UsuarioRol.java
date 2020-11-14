// Generated with g9.

package com.bonoperubackend.BonoPeruBackend.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity(name="usuario_rol")
public class UsuarioRol implements Serializable {

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

    @ManyToOne(optional=false)
    @JoinColumn(name="fid_rol", nullable=false)
    private Rol rol;
    @OneToOne(optional=false, mappedBy="usuarioRol")
    @JoinColumn(name="fid_usuario", nullable=false)
    private Usuario usuario;

    /** Default constructor. */
    public UsuarioRol() {
        super();
    }

    /**
     * Access method for rol.
     *
     * @return the current value of rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Setter method for rol.
     *
     * @param aRol the new value for rol
     */
    public void setRol(Rol aRol) {
        rol = aRol;
    }

    /**
     * Access method for usuario.
     *
     * @return the current value of usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Setter method for usuario.
     *
     * @param aUsuario the new value for usuario
     */
    public void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }

}
