package geneticalgorithm;

import java.util.Random;
import java.util.stream.Stream;

public class Population {
    private static Population population;
    private Chromosome[] chromosomes;
    private Chromosome[] parents;
    private int populationSum = 0;
    private int size;

    private Population(int size) {
        this.size = size;
    }

    static Population createInstance(int populationSize) {
        if (population == null) {
            population = new Population(populationSize);
        }
        return population;
    }

    void initPopulation(int chromosomeLength) {
        chromosomes = new Chromosome[size];
        for (int i = 0; i < size; i++) {
            chromosomes[i] = new Chromosome(chromosomeLength);
            String randomFind = chromosomes[i].getValidChromosome();
            chromosomes[i].setGenes(Stream.of(randomFind.split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray());
        }
    }

    void calculateFitness() {
        for (int i = 0; i < size; i++) {
            chromosomes[i].setFitnessScore(chromosomes[i].calculateFitnessScore());
            populationSum += chromosomes[i].getFitnessScore();
        }
    }

    void performSelection() {
        parents = new Chromosome[2];
        parents[0] = selectParent();
        parents[1] = selectParent();
    }

    private Chromosome selectParent() {
        int partialSum = 0;
        int rand = new Random().nextInt(populationSum);
        for (int i = 0; i < size; i++) {
            partialSum += chromosomes[i].getFitnessScore();
            if (partialSum >= rand)
                return chromosomes[i];
        }
        return null;
    }

    void performCrossover() {

    }

    void performMutation() {

    }

    public Chromosome[] getParents() {
        return parents;
    }

    public int getSize() {
        return size;
    }
}
