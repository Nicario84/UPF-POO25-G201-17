public class SupervisedLearner {
    private Algorithm algorithm;
    private Dataset dataset;
    private Model model;

    public SupervisedLearner(Algorithm a, Dataset d) {
        this.algorithm = a;
        this.dataset = d;
        this.model = new Model(dataset.getDim());   // Inicializamos el modelo con ceros
    }

    public Model solve() {
        this.model = algorithm.solve(dataset);
        return model;
    }

    public double predict(Vector input) {
        Vector augmented = input.augment();
        return model.getParams().dotProduct(augmented);
    }

    public String toString() {
        return model.toString();
    }
    
}
