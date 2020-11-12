package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenético;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Hashtable;

public class genético {

    public ArrayList<Integer> get_fitness(ArrayList<Integer> chromosome, ArrayList<Item> items, Hashtable lugares){
        int i=0;
        return  new ArrayList<Integer>();
    }

    public void evaluate_population(ArrayList<Individual> population, ArrayList<Item> items, Hashtable lugares) {
        //Evalua una poblacion de individuos con la funcion get_fitness
        int pop_size = population.size();
        for (int i=0; i< pop_size; i++){
            if(population.get(i).getFitness().isEmpty()){
                population.get(i).setFitness(get_fitness(population.get(i).getChromosome(), items, lugares));
            }
        }
    }

    public static ArrayList<Integer> get_crowding_distances(ArrayList<ArrayList<Integer>> fitnesses){
        //La distancia crowding de un individuo es la diferencia del fitness mas proximo hacia arriba menos el fitness mas proximo
        //hacia abajo. El valor crowding total es la suma de todas las distancias crowdings para todos los fitness
        int pop_size = fitnesses.size();
        int num_objectives = fitnesses.get(0).size();
        //crea matriz crowding. Filas representan individuos, columnas representan objectives
        int[][] crowding_matrix = new int [pop_size][num_objectives];
        //normalisa los fitnesses entre 0 y 1 (ptp es max - min)
        for (int x=0; x < crowding_matrix.length; x++) {
            for (int y=0; y < crowding_matrix[x].length; y++) {
                crowding_matrix[x][y]=0;
            }
        }
        //
        int[][] matrizMa=new int[1][fitnesses.get(0).size()];
        int[][] matrizMi=new int[1][fitnesses.get(0).size()];
        for (ArrayList<Integer> fitnesse : fitnesses) {
            for (int j = 0; j < fitnesse.size(); j++) {
                if (fitnesse.get(j) > matrizMa[1][j]) {
                    matrizMa[1][j] = fitnesse.get(j);
                }
                if (fitnesse.get(j) < matrizMi[1][j]) {
                    matrizMi[1][j] = fitnesse.get(j);
                }
            }
        }
        int[] matrizDiv=new int[fitnesses.get(0).size()];
        for (int j = 0; j < matrizDiv.length; j++) {
            matrizDiv[j]=matrizMa[1][j]-matrizMi[1][j];
        }
        int[][] normalized_fitnesses = new int[fitnesses.size()][fitnesses.get(0).size()];
        //normalized_fitnesses=(fitnesses-matrizMi)/ matrizDiv;
        for(int i=0;i<fitnesses.size();i++){
            for(int j = 0; j< fitnesses.get(0).size(); j++){
                normalized_fitnesses[i][j]= fitnesses.get(i).get(j) -matrizMi[1][j];
                normalized_fitnesses[i][j]=normalized_fitnesses[i][j]/matrizDiv[j];
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
        ArrayList<Integer> crowding_distances= new ArrayList<Integer>(num_objectives);
        for(int i =0;i<num_objectives;i++){
            for(int j=0;j<pop_size;j++){
                suma = suma + crowding_matrix[j][i];
            }
            crowding_distances.set(i, suma);
        }
        return crowding_distances;
    }
    //ESTA FUNCIÓN FALTA ARREGLARLA
    private ArrayList<Individual> select_by_crowding(ArrayList<Individual> paretofront_population, int num_individuals) {
        //    Selecciona una poblacion de individuos basado en torneos de pares de individuos: dos individuos se escoge al azar
        //    y se selecciona el mejor segun la distancia crowding. Se repite hasta obtener num_individuals individuos
        ArrayList<Individual>  population1 = new ArrayList<Individual>();
        for(int i=0;i<paretofront_population.size();i++){
            population1.set(i, paretofront_population.get(i));
        }
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
        int[] permut=new int[pop_size];
        for(int i=0;i<num_individuals;i++){// por cada individuo a seleccionar
            //escoje dos individuos aleatoriamente de la poblacion no escogida aun
            for(int j=0;j<pop_size;j++){
                permut[j]=j;
            }
            for (int k = 0; k < pop_size; k++) {
                int r = (int) (Math.random() * (k+1));
                int swap = permut[r];
                permut[r] = permut[k];
                permut[k] = swap;
            }
            int ind1_id = permut[0];
            int ind2_id = permut[1];
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
        return population_selected;
    }

    private ArrayList<Individual> get_paretofront_population(ArrayList<Individual> populationC) {
        return new ArrayList<Individual>();
    }

    public ArrayList<Individual> build_next_population(ArrayList<Individual> population, int min_pop_size, int max_pop_size) {
    /*
    Construye la poblacion de la siguiente generacion añadiendo sucesivas fronteras de Pareto hasta
    tener una poblacion de al menos min_pop_size individuos. Reduce la frontera de Pareto con el metodo de
    crowding distance si al agregar la frontera excede el tamaño maximo de la poblacion (max_pop_size)
    */
        ArrayList<Individual> populationC=new ArrayList<>();
        population.addAll(population);

        ArrayList<Individual> pareto_front = new ArrayList<>();
        ArrayList<Individual> next_population = new ArrayList<>();
        ArrayList<Individual> paretofront_population = new ArrayList<>();

        int combined_population_size=0;

        while(next_population.size() < min_pop_size){
            // Se obtiene la poblacion frontera de Pareto actual
            paretofront_population = get_paretofront_population(populationC);

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




}
