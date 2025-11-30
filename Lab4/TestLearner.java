public class TestLearner {
    public static void main(String[] args) {
        // Creamos vectores de entrada
        Vector input1 = new Vector(new double[]{1.0, 2.0});
        Vector input2 = new Vector(new double[]{2.0, 1.0});
        Vector input3 = new Vector(new double[]{3.0, 1.0});

        // Creamos los records con salidas double
        Record r1 = new Record(input1, 5.0);
        Record r2 = new Record(input2, 4.0);

        // Creamos el dataset con los record
        RawDataset rawData = new RawDataset(2);
        rawData.addRecord(r1);
        rawData.addRecord(r2);
        System.out.println("\nDataset:");
        for (int i = 0; i < rawData.size(); i++) {
            System.out.println(rawData.getRecord(i));
        }

        // Ahora creamos el algoritmo y resolvemos el modelo
        Algorithm algo = new GradientDescent(0.05, 0.001);
        Model model = algo.solve(rawData);
        System.out.println("\nParametros del modelo despues del training: ");
        System.out.println(model);

        // Probamos la predicción con el modelo
        double prediction = model.predict(input3);
        System.out.println("\nPrediccion del input " + input3 + ": " + prediction);

        // Usamos SupervisedLearner
        SupervisedLearner learner = new SupervisedLearner(algo, rawData);
        learner.solve();
        System.out.println("\nModelo de aprendizaje: ");
        System.out.println(learner);

        double learnerPrediction = learner.predict(input3);
        System.out.println("\nPrediccion de aprendizaje del input " + input3 + ": " + learnerPrediction);
    }
}
