package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenetico;

public class Item
{
    //Atributos de la clase
    private String id;
    private Integer idbd;
    private float penalidad;
    private int es_disc;
    private int sexo;
    private int ubigeo1;
    private int ubigeo2;
    private int ubigeo3;

    //Constructor con el mismo nombre de la clase
    public Item(Integer idbd, String nuevo_id, float penal, int disc, int sex, int ubi1, int ubi2, int ubi3){

        this.id = nuevo_id;
        this.idbd=idbd;
        this.penalidad = penal;
        this.es_disc = disc;
        this.sexo = sex;
        this.ubigeo1 = ubi1;
        this.ubigeo2 = ubi2;
        this.ubigeo3 = ubi3;
    }

    //Métodos de la clase


    public Integer getIdbd() {
        return idbd;
    }

    public void setIdbd(Integer idbd) {
        this.idbd = idbd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPenalidad() {
        return penalidad;
    }

    public void setPenalidad(float penalidad) {
        this.penalidad = penalidad;
    }

    public int getEs_disc() {
        return es_disc;
    }

    public void setEs_disc(int es_disc) {
        this.es_disc = es_disc;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getUbigeo1() {
        return ubigeo1;
    }

    public void setUbigeo1(int ubigeo1) {
        this.ubigeo1 = ubigeo1;
    }

    public int getUbigeo2() {
        return ubigeo2;
    }

    public void setUbigeo2(int ubigeo2) {
        this.ubigeo2 = ubigeo2;
    }

    public int getUbigeo3() {
        return ubigeo3;
    }

    public void setUbigeo3(int ubigeo3) {
        this.ubigeo3 = ubigeo3;
    }
}
