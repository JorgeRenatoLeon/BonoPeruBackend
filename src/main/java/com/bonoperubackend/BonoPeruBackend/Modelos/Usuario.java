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
                @UniqueConstraint(columnNames = "usuario")
        })
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @NotBlank
    @Size(max = 50)
    private String usuario;

    @NotBlank
    @Size(max = 120)
    private String contrasena;

    @Size(max = 200)
    private String nombres;

    @Size(max = 200)
    private String apellidos;

    @Size(max = 200)
    private String correo;

    @Size(max = 3)
    private String estado;

    private int usuariocreacion;

    private int usuarioactualizacion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "usuariorol",
            joinColumns = @JoinColumn(name = "fidusuario"),
            inverseJoinColumns = @JoinColumn(name = "fidrol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena, String nombres, String apellidos, String estado) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.estado = estado;
    }

    public Integer getId() {
        return idusuario;
    }

    public void setId(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsername() {
        return usuario;
    }

    public void setUsername(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return contrasena;
    }

    public void setPassword(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getUsuarioActualizacion() {
        return usuarioactualizacion;
    }

    public void setUsuarioActualizacion(int usuarioactualizacion) {
        this.usuarioactualizacion = usuarioactualizacion;
    }

    public int getUsuarioCreacion() {
        return usuariocreacion;
    }

    public void setUsuarioCreacion(int usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}