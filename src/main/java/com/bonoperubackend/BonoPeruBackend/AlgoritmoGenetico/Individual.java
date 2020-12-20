package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenetico;

import com.bonoperubackend.BonoPeruBackend.Modelos.HorarioLugarEntregaAlgoritmo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Individual {
    private ArrayList<Integer> fitness=new ArrayList<>();
    private ArrayList<String> chromosome;

    public Individual(ArrayList<String> chromosome) {
        this.chromosome = chromosome;
    }

    public Individual() {
    }

    public ArrayList<Integer> getFitness() {

        return fitness;
    }

    public void setFitness(ArrayList<Integer> fitness) {
        this.fitness = fitness;
    }

    public ArrayList<String> getChromosome() {
        return chromosome;
    }

    public void setChromosome(ArrayList<String> chromosome) {
        this.chromosome = chromosome;
    }

    public ArrayList<Individual> crossover_onepoint(Individual individual) {
        /*"Retorna dos nuevos individuos del cruzamiento de un punto entre individuos self y other "
        c = randrange(len(self.chromosome))
        ind1 = Individual(self.chromosome[:c] + other.chromosome[c:])
        ind2 = Individual(other.chromosome[:c] + self.chromosome[c:])
        return [ind1, ind2]*/

        return new ArrayList<Individual>();
    }

    public ArrayList<Individual>  crossover_uniform(Individual other) {

        ArrayList<String> chromosome1 =new ArrayList<>();
        ArrayList<String> chromosome2 =new ArrayList<>();
        int n= this.chromosome.size();
        Random uniform = new Random();
        for (int i=0;i<n;i++){
            if (uniform.nextFloat()<0.5 ) { //NextFloat es un número entre 0 y 1
                chromosome1.add(this.getChromosome().get(i));
                chromosome2.add(other.getChromosome().get(i));
            } else {
                chromosome1.add(other.getChromosome().get(i));
                chromosome2.add(this.getChromosome().get(i));
            }
        }
        Individual ind1= new Individual(chromosome1);
        Individual ind2= new Individual(chromosome2);

        ArrayList<Individual> individuals=new ArrayList<>();
        individuals.add(ind1);
        individuals.add(ind2);
        /* ///////////Devuelvo una lista o dos? */
        return individuals;
    }

    public Individual mutation_flip(Hashtable lugares, ArrayList<HorarioLugarEntregaAlgoritmo> lugarXdia) {
        //Cambia aleatoriamente el alelo de un gen.
        ArrayList<String> new_chromosome =new ArrayList<>();
        new_chromosome.addAll(this.chromosome);

        Random rn = new Random();

        int mutGene=rn.nextInt(new_chromosome.size()); // escoge un gen para mutar

        //Seleccionamor el lugar de entrega aleatoriamente
        int p=rn.nextInt(lugarXdia.size()); //escoger el luga de entrega nuevo
        int p2=rn.nextInt(2);
        String k=lugarXdia.get(p).getCodigo();
        if (p2==1) k=k+"T";
        else if (p2==0) k=k+"M";

        //Asignamos el nuevo valor de lugar de entrega
        new_chromosome.set(mutGene,k);

        return new Individual(new_chromosome);

    }

}
