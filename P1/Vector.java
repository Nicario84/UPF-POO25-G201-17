public class Vector {

    private double[] elems; // array of doubles representing vector elements


    // Constructor using an existing array
    public Vector(double[] e) {
        this.elems = e; 
    }

    // Constructor with dimension and initial value
    public Vector(int dim, double val) {
        this.elems = new double[dim];
        for (int i = 0; i < dim; i++) {
            elems[i] = val;
        }
    }

    // Return dimension of vector
    public int getDim() {
        return elems.length;
    }

    // Add another vector
    public Vector add(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] + v.elems[i];
        }
        return new Vector(result);
    }

    // Subtract another vector
    public Vector subtract(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] - v.elems[i];
        }
        return new Vector(result);
    }

    // Multiply element-wise with another vector
    public Vector multiply(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] * v.elems[i];
        }
        return new Vector(result);
    }

    // Divide element-wise with another vector
    public Vector divide(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] / v.elems[i];
        }
        return new Vector(result);
    }

    // Multiply with scalar
    public Vector multiply(double scalar) {
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] * scalar;
        }
        return new Vector(result);
    }

    // Divide with scalar
    public Vector divide(double scalar) {
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] / scalar;
        }
        return new Vector(result);
    }

    // Square root of each element
    public Vector sqrt() {
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = Math.sqrt(this.elems[i]);
        }
        return new Vector(result);
    }

    // Dot product
    public double dotProduct(Vector v) {
        checkDim(v);
        double sum = 0.0;
        for (int i = 0; i < elems.length; i++) {
            sum += this.elems[i] * v.elems[i];
        }
        return sum;
    }

    // Norm (magnitude)
    public double norm() {
        return Math.sqrt(this.dotProduct(this));
    }

    // Convert vector to string
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elems.length; i++) {
            sb.append(elems[i]);
            if (i < elems.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Helper method: check if dimensions match
    private void checkDim(Vector v) {
        if (this.elems.length != v.elems.length) {
            throw new IllegalArgumentException("Vectors must have the same dimension.");
        }
    }
}

