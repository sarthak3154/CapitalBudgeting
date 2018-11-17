package geneticalgorithm;

import java.util.Arrays;

public class GeneticAlgorithm {
    private Solution solution;
    private int maxGenerations = 10000;
    private int currentGeneration = 0;
    private int populationSize;
    private int chromosomeLength;
    private double mutationProbability;
    private Population population;

    GeneticAlgorithm(int populationSize, int chromosomeLength, double mutationProbability) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.mutationProbability = mutationProbability;
        solution = new Solution();
    }

    void init() {
        System.out.println("Mutation Probability: " + String.valueOf(mutationProbability));
        System.out.println("Size of Population: " + populationSize);
        System.out.println("\nPopulation for generation N:\n[x1, x2, x3, x4] [Fitness Score]");
        population = Population.getInstance(populationSize);
        population.setMutationProbability(mutationProbability);
        population.initPopulation(chromosomeLength);
        do {
            population.calculateFitness();
            displayCurrentGenerationPopulation();
            population.generate();
        } while (!isTerminationConditionMet());
        displayFinalSelection();
    }

    private void displayFinalSelection() {
        System.out.println("\n:: Best projects to invest in order to maximize the total return ::");
        int count = 0;
        String[] genes = solution.getOffspring().substring(1, solution.getOffspring().length() - 1).split(",");
        for (int i = 0; i < genes.length; i++) {
            if (Integer.parseInt(genes[i].trim()) == 1) {
                System.out.printf("Project %d: ", (++count));
                System.out.println(i + 1);
            }
        }
        System.out.println("\nFitness Score of the selection: " + solution.getFitnessScore());
    }

    private void displayCurrentGenerationPopulation() {
        System.out.println("\nPopulation for generation " + (++currentGeneration) + ":");
        Chromosome[] children = population.getChromosomes();
        for (int i = 0; i < populationSize; i++) {
            System.out.printf(Arrays.toString(children[i].getGenes()) + " [%.2f]\n", children[i].getFitnessScore());
        }
        System.out.println();
    }

    private boolean isTerminationConditionMet() {
        Chromosome fittestOffset = population.getFittestOffspring();
        String fittestOffsetValue = Arrays.toString(fittestOffset.getGenes());
        if ((solution.getOffspring() != null &&
                Double.compare(fittestOffset.getFitnessScore(), solution.getFitnessScore()) > 0) ||
                currentGeneration == 1) {
            solution.setOffspring(fittestOffsetValue);
            solution.setRecurCount(1);
            solution.setFitnessScore(fittestOffset.getFitnessScore());
        } else if (solution.getOffspring() != null &&
                Double.compare(fittestOffset.getFitnessScore(), solution.getFitnessScore()) < 0) {
            solution.setRecurCount(0);
        } else if (fittestOffsetValue.compareTo(solution.getOffspring()) == 0) {
            solution.setRecurCount(solution.getRecurCount() + 1);
        }
        return solution.getRecurCount() >= Constants.TERMINATION_RECUR_COUNT || currentGeneration >= maxGenerations;
    }

    class Solution {
        String offspring;
        int recurCount;
        double fitnessScore;

        double getFitnessScore() {
            return fitnessScore;
        }

        void setFitnessScore(double fitnessScore) {
            this.fitnessScore = fitnessScore;
        }

        String getOffspring() {
            return offspring;
        }

        void setOffspring(String offspring) {
            this.offspring = offspring;
        }

        int getRecurCount() {
            return recurCount;
        }

        void setRecurCount(int recurCount) {
            this.recurCount = recurCount;
        }
    }

}
