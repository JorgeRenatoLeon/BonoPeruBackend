package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenetico;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.LugarEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Genetic {

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    public ArrayList<Individual> init_population(int pop_size, int chromosome_size, ArrayList<Item> items,
                                                 Hashtable<String,ArrayList<Integer>> lugares,
                                                 ArrayList<HorarioLugarEntregaAlgoritmo> lugarXdia) {
        //Inicializa una poblacion de pop_size individuos, cada cromosoma de individuo de tamaño chromosome_size.
        ArrayList<Individual> population=new ArrayList<Individual>();
        Random rn=new Random();

        for (int i=0; i<pop_size; i++){
            //Creamos un cromosoma
            ArrayList<String> new_chromosome=new ArrayList<String>();
            Hashtable<String,ArrayList<Integer>> capacidadU=new Hashtable();
            //Ver si modifica el original
            Set<String> lkeys=lugares.keySet();
            for(String lk: lkeys){
                capacidadU.put(lk,new ArrayList<>(lugares.get(lk)));
            }
            //capacidadU.putAll(lugares);
            //System.out.println("sz4: "+capacidadU.size());
            int j=0;
            while (j<chromosome_size){

                //Asignamos un lugar de entrega a un beneficiario, obtenemos la llave de lugarXdia
                int p=rn.nextInt(lugarXdia.size());
                int p2=rn.nextInt(2);
                String k=lugarXdia.get(p).getCodigo();
                //Escoger entre turno mañana y turno tarde
                if (p2==1) k=k+"T";
                else if (p2==0) k=k+"M";
                //Minimo que pertenezca al mismo departamento
                //System.out.println(k);
                //System.out.println(capacidadU.get(k).get(0));
                if(capacidadU.get(k).get(0)>0 && items.get(j).getUbigeo1()==lugares.get(k).get(1)){
                    capacidadU.get(k).set(0,capacidadU.get(k).get(0)-1);
                    new_chromosome.add(k);
                //}else if(capacidadU.get(k).get(0)>0){
                //    j-=1;
                }else{
                    new_chromosome.add("0");
                }
                j+=1;
            }
            population.add( new Individual(new_chromosome));

        }
        return population;
    }

    public ArrayList<Integer> get_fitness(ArrayList<String> chromosome, ArrayList<Item> items,
                                          Hashtable<String,ArrayList<Integer>> lugares){
        /*
        Retorna el fitness del cromosoma pasado. Fitness es el valor total de los items incluidos en el cromosoma
        Si el peso total es mayor que max_sexo -> el fitness es 1
        */
        //Objetivos
        ArrayList<Integer> fitness = new ArrayList<Integer>(Collections.nCopies(3, 0));

        Hashtable<String,ArrayList<Integer>> locales_copia=new Hashtable();
        //Ver si modifica el original
        //locales_copia.putAll(lugares);

        Set<String> lkeys=lugares.keySet();
        for(String lk: lkeys){
            locales_copia.put(lk,new ArrayList<>(lugares.get(lk)));
            locales_copia.get(lk).set(0,0);
        }

        int sum_abue = 0, sum_man=0, sum_woman=0, sum_diferente=0, sum_penalidad=0, sum_ubicacion=0;
        int cant_personas=0;

        //Recorremos todos los items
        for (int i=0; i<items.size(); i++){
            //Verificamos si el beneficio va a recoger el bono
            if(chromosome.get(i)!="0"){
                cant_personas+=100;
                //Acumular la suma de si es abuelo o no
                sum_abue+=items.get(i).getEs_disc();

                //Cantidad de hombres
                if(items.get(i).getSexo()==1){
                    //locales_copia.get(chromosome.get(i)).set(4,locales_copia.get(chromosome.get(i)).get(4)+1);
                    sum_man+=1;
                }
                //Cantidad de mujeres
                if(items.get(i).getSexo()==2){
                    //locales_copia.get(chromosome.get(i)).set(5,locales_copia.get(chromosome.get(i)).get(5)+1);
                    sum_woman+=1;
                }
                //acumular la penalidad
                sum_penalidad+=items.get(i).getPenalidad();

                //se verificar si se sobrepasa la capacidad del lugar
                locales_copia.get(chromosome.get(i)).set(0, locales_copia.get(chromosome.get(i)).get(0)+1);
                //si se paso de la capacidad del lugar retorna 1 como fitness
                if(locales_copia.get(chromosome.get(i)).get(0) > lugares.get(chromosome.get(i)).get(0)){
                    fitness.set(0,-10000000);
                    fitness.set(1,0);
                    fitness.set(2,0);
                    return fitness;
                }
                //acumular la ubicación

                if (items.get(i).getUbigeo1() == lugares.get(chromosome.get(i)).get(1) &&
                        items.get(i).getUbigeo2() == lugares.get(chromosome.get(i)).get(2) &&
                        items.get(i).getUbigeo3() == lugares.get(chromosome.get(i)).get(3) ){
                    sum_ubicacion+=4000;
                }else if(items.get(i).getUbigeo1() == lugares.get(chromosome.get(i)).get(1) &&
                        items.get(i).getUbigeo2() == lugares.get(chromosome.get(i)).get(2)){
                    sum_ubicacion+=2000/Math.abs(items.get(i).getUbigeo3() - lugares.get(chromosome.get(i)).get(3));
                    if(Math.abs(items.get(i).getUbigeo3() - lugares.get(chromosome.get(i)).get(3)) > 3){
                        sum_diferente+=10;
                    }
                }else if(items.get(i).getUbigeo1() == lugares.get(chromosome.get(i)).get(1)){
                    sum_ubicacion+=80/Math.abs(items.get(i).getUbigeo2() - lugares.get(chromosome.get(i)).get(2));
                    if (Math.abs(items.get(i).getUbigeo2() - lugares.get(chromosome.get(i)).get(2)) > 2){
                        sum_diferente+=100;
                    }
                }else {
                    fitness.set(0,-10000000);
                    fitness.set(1,0);
                    fitness.set(2,0);
                    return fitness;
                }
            }
        }
        //priorizar a discapacitados y mujeres -> maximizar
        fitness.set(0,sum_abue*100 + sum_woman*50 + sum_man*10 - sum_penalidad);
        //maximizar inversa de penalidad -> minimizar el penalidad (penalidad)
        if (sum_diferente!=0){
            fitness.set(1,1/sum_diferente);
        }else{
            fitness.set(0,0);
        }
        //maximizar la ubicación
        fitness.set(2,sum_ubicacion);

        return fitness;

    }

    public void evaluate_population(ArrayList<Individual> population,
                                    ArrayList<Item> items, Hashtable<String, ArrayList<Integer>> lugares) {
        //Evalua una poblacion de individuos con la funcion get_fitness
        int pop_size = population.size();
        for (int i=0; i< pop_size; i++){
            if(population.get(i).getFitness().isEmpty()){
                population.get(i).setFitness(get_fitness(population.get(i).getChromosome(), items, lugares));

            }
        }
    }

    public ArrayList<Individual> build_offspring_population(ArrayList<Individual> population, String crossover,
                                                            String mutation, double pmut,
                                                            Hashtable<String,ArrayList<Integer>> lugares,
                                                            ArrayList<HorarioLugarEntregaAlgoritmo> lugarXdia) {
        /*
        Construye una poblacion hija con los operadores de cruzamiento y mutacion pasados
        crossover:  operador de cruzamiento
        mutation:   operador de mutacion
        pmut:       taza de mutacion
        lugares:    todos los lugares de entrega que participan en el algoritmo
        lugarXdia:  lugares de entrega disponibles para ese día
        */

        Random r = new Random();
        int pop_size = population.size();

        //Selecciona parejas de individuos (mating_pool) para cruzamiento con el metodo de la ruleta
        ArrayList< ArrayList<Individual> > mating_pool = new ArrayList<>();

        for (int i=0; i < pop_size/2; i++){
            //Escoje dos individuos diferentes aleatoriamente de la poblacion
            int p1=r.nextInt(pop_size);
            int p2=r.nextInt(pop_size);
            ArrayList<Individual> pair = new ArrayList<>();
            pair.add(population.get(p1));
            pair.add(population.get(p2));
            mating_pool.add(pair);
        }

        //Crea la poblacion descendencia cruzando las parejas del mating pool
        ArrayList<Individual> offspring_population = new ArrayList<>();
        for (int i=0; i < mating_pool.size(); i++){
            if (crossover=="onepoint"){
                offspring_population.addAll(mating_pool.get(i).get(0).crossover_onepoint(mating_pool.get(i).get(1)));
            }else if(crossover=="uniform"){
                offspring_population.addAll(mating_pool.get(i).get(0).crossover_uniform(mating_pool.get(i).get(1)));
            }
        }

        // Aplica el operador de mutacion con probabilidad pmut en cada hijo generado
        for (int i=0; i<offspring_population.size(); i++){
            double uniform=r.nextDouble();
            if (uniform<pmut) {
                if (mutation == "flip") {
                    offspring_population.set(i, offspring_population.get(i).mutation_flip(lugares, lugarXdia));
                }
            }
        }
        return offspring_population;
    }

    public static ArrayList<Integer> get_crowding_distances(ArrayList<ArrayList<Integer>> fitnesses){

        //La distancia crowding de un individuo es la diferencia del fitness mas proximo hacia arriba menos el fitness mas proximo
        //hacia abajo. El valor crowding total es la suma de todas las distancias crowdings para todos los fitness
        int pop_size = fitnesses.size();
        int num_objectives = fitnesses.get(0).size();//3
        //crea matriz crowding. Filas representan individuos, columnas representan objectives
        int[][] crowding_matrix = new int [pop_size][num_objectives];
        //normalisa los fitnesses entre 0 y 1 (ptp es max - min)
        for (int x=0; x < crowding_matrix.length; x++) {
            for (int y=0; y < crowding_matrix[x].length; y++) {
                crowding_matrix[x][y]=0;
            }
        }
        //
        int[] matrizMa=new int [fitnesses.get(0).size()];
        int[] matrizMi=new int [fitnesses.get(0).size()];
        matrizMa[0]=0;
        matrizMa[1]=0;
        matrizMa[2]=0;
        matrizMi[0]=500;
        matrizMi[1]=500;
        matrizMi[2]=500;
        for (ArrayList<Integer> fitnesse : fitnesses) {
            for (int j = 0; j < fitnesse.size(); j++) {//3
                if (fitnesse.get(j) > matrizMa[j]) {
                    matrizMa[j] = fitnesse.get(j);
                }
                if (fitnesse.get(j) < matrizMi[j]) {
                    matrizMi[j] = fitnesse.get(j);
                }
            }
        }
        int[] matrizDiv=new int[fitnesses.get(0).size()];
        for (int j = 0; j < matrizDiv.length; j++) {
            matrizDiv[j]=matrizMa[j]-matrizMi[j];
        }
        int[][] normalized_fitnesses = new int[fitnesses.size()][fitnesses.get(0).size()];
        //normalized_fitnesses=(fitnesses-matrizMi)/ matrizDiv;
        for(int i=0;i<fitnesses.size();i++){
            for(int j = 0; j< fitnesses.get(0).size(); j++){
                normalized_fitnesses[i][j]= fitnesses.get(i).get(j) -matrizMi[j];
                if(matrizDiv[j]==0){
                    normalized_fitnesses[i][j]=normalized_fitnesses[i][j];
                }else{
                    normalized_fitnesses[i][j]=normalized_fitnesses[i][j]/matrizDiv[j];
                }
            }
        }
        //
        int[] crowding= new int[pop_size];
        int[] sorted_fitnesses = new int[pop_size];
        int[] sorted_fitnesses_index = new int[pop_size];
        int[] re_sort_order = new int[pop_size];
        int aux=0;
        int[] sorted_crowding= new int[pop_size];
        for(int col = 0; col < num_objectives; col++){ //Por cada objective
            for (int y=0; y < pop_size; y++) {
                crowding[y]=0;
            }
            //puntos extremos tienen maximo crowding
            crowding[0] = 1;
            crowding[pop_size - 1] = 1;
            //ordena los fitness normalizados del objectivo actual
            for (int i = 0; i < pop_size - 1; i++) {
                for (int j = 0; j < pop_size - i - 1; j++) {
                    if (normalized_fitnesses[j + 1][col] < normalized_fitnesses[j][col]) {
                        aux = normalized_fitnesses[j + 1][col];
                        normalized_fitnesses[j + 1][col] = normalized_fitnesses[j][col];
                        normalized_fitnesses[j][col] = aux;
                        sorted_fitnesses_index[i]=j;
                    }
                }
            }
            for (int i = 0; i < pop_size; i++) {
                sorted_fitnesses[i] = normalized_fitnesses[i][col];
            }

            //Calcula la distancia crowding de cada individuo como la diferencia de score de los vecinos
            for(int i=1;i<pop_size - 1;i++){
                crowding[i]=sorted_fitnesses[i+1]-sorted_fitnesses[i-1];
            }
            //obtiene el ordenamiento original
            for (int i = 0; i < pop_size - 1; i++) {
                for (int j = 0; j < pop_size - i - 1; j++) {
                    if (sorted_fitnesses_index[j + 1] < sorted_fitnesses_index[j]) {
                        aux = sorted_fitnesses_index[j + 1];
                        sorted_fitnesses_index[j + 1] = sorted_fitnesses_index[j];
                        sorted_fitnesses_index[j] = aux;
                        re_sort_order[i]=j;
                    }
                }
            }
            int a=0;
            for(int i=0;i<pop_size;i++){
                a=(int) re_sort_order[i];
                sorted_crowding[i]=crowding[a];
            }
            //Salva las distancias crowdingpara el objetivo que se esta iterando
            for (int i =0;i<pop_size;i++){
                crowding_matrix[i][col]=sorted_crowding[i];
            }
        }
        //Obtiene las distancias crowding finales sumando las distancias crowding de cada objetivo
        int suma=0;
        ArrayList<Integer> crowding_distances= new ArrayList<Integer>();
        for(int i =0;i<pop_size;i++){
            for(int j=0;j<num_objectives;j++){
                suma = suma + crowding_matrix[i][j];
            }
            crowding_distances.add(suma);
        }
        return crowding_distances;
    }
    //ESTA FUNCIÓN FALTA ARREGLARLA
    private ArrayList<Individual> select_by_crowding(ArrayList<Individual> paretofront_population, int num_individuals) {
        //    Selecciona una poblacion de individuos basado en torneos de pares de individuos: dos individuos se escoge al azar
        //    y se selecciona el mejor segun la distancia crowding. Se repite hasta obtener num_individuals individuos
        ArrayList<Individual>  population1 = new ArrayList<Individual>();
        population1.addAll(paretofront_population);
        /*System.out.println("Antes del select by crowding: "+paretofront_population.size());
        for(int i=0; i<paretofront_population.size(); i++){
            System.out.println(paretofront_population.get(i).getChromosome());
        }*/
        int pop_size = population1.size();
        int num_objectives = (population1.get(0).getFitness()).size();

        //extrae los fitness de la poblacion en la matriz fitnesses
        ArrayList<ArrayList<Integer>> fitnesses= new ArrayList<ArrayList<Integer>>();
        //float[][] fitnesses=new float[pop_size][num_objectives];
        for(int i=0;i<pop_size;i++){
            fitnesses.add(population1.get(i).getFitness());
        }
        //obtiene las  distancias  crowding
        ArrayList<Integer> crowding_distances = get_crowding_distances(fitnesses);
        ArrayList<Individual> population_selected=new ArrayList<>();//poblacion escogida
        //int[] permut=new int[pop_size];
        for(int i=0;i<num_individuals;i++){// por cada individuo a seleccionar
            //escoje dos individuos aleatoriamente de la poblacion no escogida aun
            Random rn = new Random();
            int ind1_id = rn.nextInt(population1.size());
            int ind2_id = rn.nextInt(population1.size());
            //Si ind1_id es el mejor
            if (crowding_distances.get(ind1_id) >= crowding_distances.get(ind2_id)){
                //traslada el individuo ind1 de population a la lista de individuos seleccionados
                population_selected.add( population1.remove(ind1_id) );
                //remueve la distancia crowding del individuo seleccionado
                crowding_distances.remove(ind1_id);

            }else{//Si ind2_id es el mejor
                //traslada el individuo ind2 de population a la lista de individuos seleccionados
                population_selected.add( population1.remove(ind2_id) );
                //remueve la distancia crowding del individuo seleccionado
                crowding_distances.remove(ind2_id);

            }
        }
        /*System.out.println("Despues del select by crowding: "+paretofront_population.size());
        System.out.println("Despues del select by crowding2: "+population1.size());
        for(int i=0; i<population1.size(); i++){
            System.out.println(population1.get(i).getChromosome());
        }*/
        return population_selected;
    }

    private ArrayList<Individual> get_paretofront_population(ArrayList<Individual> populationC) {
        ArrayList<Individual> population = (ArrayList<Individual>) populationC.clone();
        int pop_size = population.size();

        //todos los individuos son inicialmente asumidos como la frontera de Pareto
        ArrayList<Integer> pareto_front = new ArrayList<Integer>(Collections.nCopies(pop_size, 1));

        // Compara cada individuo contra todos los demas
        for (int i=0; i<pop_size ;i++){
            for (int j=0; j<pop_size; j++){
                // Chequea si individuo 'i' es dominado por individuo 'j'
                boolean mayorIgual = true;
                boolean mayor = false;
                ArrayList<Integer> populationJ = population.get(j).getFitness();
                ArrayList<Integer> populationI = population.get(i).getFitness();
                for (int k=0; k<populationJ.size();k++) {
                    if(populationJ.get(k)<populationI.get(k)){
                        //Si alguna entrada de J es menor que I
                        //j no domina a i
                        mayorIgual = false;
                        break;
                    }
                    if(populationJ.get(k)>populationI.get(k)){
                        //Alguna entreda del fitness de J es mayor que alguna entreda de I
                        mayor = true;
                    }
                }
                if(mayor && mayorIgual){
                    // j domina i -> señaliza que individuo 'i' como no siendo parte de la frontera de Pareto
                    pareto_front.set(i,0);
                    break; // Para la busqueda para 'i' (no es necesario hacer mas comparaciones)
                }
            }

        }


        ArrayList<Individual> paretofront_population = new ArrayList<Individual>();
        for (int i=0;i<pop_size;i++){ // Construye la lista de individuos de la frontera de Pareto
            if(pareto_front.get(i)==1)
                paretofront_population.add(population.get(i));
        }

        return paretofront_population;
    }

    public ArrayList<Individual> build_next_population(ArrayList<Individual> population, int min_pop_size, int max_pop_size) {
    /*
    Construye la poblacion de la siguiente generacion añadiendo sucesivas fronteras de Pareto hasta
    tener una poblacion de al menos min_pop_size individuos. Reduce la frontera de Pareto con el metodo de
    crowding distance si al agregar la frontera excede el tamaño maximo de la poblacion (max_pop_size)
    */
        ArrayList<Individual> populationC=new ArrayList<>();
        populationC.addAll(population);

        ArrayList<Individual> pareto_front = new ArrayList<>();
        ArrayList<Individual> next_population = new ArrayList<>();
        ArrayList<Individual> paretofront_population = new ArrayList<>();

        int combined_population_size=0;


        while(next_population.size() < min_pop_size){
            // Se obtiene la poblacion frontera de Pareto actual
            paretofront_population = get_paretofront_population(populationC);
            //Imprimir P
            //System.out.println("PFP.size: "+paretofront_population.size());
            /*for(int ari=0; ari<paretofront_population.size(); ari++){
                System.out.println("i: "+ari+"cant:"+paretofront_population.get(ari).getChromosome().size());
            }*/

            // Si poblacion actual + paretofront excede el maximo permitido -> reduce paretofront con el metodo de crowding
            combined_population_size = next_population.size() + paretofront_population.size();
            if  (combined_population_size > max_pop_size)
                paretofront_population = select_by_crowding( paretofront_population, max_pop_size-next_population.size() ) ;

            // Adiciona la frontera de Pareto (original o reducida) a la poblacion en construccion
            next_population.addAll(paretofront_population);

            // remueve de population los individuos que fueron agregados a next_population
            for (int i=0; i < paretofront_population.size(); i++){
                for (int j=0; j < populationC.size(); j++){
                    int iguales=1;
                    if (paretofront_population.get(i).getChromosome().equals(populationC.get(j).getChromosome())){
                        //Si son iguales, elimino el cromosoma del cromosoma copia
                        //No estoy segura que sea de la copia, para qué haría eso?
                        //para que el cromosoma se quede en paretofront_population
                        //y sea utilizado en la siguiente generación
                        populationC.remove(j);
                    }
                }
            }
        }
        return next_population;

    }


    public Hashtable<String,Hashtable<String,ArrayList<Item>>> generarCronograma(ArrayList<LugarEntregaAlgoritmo> lugarentregas,
                                                                                    ArrayList<BeneficiarioAlgoritmo> beneficiarios,
                                                                                   ArrayList<ArrayList<HorarioLugarEntregaAlgoritmo>> lugaresXdia,
                                                                                    Calendar c){
        /*
        lugarentregas: todos los lugares con sus datos para poder calificarlos y generar el objeto lugares
        beneficiarios: todos los beneficiaos con sus datos para calificarlos y generar el objeto itempool
        lugaresXdia: son los lugares de entrega por día (algunos no atienden todos los días)
        c: fecha anterior a la de inicio del cronograma

        itempool: diccionario de los beneficiarios con los datos procesados para generar el cronograma
        lugares: diccionario de los lugares de entrega con los datos procesados
         */
        ArrayList<Item> itempool;
        Hashtable<String,ArrayList<Integer>> lugares;
        ArrayList<Individual> P, Q, pareto_front_population;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);

        itempool=procesarBeneficiarios(beneficiarios);
        lugares=procesarLugares(lugarentregas);
        Set<String> lkeys=lugares.keySet();
        for(String lk: lkeys){
            System.out.println(lk + " " +lugares.get(lk));
        }
        int NUM_ITEMS = beneficiarios.size(); // numero de beneficiarios
        int MIN_POP_SIZE = 50;
        int MAX_POP_SIZE = 50;
        int CHROMOSOME_SIZE = NUM_ITEMS;
        int GENERATIONS = 300;  // # numero de generaciones
        double PMUT = 0.5;  // tasa de mutacion


        Hashtable<String,Hashtable<String,ArrayList<Item>>> cronograma=new Hashtable<>();


        while(itempool.size()!=0){
            //Nos ubicamos en el día de entrega, hay lugares que también atienden domingo
            c.add(Calendar.DATE, 1);
            diaSemana = c.get(Calendar.DAY_OF_WEEK);
            //System.out.println(c.getTime().toString());
            //Seleccionamos la lista según el día de la semana que tenga lugares de entrega
            while(lugaresXdia.get(diaSemana-1).size()==0){
                c.add(Calendar.DATE, 1);
                diaSemana = c.get(Calendar.DAY_OF_WEEK);
                //System.out.println(c.getTime().toString() + " "+ diaSemana);
            }

            ArrayList<HorarioLugarEntregaAlgoritmo> lugarXdia = lugaresXdia.get(diaSemana-1);

            //De lugarXdia se obtienen las llaves y de lugares los datos del lugar de entrega
            P=init_population(MAX_POP_SIZE,itempool.size(),itempool,lugares,lugarXdia);
            evaluate_population(P,itempool,lugares);
            //System.out.println("P.size: "+P.size());
            //System.out.println("P.fitness: "+P.get(0).getFitness());

            for(int i=0; i<GENERATIONS; i++){
                // genera y evalua la poblacion hija
                Q=build_offspring_population(P,"uniform", "flip",PMUT, lugares, lugarXdia);
                evaluate_population(Q,itempool,lugares);

                //System.out.println("Q.size: "+Q.size());
                //System.out.println("Q.fitness: "+Q.get(0).getFitness());

                // une la poblacion padre y la poblacion hija
                P.addAll(Q);
                // Construye la poblacion de la siguiente generacion
                P=build_next_population(P,MIN_POP_SIZE,MAX_POP_SIZE);
                //System.out.println("Pi.size: "+P.size());
                //System.out.println("Pi.fitness: "+P.get(0).getFitness());
            }

            // Obtiene la poblacion de la frontera de pareto final
            pareto_front_population = get_paretofront_population(P);
            //System.out.println(pareto_front_population);
            Hashtable<String,ArrayList<Item>> beneficiariosAsignados = new Hashtable<>();

            // Inicializamos las listas de beneficiarios por lugar de entrega
            for (int j=0; j<lugarXdia.size(); j++){
                // Beneficiarios de la mañana
                beneficiariosAsignados.put(lugarXdia.get(j).getCodigo()+'M', new ArrayList<>());
                // Beneficiarios de la tarde
                beneficiariosAsignados.put(lugarXdia.get(j).getCodigo()+'T', new ArrayList<>());
            }

            int i=0;
            //System.out.println("pf: " +pareto_front_population.get(0).getChromosome());
            for (int j=0; j<pareto_front_population.get(0).getChromosome().size(); j++){
                if(pareto_front_population.get(0).getChromosome().get(j)!="0"){
                    beneficiariosAsignados.get(pareto_front_population.get(0).getChromosome().get(j)).add(itempool.get(i));
                    itempool.get(i).setCantAsignado(itempool.get(i).getCantAsignado()+1);
                    if(itempool.get(i).getPenalidad()>4 || itempool.get(i).getCantAsignado()>1){
                        itempool.remove(itempool.get(i));
                        i-=1;
                    }

                }
                i+=1;
            }
            cronograma.put(df.format(c.getTime()),beneficiariosAsignados);
            //cronograma.add(beneficiariosAsignados);
        }
        /*
        Set<String> lkeys2=lugares.keySet();
        for(String lk: lkeys2){
            System.out.println(lk + " " +lugares.get(lk));
        }*/
        //Hashtable<Calendar,Hashtable<String,ArrayList<Item>>>
        Set<String>  lCro=cronograma.keySet();
        for(String lck: lCro){
            System.out.println(lck);
            Set<String> lCroS = cronograma.get(lck).keySet();
            for(String key: lCroS){
                System.out.println("lugar: "+ key + " item: "+cronograma.get(lck).get(key));
            }

        }
        for(String lk: lkeys){
            System.out.println(lk + " " +lugares.get(lk));
        }
        return cronograma;
    }

    private ArrayList<Item> procesarBeneficiarios(ArrayList<BeneficiarioAlgoritmo> beneficiarios) {
        ArrayList<Item> itempool = new ArrayList<>();
        //int nuevo_id, int penal, int abue, int sex, int ubi1, int ubi2, int ubi3
        for(int i=0; i<beneficiarios.size(); i++){
            BeneficiarioAlgoritmo beneficiario=beneficiarios.get(i);
            int varSexo=0, varDisc=0;

            //Asignamos el valor según el sexo
            if(beneficiario.getMasculino())
                varSexo=1;
            else if(beneficiario.getFemenino())
                varSexo=2;

            //Asignamos el valor según su condición de discapacidato
            if(beneficiario.getEsdiscapacitado())
                varDisc=1;

            //Creamos el item según las condiciones
            Item item = new Item(beneficiario.getIdbeneficiario(), beneficiario.getCodigofamilia(),beneficiario.getPenalidad(), varDisc,  varSexo, beneficiario.getUbigeodepartamento(), beneficiario.getUbigeoprovincia(), beneficiario.getUbigeodistrito(),0);
            System.out.println(item.getPenalidad());
            itempool.add(item);

        }
        return itempool;
    }

    private Hashtable<String,ArrayList<Integer>> procesarLugares(ArrayList<LugarEntregaAlgoritmo> lugarentregas) {
        Hashtable<String,ArrayList<Integer>> lugares=new Hashtable<>();
        LugarEntregaAlgoritmo lugar;
        int capacidad=0;
        //System.out.println("sz2: "+lugarentregas.size());
        for(int i=0; i<lugarentregas.size();i++){
            lugar=lugarentregas.get(i);
            ArrayList<Integer> valorM=new ArrayList<>();
            ArrayList<Integer> valorT=new ArrayList<>();
            capacidad=procesarCapacidad(lugar);
            valorM.add(capacidad);
            valorT.add(capacidad);
            valorM.add(lugar.getUbigeodepartamento());
            valorT.add(lugar.getUbigeodepartamento());
            valorM.add(lugar.getUbigeoprovincia());
            valorT.add(lugar.getUbigeoprovincia());
            valorM.add(lugar.getUbigeodistrito());
            valorT.add(lugar.getUbigeodistrito());
            lugares.put(lugar.getCodigo()+'M',valorM); //Turno mañana
            lugares.put(lugar.getCodigo()+'T',valorT); //Turno tarde
        }
        //System.out.println("sz3: "+lugares.size());
        return  lugares;
    }

    private int procesarCapacidad(LugarEntregaAlgoritmo lugar) {
        int capacidad=0, penalidad=0;
        penalidad=lugar.getCantquejas()*2+lugar.getCantbonosnoentregados();

        if(lugar.getCantbonosentregados()+lugar.getCantbonosnoentregados()!=0)
            penalidad=penalidad/((lugar.getCantbonosentregados()+lugar.getCantbonosnoentregados())*2);
        else
            penalidad=0;

        penalidad=penalidad*100;

        capacidad=lugar.getCapacidad();

        //Se disminuye la capacidad según la el nivel de riesgo de la zona a la que pertenece
        //Nivel 1, indica que no es zona de riesgo, entonces no se disminuirá la capacidad
        if(lugar.getNivelcontagio()==2)
            capacidad-=0.1*capacidad;
        else if(lugar.getNivelcontagio()==3)
            capacidad-=0.2*capacidad;
        else if(lugar.getNivelcontagio()==4)
            capacidad-=0.3*capacidad;
        else if(lugar.getNivelcontagio()==5)
            capacidad-=0.4*capacidad;

        //Disminuimos la capacidad según su historial de entregas que se ve reflejado en su penalidad
        //A menor % de penalidad es mejor, por ello no se le modificará la capacidad aquellos que
        //pertenezcan de 0 a 20 % de penaldad
        if( penalidad>20 && penalidad<=40)
            capacidad-=0.1*capacidad;
        else if( penalidad>40 && penalidad<=60)
            capacidad-=0.2*capacidad;
        else if( penalidad>60 && penalidad<=80)
            capacidad-=0.3*capacidad;
        else if( penalidad>80)
            capacidad-=0.4*capacidad;
        //System.out.println("cap: "+capacidad);
        return capacidad;
    }


}
