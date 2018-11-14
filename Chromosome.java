package geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

public class Chromosome {
    private int length;
    private int fitnessScore;
    private int[] genes;

    Chromosome(int length) {
        this.length = length;
        genes = new int[length];
    }

    private String findRandomChromosome() {
        for (int i = 0; i < length; i++) {
            genes[i] = new Random().nextInt(Constants.GENE_VALUES_BOUND);
        }
        return Arrays.toString(genes);
    }

    private boolean isValid(String chromosome) {
        if (chromosome.length() % 4 != 0) {
            return false;
        }
        char[] p1Chromosome = chromosome.substring(0, chromosome.length() / 3).toCharArray();
        if (0.5 * p1Chromosome[0] + p1Chromosome[1] + 1.5 * p1Chromosome[2] + 0.1 * p1Chromosome[3] > 3.1) {
            return false;
        }

        char[] p2Chromosome = chromosome.substring(chromosome.length() / 3, 2 * chromosome.length() / 3).toCharArray();
        if (0.3 * p2Chromosome[0] + 0.8 * p2Chromosome[1] + 1.5 * p2Chromosome[2] + 0.4 * p2Chromosome[3] > 2.5) {
            return false;
        }

        char[] p3Chromosome = chromosome.substring(2 * chromosome.length() / 3, chromosome.length()).toCharArray();
        return !(0.3 * p3Chromosome[0] + 0.2 * p3Chromosome[1] + 0.3 * p3Chromosome[2] + 0.1 * p3Chromosome[3] > 0.4);
    }

    String getValidChromosome() {
        String chromosome = findRandomChromosome();
        while (!isValid(chromosome))
            chromosome = findRandomChromosome();
        return chromosome;
    }

    int calculateFitnessScore() {
        int sum = 0;
        for (int i = 0; i < genes.length; i += 4) {
            sum += (0.2 * genes[i] + 0.3 * genes[i + 1] + 0.5 * genes[i + 2] + 0.1 * genes[i + 3]);
        }
        return sum;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
