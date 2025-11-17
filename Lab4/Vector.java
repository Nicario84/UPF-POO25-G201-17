public class Vector {

    private double[] elems; 


    public Vector(double[] e) {
        this.elems = e; 
    }

    public Vector(int dim, double val) {
        this.elems = new double[dim];
        for (int i = 0; i < dim; i++) {
            elems[i] = val;
        }
    }

    // Dimension del vector
    public int getDim() {
        return elems.length;
    }

    public double get(int i) {
        return elems[i];
    }

    private void checkDim(Vector v) {
        if (this.elems.length != v.elems.length) {
            System.out.println("Vectors must have the same dimension.");  
        }
    }

    // Añadir vector
    public Vector add(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] + v.elems[i];
        }
        return new Vector(result);
    }

    // quitar vector
    public Vector subtract(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] - v.elems[i];
        }
        return new Vector(result);
    }

    // Multiplicar vectores
    public Vector multiply(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] * v.elems[i];
        }
        return new Vector(result);
    }

    // Dividir vector
    public Vector divide(Vector v) {
        checkDim(v);
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] / v.elems[i];
        }
        return new Vector(result);
    }

    // Multiplicar en escalar
    public Vector multiply_scalar(double scalar) {
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] * scalar;
        }
        return new Vector(result);
    }

    // Dividir en escalar
    public Vector divide(double scalar) {
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = this.elems[i] / scalar;
        }
        return new Vector(result);
    }

    // Raiz cuadrada
    public Vector sqrt() {
        double[] result = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            result[i] = Math.sqrt(this.elems[i]);
        }
        return new Vector(result);
    }

    // Signo de punto (·)
    public double dotProduct(Vector v) {
        checkDim(v);
        double sum = 0.0;
        for (int i = 0; i < elems.length; i++) {
            sum += this.elems[i] * v.elems[i];
        }
        return sum;
    }

    // Norma
    public double norm() {
        return Math.sqrt(this.dotProduct(this)); //reutilizamos el dotProduct para ahorrarnos codigo que escribir
    }

    // Aumentamos la dimensión de Vector para que incluya el parámetro que multplica el bias
    public Vector augment() {
        double[] augmented = new double[getDim() + 1];
        System.arraycopy(elems, 0, augmented, 0, getDim());
        augmented[getDim()] = 1.0;
        return new Vector(augmented);
    }

    // Passar a String
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elems.length; i++) {
            sb.append(elems[i]);
            if (i < elems.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}

