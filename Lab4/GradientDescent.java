public class GradientDescent extends Algorithm {

    private double stoppingCriterion;

    public GradientDescent(double learningRate, double stoppingCriterion) {
        super(learningRate);
        this.stoppingCriterion = stoppingCriterion;
    }

    private Vector gradient(Dataset ds, Model m) {

        int dim = ds.getRecord(0).getInput().getDim() + 1;
        double[] grad = new double[dim];

        for (int i = 0; i < ds.size(); i++) {
            Record r = ds.transform(ds.getRecord(i));  
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

    @Override
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
