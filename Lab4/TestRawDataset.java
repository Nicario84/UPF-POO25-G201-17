public class TestRawDataset {
    public static void main(String[] args) {
        // Instanciar RawDataset (ya que Dataset es abstracta)
        RawDataset rawData = new RawDataset(2);

        rawData.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 3.0));
        rawData.addRecord(new Record(new Vector(new double[]{2.0, 3.0}), 6.0));
        rawData.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 9.0));

        System.out.println("=== Original Dataset (Raw) ===");
        System.out.println(rawData.toString());

        // Demostrar los cálculos internos del RawDataset
        System.out.println("Cálculos de RawDataset:");
        System.out.println("Mean Input: " + rawData.meanInput().toString());
        System.out.println("Std Input: " + rawData.stdInput().toString());
        System.out.println("Mean Output: " + rawData.meanOutput());
        System.out.println("Std Output: " + rawData.stdOutput());

        // Estandarizar el dataset (llamando al método de RawDataset)
        StandardizedDataset sdData = rawData.standardize();

        System.out.println("\n=== Standardized Dataset ===");
        // Los registros de sdData ya están transformados (media 0, desviación 1)
        System.out.println(sdData.toString());
        
        // Demostrar la transformación inversa (output)
        double standardizedPrediction = 0.0;
        double originalPrediction = sdData.output(standardizedPrediction);
        System.out.printf("Test Output: 0.0 (estandarizado) se traduce a la media original: %.4f\n", originalPrediction);
        
        // Test con otra predicción
        double testPrediction = 1.0;
        double originalTest = sdData.output(testPrediction);
        System.out.printf("Test Output: 1.0 (estandarizado) se traduce a: %.4f\n", originalTest);
    }
}