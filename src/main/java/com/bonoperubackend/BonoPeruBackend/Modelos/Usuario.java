package com.bonoperubackend.BonoPeruBackend.Modelos;

import org.apache.tomcat.util.security.Escape;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "USUARIO")
        })
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_USUARIO;

    @NotBlank
    @Size(max = 50)
    private String USUARIO;

    @NotBlank
    @Size(max = 120)
    private String CONTRASEÑA;

    @Size(max = 200)
    private String NOMBRES;

    @Size(max = 200)
    private String APELLIDOS;

    @Size(max = 3)
    private String ESTADO;

    private int USUARIO_CREACION;

    private int USUARIO_ACTUALIZACION;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "usuario_rol",
            joinColumns = @JoinColumn(name = "fid_usuario"),
            inverseJoinColumns = @JoinColumn(name = "fid_rol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String USUARIO, String CONTRASEÑA, String NOMBRES, String APELLIDOS, String ESTADO) {
        this.USUARIO = USUARIO;
        this.CONTRASEÑA = CONTRASEÑA;
        this.APELLIDOS = APELLIDOS;
        this.NOMBRES = NOMBRES;
        this.ESTADO = ESTADO;
    }

    public Integer getId() {
        return ID_USUARIO;
    }

    public void setId(Integer ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getUsername() {
        return USUARIO;
    }

    public void setUsername(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getPassword() {
        return CONTRASEÑA;
    }

    public void setPassword(String CONTRASEÑA) {
        this.CONTRASEÑA = CONTRASEÑA;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public int getUSUARIO_ACTUALIZACION() {
        return USUARIO_ACTUALIZACION;
    }

    public void setUSUARIO_ACTUALIZACION(int USUARIO_ACTUALIZACION) {
        this.USUARIO_ACTUALIZACION = USUARIO_ACTUALIZACION;
    }

    public int getUSUARIO_CREACION() {
        return USUARIO_CREACION;
    }

    public void setUSUARIO_CREACION(int USUARIO_CREACION) {
        this.USUARIO_CREACION = USUARIO_CREACION;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}