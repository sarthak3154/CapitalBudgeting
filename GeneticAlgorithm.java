package geneticalgorithm;

public class GeneticAlgorithm {
    private int maxGenerations = 1000;
    private int populationSize;
    private int chromosomeLength;
    private Population population;

    GeneticAlgorithm(int populationSize, int chromosomeLength) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
    }

    void init() {
        population = Population.createInstance(populationSize);
        population.initPopulation(chromosomeLength);
        while (!isTerminationConditionMet()) {
            population.calculateFitness();
            population = population.generate();
        }
    }

    private boolean isTerminationConditionMet() {
        //TODO termination condition
        return false;
    }

}
