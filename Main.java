package geneticalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static void initGeneticAlgorithm(int populationSize) {
        new GeneticAlgorithm(populationSize, Constants.DEFAULT_CHROMOSOME_LENGTH).init();
    }

    public static void main(String[] args) {
        System.out.println(":: Capital Budgeting ::\n\nEnter the population size (Or Press Enter to retrieve default 10) :");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int populationSize;
        try {
            populationSize = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            populationSize = Constants.DEFAULT_POPULATION_SIZE;
            e.printStackTrace();
        }
        initGeneticAlgorithm(populationSize);
    }
}
