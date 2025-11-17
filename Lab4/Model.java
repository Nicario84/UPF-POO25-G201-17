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

    public double predict(Vector input) {
        Vector augmented = input.augment();
        return params.dotProduct(augmented);
    }

    public void update(Vector gradient) {
        this.params = this.params.subtract(gradient);
    }

    public String toString() {
        return params.toString();
    }
}
