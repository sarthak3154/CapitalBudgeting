package geneticalgorithm;

import java.util.Arrays;

public class Population {
    private static Population population;
    private int size;

    private Population(int size) {
        this.size = size;
    }

    static Population createInstance(int populationSize) {
        if (population == null)
            return new Population(populationSize);
        return population;
    }

    void initPopulation(int chromosomeLength) {
        Chromosome[] chromosomes = new Chromosome[size];
        for (int i = 0; i < size; i++) {
            chromosomes[i] = new Chromosome(chromosomeLength);
            String randomFind = chromosomes[i].findRandomChromosome();
            //TODO compare the random find to previous gen chromosomes
        }
    }

    public int getSize() {
        return size;
    }
}
