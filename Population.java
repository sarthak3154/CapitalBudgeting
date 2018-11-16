package geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

public class Population {
    private static Population population;
    private Chromosome[] chromosomes;
    private Chromosome[] parentChromosomes;
    private int[] parentIndices;
    private double mutationProbability;
    private double fitnessScore = 0;
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
        parentIndices = new int[2];
        this.chromosomeLength = chromosomeLength;
        for (int i = 0; i < size; i++) {
            chromosomes[i] = new Chromosome(chromosomeLength);
            chromosomes[i].setGenes(chromosomes[i].getValidChromosome());
        }
    }

    void calculateFitness() {
        fitnessScore = 0;
        for (int i = 0; i < size; i++) {
            chromosomes[i].calculateFitnessScore();
            fitnessScore += chromosomes[i].getFitnessScore();
        }
    }

    /**
     * Generate the next generation population
     *
     * @return
     */
    void generate() {
        rouletteSelection();
        do {
            updateParents();
            crossover();
            mutation();
        } while (!isValidOffset());
        insertOffspring();
    }

    private void updateParents() {
        parentChromosomes[0] = chromosomes[parentIndices[0]];
        parentChromosomes[1] = chromosomes[parentIndices[1]];
    }

    private boolean isValidOffset() {
        return parentChromosomes[0].isValidChromosome() && parentChromosomes[1].isValidChromosome();
    }

    private Chromosome getFittestOffspring() {
        if (parentChromosomes[0].getFitnessScore() > parentChromosomes[1].getFitnessScore()) {
            return parentChromosomes[0];
        }
        return parentChromosomes[1];
    }

    private int getLeastFittestChromosomeIndex() {
        int min = Integer.MAX_VALUE;
        double minValue = 0.0;
        for (int i = 0; i < size; i++) {
            if (Double.compare(minValue, chromosomes[i].getFitnessScore()) < 0) {
                minValue = chromosomes[i].getFitnessScore();
                min = i;
            }
        }
        return min;
    }

    private void insertOffspring() {
        parentChromosomes[0].calculateFitnessScore();
        parentChromosomes[1].calculateFitnessScore();
        int leastFittestIndex = getLeastFittestChromosomeIndex();
        chromosomes[leastFittestIndex] = getFittestOffspring();
        System.out.println("\nInserted Offspring: " + Arrays.toString(chromosomes[leastFittestIndex].getGenes()));
    }

    /**
     * Roulette Selection Mechanism
     *
     * @return
     */
    private int getFittestChromosomeIndex() {
        double partialSum = 0;
        Random random = new Random();
        double rand = (fitnessScore) * random.nextDouble();
        System.out.println("Fitness Score: " + fitnessScore);
        for (int i = 0; i < size; i++) {
            partialSum += chromosomes[i].getFitnessScore();
            if (partialSum >= rand)
                return i;
        }
        return -1;
    }

    /**
     * Roulette Wheel Selection Algorithm
     */
    private void rouletteSelection() {
        parentChromosomes = new Chromosome[2];
        parentIndices = new int[2];
        Arrays.fill(parentChromosomes, null);
        parentIndices[0] = getFittestChromosomeIndex();
        parentChromosomes[0] = chromosomes[parentIndices[0]];
        parentIndices[1] = getFittestChromosomeIndex();
        parentChromosomes[1] = chromosomes[parentIndices[1]];
        System.out.println("Individuals selected from Roulette Wheel for crossover: \n" +
                Arrays.toString(parentChromosomes[0].getGenes()) + "\n" +
                Arrays.toString(parentChromosomes[1].getGenes()));
    }

    private void crossover() {
        int crossOverPoint = new Random().nextInt(chromosomeLength);
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = parentChromosomes[0].getGenes()[i];
            parentChromosomes[0].getGenes()[i] = parentChromosomes[1].getGenes()[i];
            parentChromosomes[1].getGenes()[i] = temp;
        }
    }

    private void mutation() {
        if (Math.random() > mutationProbability) {
            Random random = new Random();
            int mutationPoint = random.nextInt(chromosomeLength);
            parentChromosomes[0].getGenes()[mutationPoint] = (parentChromosomes[0].getGenes()[mutationPoint] == 0
                    ? 1 : 0);
            int altMutationPoint = random.nextInt(chromosomeLength);
            parentChromosomes[1].getGenes()[altMutationPoint] = (parentChromosomes[1].getGenes()[altMutationPoint] == 0
                    ? 1 : 0);
        }
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public int getSize() {
        return size;
    }

    public double getFitnessScore() {
        return fitnessScore;
    }
}
