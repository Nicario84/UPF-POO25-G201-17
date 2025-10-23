package Lab2;

import java.util.ArrayList;

public class Dataset {
    private int dim;
    private ArrayList<Record> data;

    public Dataset(int d) {
        this.dim = d;
        this.data = new ArrayList<>();
    }

    public int getDim() {
        return dim;
    }

    public ArrayList<Record> getData() {
        return data;
    }

    public void addRecord(Record r) {
        data.add(r);
    }
    
    public Record getRecord(int i) {
        return data.get(i);
    }

    public Vector meanInput() {
        Vector sum = new Vector(dim, 0.0);
        for (int i = 0; i < data.size(); i++) {
            sum = sum.add(data.get(i).getInput());
        }
        return sum.divide(data.size());

    }

    public double meanOutput() {
        double sum = 0.0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).getOutput();
        }
        return sum / data.size();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Dataset:\n");
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}

