package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenético;

public class genético {
    public static float[] get_crowding_distances(float[][] fitnesses){
        //La distancia crowding de un individuo es la diferencia del fitness mas proximo hacia arriba menos el fitness mas proximo
        //hacia abajo. El valor crowding total es la suma de todas las distancias crowdings para todos los fitness
        int pop_size = fitnesses.length;
        int num_objectives = fitnesses[0].length;
        //crea matriz crowding. Filas representan individuos, columnas representan objectives
        float[][] crowding_matrix = new float [pop_size][num_objectives];
        //normalisa los fitnesses entre 0 y 1 (ptp es max - min)
        for (int x=0; x < crowding_matrix.length; x++) {
            for (int y=0; y < crowding_matrix[x].length; y++) {
                crowding_matrix[x][y]=0;
            }
        }
        //
        float[][] matrizMa=new float[1][fitnesses[0].length];
        float[][] matrizMi=new float[1][fitnesses[0].length];
        for (float[] fitnesse : fitnesses) {
            for (int j = 0; j < fitnesse.length; j++) {
                if (fitnesse[j] > matrizMa[1][j]) {
                    matrizMa[1][j] = fitnesse[j];
                }
                if (fitnesse[j] < matrizMi[1][j]) {
                    matrizMi[1][j] = fitnesse[j];
                }
            }
        }
        float[] matrizDiv=new float[fitnesses[0].length];
        for (int j = 0; j < matrizDiv.length; j++) {
            matrizDiv[j]=matrizMa[1][j]-matrizMi[1][j];
        }
        float[][] normalized_fitnesses = new float[fitnesses.length][fitnesses[0].length];
        //normalized_fitnesses=(fitnesses-matrizMi)/ matrizDiv;
        for(int i=0;i<fitnesses.length;i++){
            for(int j=0;j<fitnesses[0].length;j++){
                normalized_fitnesses[i][j]=fitnesses[i][j]-matrizMi[1][j];
                normalized_fitnesses[i][j]=normalized_fitnesses[i][j]/matrizDiv[j];
            }
        }
        //
        float[] crowding= new float[pop_size];
        float[] sorted_fitnesses = new float[pop_size];
        float[] sorted_fitnesses_index = new float[pop_size];
        float[] re_sort_order = new float[pop_size];
        float aux=0;
        float[] sorted_crowding= new float[pop_size];
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
        float suma=0;
        float[] crowding_distances= new float[num_objectives];
        for(int i =0;i<num_objectives;i++){
            for(int j=0;j<pop_size;j++){
                suma=suma+crowding_matrix[j][i];
            }
            crowding_distances[i]=suma;
        }
        return crowding_distances;
    }
    //ESTA FUNCIÓN FALTA ARREGLARLA
    /*public static float[] select_by_crowding(float[] population, int num_individuals){
        //    Selecciona una poblacion de individuos basado en torneos de pares de individuos: dos individuos se escoge al azar
        //    y se selecciona el mejor segun la distancia crowding. Se repite hasta obtener num_individuals individuos
        float[] population1 = new float[population.length];
        for(int i=0;i<population.length;i++){
            population1[i]=population[i];
        }
        int pop_size = population1.length;
        int num_objectives = (population1[0].fitness).length;
        //extrae los fitness de la poblacion en la matriz fitnesses
        float[][] fitnesses=new float[pop_size][num_objectives];
        for(int i=0;i<pop_size;i++){
            for(int j=0;j<num_objectives;j++){
                fitnesses[i][j]=population1[i].fitness;
            }
        }
        //obtiene las  distancias  crowding
        float[] crowding_distances = get_crowding_distances(fitnesses);
        float[]population_selected = null;//poblacion escogida
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
            if (crowding_distances[ind1_id] >= crowding_distances[ind2_id]){
                //traslada el individuo ind1 de population a la lista de individuos seleccionados
                population_selected.append( population1.pop(ind1_id) );
                //remueve la distancia crowding del individuo seleccionado
                crowding_distances = np.delete(crowding_distances, ind1_id, axis=0);
            }else{//Si ind2_id es el mejor
                //traslada el individuo ind2 de population a la lista de individuos seleccionados
                population_selected.append( population1.pop(ind2_id) );
                //remueve la distancia crowding del individuo seleccionado
                crowding_distances = np.delete(crowding_distances, ind2_id, axis=0);
            }
        }
        return population_selected;
    }*/
}
