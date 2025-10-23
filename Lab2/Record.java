package Lab2;

public class Record {
    private Vector input;
    private double output;


    public Record(Vector i, double o) {
        this.input = i;
        this.output = o;
    }

    public Vector getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    public String toString() {
        return "Record(Input: " + input.toString() + ", Output: " + output + ")";
    }
}
