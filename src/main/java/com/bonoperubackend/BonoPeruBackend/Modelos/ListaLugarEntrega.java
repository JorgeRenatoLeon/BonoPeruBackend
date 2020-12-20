package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="listalugarentrega")
public class ListaLugarEntrega implements Serializable {
    @Id
    private Integer idlugarentrega;
    @Column(nullable=false, length=10)
    private String codigo;
    @Column(nullable=false, length=200)
    private String nombre;
    private String depprodis;
    private String tipo;
    @Column(nullable=false, length=200)
    private String direccion;

    public ListaLugarEntrega() {
        super();
    }

    public Integer getIdlugarentrega() {
        return idlugarentrega;
    }

    public void setIdlugarentrega(Integer idlugarentrega) {
        this.idlugarentrega = idlugarentrega;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepprodis() {
        return depprodis;
    }

    public void setDepprodis(String depprodis) {
        this.depprodis = depprodis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
