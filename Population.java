package geneticalgorithm;

import java.util.Random;
import java.util.stream.Stream;

public class Population {
    private static Population population;
    private Chromosome[] chromosomes;
    private Chromosome[] parents;
    private int populationSum = 0;
    private int chromosomeLength;
    private int size;

    private Population(int size) {
        this.size = size;
    }

    static Population getInstance(int populationSize) {
        if (population == null) {
            population = new Population(populationSize);
        }
        return population;
    }

    void initPopulation(int chromosomeLength) {
        chromosomes = new Chromosome[size];
        this.chromosomeLength = chromosomeLength;
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

    /**
     * Generate the next generation population
     *
     * @return
     */
    void generate() {
        rouletteSelection();
        crossover();
        mutation();
    }

    /**
     * Roulette Selection Mechanism
     *
     * @return
     */
    private Chromosome getFittestChromosome() {
        int partialSum = 0;
        int rand = new Random().nextInt(populationSum);
        for (int i = 0; i < size; i++) {
            partialSum += chromosomes[i].getFitnessScore();
            if (partialSum >= rand)
                return chromosomes[i];
        }
        return null;
    }

    /**
     * Roulette Wheel Selection Algorithm
     */
    private void rouletteSelection() {
        parents = new Chromosome[2];
        parents[0] = getFittestChromosome();
        parents[1] = getFittestChromosome();
    }

    private void crossover() {
        int crossOverPoint = new Random().nextInt(chromosomeLength);
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = parents[0].getGenes()[i];
            parents[0].getGenes()[i] = parents[1].getGenes()[i];
            parents[1].getGenes()[i] = temp;
        }
    }

    private void mutation() {
        Random random = new Random();
        int mutationPoint = random.nextInt(chromosomeLength);
        parents[0].getGenes()[mutationPoint] = (parents[0].getGenes()[mutationPoint] == 0 ? 1 : 0);
        int altMutationPoint = random.nextInt(chromosomeLength);
        parents[1].getGenes()[altMutationPoint] = (parents[1].getGenes()[altMutationPoint] == 0 ? 1 : 0);
    }

    public int getSize() {
        return size;
    }
}
