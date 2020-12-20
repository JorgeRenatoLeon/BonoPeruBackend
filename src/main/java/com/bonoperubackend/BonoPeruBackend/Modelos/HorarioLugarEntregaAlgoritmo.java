package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="horariolugarentregaalgoritmo")
public class HorarioLugarEntregaAlgoritmo implements Serializable {
    @Id
    private String codigo;

    private Integer idhorariolugarentrega;

    private LocalTime horaaperturaturnoma;
    private LocalTime horacierreturnoma;
    private LocalTime horaaperturaturnotar;
    private LocalTime horacierreturnotar;

    public HorarioLugarEntregaAlgoritmo() {
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdhorariolugarentrega() {
        return idhorariolugarentrega;
    }

    public void setIdhorariolugarentrega(Integer idhorariolugarentrega) {
        this.idhorariolugarentrega = idhorariolugarentrega;
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
}
