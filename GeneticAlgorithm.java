package geneticalgorithm;

import java.util.Arrays;

public class GeneticAlgorithm {
    private int maxGenerations = 25;
    private int currentGeneration = 0;
    private int populationSize;
    private int chromosomeLength;
    private double mutationProbability;
    private Population population;

    GeneticAlgorithm(int populationSize, int chromosomeLength, double mutationProbability) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.mutationProbability = mutationProbability;
    }

    void init() {
        System.out.println("Mutation Probability: " + String.valueOf(mutationProbability));
        System.out.println("Size of Population: " + populationSize);
        population = Population.getInstance(populationSize);
        population.setMutationProbability(mutationProbability);
        population.initPopulation(chromosomeLength);
        while (!isTerminationConditionMet()) {
            population.calculateFitness();
            displayCurrentGenerationPopulation();
            population.generate();
        }
    }

    private void displayCurrentGenerationPopulation() {
        System.out.println("\nPopulation for generation " + currentGeneration + ":");
        Chromosome[] children = population.getChromosomes();
        for (int i = 0; i < populationSize; i++) {
            System.out.printf(Arrays.toString(children[i].getGenes()) + " [%.2f]\n", children[i].getFitnessScore());
        }
        System.out.println();
    }

    private boolean isTerminationConditionMet() {
        if (currentGeneration < maxGenerations) {
            currentGeneration++;
            return false;
        }
        return true;
    }


}
