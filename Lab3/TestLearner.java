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
        Dataset ds = new Dataset(2);
        ds.addRecord(r1);
        ds.addRecord(r2);
        System.out.println("\nDataset:");
        for (int i = 0; i < ds.size(); i++) {
            System.out.println(ds.getRecord(i));
        }

        // Ahora creamos el algoritmo y resolvemos el modelo
        Algorithm algo = new Algorithm();
        Model model = algo.solve(ds);
        System.out.println("\nParametros del modelo despues del training: ");
        System.out.println(model);

        // Probamos la predicción con el modelo
        Vector testInput = input3;
        Vector prediction = model.predict(testInput);
        System.out.println("\nPrediccion del input " + testInput + ": " + prediction.get(0));

        // Usamos SupervisedLearner
        SupervisedLearner learner = new SupervisedLearner(algo, ds);
        learner.train();
        System.out.println("\nModelo de aprendizaje: ");
        System.out.println(learner);

        Vector learnerPrediction = learner.predict(testInput);
        System.out.println("\nPrediccion de aprendizaje del input " + testInput + ": " + learnerPrediction.get(0));
    }
}
