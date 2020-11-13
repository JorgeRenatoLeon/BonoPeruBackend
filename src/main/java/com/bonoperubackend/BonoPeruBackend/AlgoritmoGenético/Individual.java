package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenético;

import java.util.ArrayList;
import java.util.Hashtable;

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

    public ArrayList<Individual>  crossover_uniform(Individual individual) {
        /*
        chromosome1 = []
        chromosome2 = []
        "Retorna dos nuevos individuos del cruzamiento uniforme entre self y other "
        for i in range(len(self.chromosome)):
            if uniform(0, 1) < 0.5:
                chromosome1.append(self.chromosome[i])
                chromosome2.append(other.chromosome[i])
            else:
                chromosome1.append(other.chromosome[i])
                chromosome2.append(self.chromosome[i])
        ind1 = Individual(chromosome1)
        ind2 = Individual(chromosome2)
        return [ind1, ind2]
         */




        return new ArrayList<Individual>();
    }

    public Individual mutation_flip(Hashtable lugares) {
        return new Individual();
    }

}
