public abstract class Algorithm {

    protected double learningRate;

    public Algorithm(double learningRate) {
        this.learningRate = learningRate;
    }

    /**
    GD o SGD implementa su propia forma de entrenar
     */
    public abstract Model solve(Dataset ds);
}
