package geneticalgorithm;

public class Main {

    private static void initGeneticAlgorithm() {
        new GeneticAlgorithm(Constants.DEFAULT_POPULATION_SIZE, Constants.DEFAULT_CHROMOSOME_LENGTH,
                Constants.MUTATION_PROBABILITY).init();
    }

    public static void main(String[] args) {
        System.out.println("::: Capital Budgeting Genetic Algorithm :::\n");
        initGeneticAlgorithm();
    }
}
