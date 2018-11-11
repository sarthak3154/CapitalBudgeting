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

    public String findRandomChromosome() {
        for (int i = 0; i < length; i++) {
            genes[i] = new Random().nextInt(Constants.GENE_VALUES_BOUND);
        }
        return Arrays.toString(genes);
    }

    public int calculateFitnessScore() {
        //TODO find the score based on fitness function
        return 0;
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
