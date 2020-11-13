package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenético;

public class Item
{
    //Atributos de la clase
    private int id;
    private int penalidad;
    private int es_abue;
    private int sexo;
    private int ubigeo1;
    private int ubigeo2;
    private int ubigeo3;

    //Constructor con el mismo nombre de la clase
    public Item(int nuevo_id, int penal, int abue, int sex, int ubi1, int ubi2, int ubi3){
        this.id = nuevo_id;
        this.penalidad = penal;
        this.es_abue = abue;
        this.sexo = sex;
        this.ubigeo1 = ubi1;
        this.ubigeo2 = ubi2;
        this.ubigeo3 = ubi3;
    }

    //Métodos de la clase
    public int getPenalidad(){
        return this.penalidad;
    }

    public int getEsAbuelo(){
        return this.es_abue;
    }

    public int getSexo(){
        return this.sexo;
    }

    public int getUbigeo1(){
        return this.ubigeo1;
    }

    public int getUbigeo2(){
        return this.ubigeo2;
    }

    public int getUbigeo3(){
        return this.ubigeo3;
    }

    public void setPenalidad(int penal){
        this.penalidad = penal;
    }

    public void setEsAbuelo(int abue){
        this.es_abue= abue;
    }

    public void setSexo(int sex){
        this.sexo = sex;
    }

    public void setUbigeo1(int ubi1){
        this.ubigeo1 = ubi1;
    }

    public void setUbigeo2(int ubi2){
        this.ubigeo2 = ubi2;
    }

    public void setUbigeo3(int ubi3){
        this.ubigeo3 = ubi3;
    }

}
