import java.util.Random;

public class StochasticGradientDescent extends Algorithm {

    private int batchSize;
    private int iterations;
    private Random random;

    public StochasticGradientDescent(double learningRate, int batchSize, int iterations) {
        super(learningRate);
        this.batchSize = batchSize;
        this.iterations = iterations;
        this.random = new Random();
    }

    /* Gradiente usando minibatch */
    private Vector stochasticGradient(Dataset ds, Model m) {

        int dim = ds.getRecord(0).getInput().getDim() + 1;
        double[] grad = new double[dim];

        int[] sample = random
                .ints(0, ds.size())
                .distinct()
                .limit(batchSize)
                .toArray();

        for (int idx : sample) {

            Record r = ds.transform(ds.getRecord(idx));
            Vector x = r.getInput().augment();
            double y = r.getOutput();

            double yHat = m.predict(r.getInput());
            double error = yHat - y;

            for (int j = 0; j < dim; j++) {
                grad[j] += error * x.get(j);
            }
        }

        for (int j = 0; j < dim; j++) {
            grad[j] = grad[j] * learningRate / batchSize;
        }

        return new Vector(grad);
    }

    @Override
    public Model solve(Dataset ds) {

        Model m = new Model(ds.getDim());

        for (int iter = 0; iter < iterations; iter++) {
            Vector grad = stochasticGradient(ds, m);
            m.update(grad);
        }

        return m;
    }
}
