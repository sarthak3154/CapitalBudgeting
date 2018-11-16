package geneticalgorithm;

import java.util.Random;

public class Chromosome {
    private int length;
    private double fitnessScore;
    private int[] genes;

    Chromosome(int length) {
        this.length = length;
        genes = new int[length];
    }

    private int[] findRandomChromosome() {
        for (int i = 0; i < length; i++) {
            genes[i] = new Random().nextInt(Constants.GENE_VALUES_BOUND);
        }
        return genes;
    }

    boolean isValidChromosome() {
        return genes.length % 4 == 0 &&
                !(0.5 * genes[0] + genes[1] + 1.5 * genes[2] + 0.1 * genes[3] > 3.1) &&
                !(0.3 * genes[0] + 0.8 * genes[1] + 1.5 * genes[2] + 0.4 * genes[3] > 2.5) &&
                !(0.3 * genes[0] + 0.2 * genes[1] + 0.3 * genes[2] + 0.1 * genes[3] > 0.4);

    }

    int[] getValidChromosome() {
        int[] chromosome = findRandomChromosome();
        while (!isValidChromosome())
            chromosome = findRandomChromosome();
        return chromosome;
    }

    void calculateFitnessScore() {
        double sum = (0.2 * genes[0] + 0.3 * genes[1] + 0.5 * genes[2] + 0.1 * genes[3]);
        setFitnessScore(sum);
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public double getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
