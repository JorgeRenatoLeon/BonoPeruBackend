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
        return new ArrayList<Individual>();
    }

    public ArrayList<Individual>  crossover_uniform(Individual individual) {
        return new ArrayList<Individual>();
    }

    public Individual mutation_flip(Hashtable lugares) {
        return new Individual();
    }

}
