public class TestDataset {
    public static void main(String[] args) {
        Dataset ds = new Dataset(2);
        ds.addRecord(new Record(new Vector(new double[]{1, 2}), 3));
        ds.addRecord(new Record(new Vector(new double[]{2, 3}), 5));
        ds.addRecord(new Record(new Vector(new double[]{3, 4}), 7));

        System.out.println("Original Dataset ===");
        System.out.println(ds);

        StandardizedDataset sd = ds.standardize();

        System.out.println("Standardized Dataset ===");
        System.out.println(sd);
    }
}
