public class Model {
    private Vector params;

    public Model(int dim) {
        double[] initial = new double[dim + 1];
        for (int i = 0; i < initial.length; i++) {
            initial[i] = 0.0;
        }
        this.params = new Vector(initial);
    }

    public Vector getParams() {
        return params;
    }

    public Vector predict(Vector input) {
        Vector augmented = input.augment();
        double[] result = new double[1];
        double sum = 0;
        for (int i = 0; i < augmented.getDim(); i++) {
            sum += augmented.get(i) * params.get(i);
        }
        result[0] = sum;
        return new Vector(result);
    }

    public void update(Vector gradient) {
        this.params = this.params.substract(gradient);
    }

    public String toString() {
        return params.toString();
    }
}
