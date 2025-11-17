import java.util.ArrayList;

public abstract class Dataset {
    private int dim;
    private ArrayList<Record> data;

    public Dataset(int d) {
        this.dim = d;
        this.data = new ArrayList<>();
    }

    public int getDim() {
        return dim;
    }

    public int size() {
        return data.size();
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

    public abstract Record transform(Record r);

    public abstract double output(double y);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Dataset:\n");
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
