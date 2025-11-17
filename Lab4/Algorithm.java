public abstract class Algorithm {
    protected double learningRate;
    protected double stoppingCriterion;

    public Algorithm(double learningRate, double stoppingCriterion) {
        this.learningRate = learningRate;   
        this.stoppingCriterion = stoppingCriterion;
    }

    public Vector gradient(Dataset ds, Model m) {
        int dim = ds.getRecord(0).getInput().getDim() + 1;
        double[] grad = new double[dim];
        
        for (int i = 0; i < ds.size(); i++) {
            Record r = ds.getRecord(i);
            Vector x = r.getInput().augment();
            double y = r.getOutput();
            double yHat = m.predict(r.getInput());
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
        Model m = new Model(ds.getDim());
        Vector grad = gradient(ds, m);
        
        while (grad.norm() > stoppingCriterion) {
            m.update(grad);
            grad = gradient(ds, m);
        }
        return m;
    }
}
