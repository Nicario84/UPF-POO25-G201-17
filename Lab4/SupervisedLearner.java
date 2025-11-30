public class SupervisedLearner {
    private Algorithm algorithm;
    private Dataset dataset;
    private Model model;

    public SupervisedLearner(Algorithm a, Dataset d) {
        this.algorithm = a;
        this.dataset = d;
        // Inicializamos el modelo con ceros. El tamaño es dim+1
        this.model = new Model(dataset.getDim()); 
    }

    public Model solve() {
        this.model = algorithm.solve(dataset);
        return model;
    }

    public double predict(Vector x) {
        // Crear un Record temporal 
        Record rawRecord = new Record(x, 0.0);
        
        // Transformar el Record 
        Record transformedRecord = dataset.transform(rawRecord);
        Vector rt = transformedRecord.getInput();

        // Obtener la predicción del modelo 
        double yHat = model.predict(rt); 

        // transformada inversa
        return dataset.output(yHat);
    }

    public String toString() {
        return model.toString();
    }
    
}