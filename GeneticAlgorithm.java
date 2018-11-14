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
        population.calculateFitness();
        population.performSelection();
        Chromosome[] parents = population.getParents();
        //TODO crossover and mutation
    }

}
