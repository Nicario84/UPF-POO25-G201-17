public class Algorithm {
    private double learningRate;
    private double stoppingCriterion;

    public Algorithm() {
        this.learningRate = 0.01;
        this.stoppingCriterion = 1e-6;
    }

    public Vector gradient(Dataset ds, Model m) {
        int dim = ds.getRecord(0).getInput().getDim() + 1;
        double[] grad = new double[dim];
        
        for (int i = 0; i < ds.size(); i++) {
            Record r = ds.getRecord(i);
            Vector x = r.getInput().augment();
            double y = r.getOutput();
            double yHat = m.predict(r.getInput()).get(0);
            double error = yHat - y;
            for (int j = 0; j < dim; j++) {
                grad[j] += error * x.get(j);
            }
        }

        for (int j = 0; j < dim; j++) {
            grad[j] = grad[j] * learningRate / ds.size();
        }

        return new Vector(grad);
    }

    public Model solve(Dataset ds) {
        Model m = new Model(ds.getRecord(0).getInput().getDim());
        Vector grad = gradient(ds, m);
        while (grad.norm() > stoppingCriterion) {
            m.update(grad);
            grad = gradient(ds, m);
        }
        return m;
    }
}
