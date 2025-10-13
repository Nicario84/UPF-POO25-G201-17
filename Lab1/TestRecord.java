public class TestRecord {

    public static void main(String[] args) {

        System.out.println("Vector class =\n\n");

        Vector v1 = new Vector(new double[]{1, 2, 3});
        Vector v2 = new Vector(new double[]{4, 5, 6});

        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);

        System.out.println("Càlculos =\n\n");

        System.out.println("v1 + v2 = " + v1.add(v2));
        System.out.println("v1 - v2 = " + v1.subtract(v2));
        System.out.println("v1 * v2 = " + v1.multiply(v2));
        System.out.println("v1 / v2 = " + v1.divide(v2));
        System.out.println("v1 * 2 = " + v1.multiply_scalar(2));
        System.out.println("v2 / 2 = " + v2.divide(2));
        System.out.println("sqrt(v1) = " + v1.sqrt());
        System.out.println("dot(v1, v2) = " + v1.dotProduct(v2));
        System.out.println("norm(v1) = " + v1.norm());

        // ----------------------------
        // Pruebas de record
        // ----------------------------
        System.out.println("\n=== Prueba Record ===");

        Record r1 = new Record(v1, 10.5);
        System.out.println("Record 1: " + r1);

        System.out.println("Input of Record 1: " + r1.getInput());
        System.out.println("Output of Record 1: " + r1.getOutput());
    }
}

