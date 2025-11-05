public class SupervisedLearner {
    private Algorithm algorithm;
    private Dataset dataset;
    private Model model;

    public SupervisedLearner(Algorithm a, Dataset d) {
        this.algorithm = a;
        this.dataset = d;
    }

    public double train() {
        this.model = algorithm.solve(dataset);
        return 0.0;
    }

    public Model solve() {
        this.model = algorithm.solve(dataset);
        return model;
    }

    public Vector predict(Vector input) {
        return model.predict(input);
    }

    public String toString() {
        return model.toString();
    }
    
}
