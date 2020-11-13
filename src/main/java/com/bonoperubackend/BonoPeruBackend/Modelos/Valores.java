package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "valores")
public class Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_valores;

    @NotBlank
    @Size(max = 100)
    private String tabla;

    @NotBlank
    @Size(max = 3)
    private String abreviatura;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

    public Valores(@NotBlank @Size(max = 100) String tabla, @NotBlank @Size(max = 3) String abreviatura, @NotBlank @Size(max = 100) String nombre, @Size(max = 500) String descripcion) {
        this.tabla = tabla;
        this.abreviatura = abreviatura;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Valores(){}

    public Integer getId_valores() {
        return id_valores;
    }

    public void setId_valores(Integer id_valores) {
        this.id_valores = id_valores;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Timestamp getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Timestamp fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
