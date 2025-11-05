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

    public Vector stdInput() {
        Vector mean = meanInput();
        Vector sumSq = new Vector(dim, 0.0);
        for (Record record : data) {
            Vector diff = record.getInput().substract(mean);
            sumSq = sumSq.add(diff.multiply(diff));
        }
        return sumSq.divide(data.size()).sqrt();
    }

    public double stdOutput() {
        double mean = meanOutput();
        double sumSq = 0.0;
        for (Record record : data) {
            double diff = record.getOutput() - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / data.size());
    }

    public StandardizedDataset standardize() {
        Vector mean_in = meanInput();
        Vector std_in = stdInput();
        double mean_out = meanOutput();
        double std_out = stdOutput();

        StandardizedDataset sd = new StandardizedDataset(dim, mean_in, std_in, mean_out, std_out);

        for (Record r : data) {
            Record transformed = sd.transform(r);
            sd.addRecord(transformed);
        }

        return sd;
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
