package com.bonoperubackend.BonoPeruBackend.AlgoritmoGenético;

import java.util.ArrayList;

public class Individual {
    private ArrayList<Integer> fitness=new ArrayList<>();
    private ArrayList<Integer> chromosome;

    public ArrayList<Integer> getFitness() {
        return fitness;
    }

    public void setFitness(ArrayList<Integer> fitness) {
        this.fitness = fitness;
    }

    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }

    public void setChromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
    }
}
