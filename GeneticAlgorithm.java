package geneticalgorithm;

public class GeneticAlgorithm {
    private int maxGenerations = 1000;
    private int populationSize;
    private int chromosomeLength;

    public GeneticAlgorithm(int populationSize, int chromosomeLength) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
    }

    public void init() {
        final Population population = Population.createInstance(populationSize);
        population.initPopulation(chromosomeLength);
    }
}
