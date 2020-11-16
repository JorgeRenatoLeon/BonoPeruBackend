package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenético;
import java.util.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] ar) {
        long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        //NUM_ITEMS = len(data1)        # numero de items que lee de bd :(
        int NUM_ITEMS = 1; // numero de beneficiarios
        int MIN_POP_SIZE = 50;
        int MAX_POP_SIZE = 50;
        int CHROMOSOME_SIZE = NUM_ITEMS;
        int GENERATIONS = 300;  // # numero de generaciones
        double  PMUT = 0.5       ;  // tasa de mutacion

        ArrayList<Individual> porcentaje=new ArrayList<>();
        ArrayList<Individual> cronograma=new ArrayList<>();
        //data1 es beneficiarios
        //data es locales
        //procesamiento de lugares
        //Dicccionario de lugares
        //beneficiarios

        // creating a Diccionario/Hash Table
        ArrayList<Integer> auxLugares=new ArrayList<>(); //Array de 4 enteros
        auxLugares.add(1); //Debe ir la longitud de data1-#beneficiarios totales
        auxLugares.add(0);         auxLugares.add(0);         auxLugares.add(0);
//        enum auxLugares=
        Hashtable<String, ArrayList<Integer>> lugares = new Hashtable<String, ArrayList<Integer>>();
        lugares.put("0", auxLugares);

        //lugares.put("10", 6); my_dict.get("10");  my_dict.isEmpty(); my_dict.remove("10"); my_dict.size();

        TFin= System.currentTimeMillis();
        tiempo= TFin- TInicio; //Tiempo en milisegundos
    }
}
