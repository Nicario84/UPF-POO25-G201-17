public class RawDataset extends Dataset {

    public RawDataset(int dim) {
        super(dim);
    }

    public Vector meanInput() {
        Vector sum = new Vector(dim, 0.0);
        for (Record r : data) {
            sum = sum.add(r.getInput());
        }
        return sum.divide(data.size());
    }

    public double meanOutput() {
        double sum = 0.0;
        for (Record r : data) {
            sum += r.getOutput();
        }
        return sum / data.size();
    }

    public Vector stdInput() {
        Vector mean = meanInput();
        Vector sumSq = new Vector(dim, 0.0);
        for (Record r : data) {
            Vector diff = r.getInput().subtract(mean);
            sumSq = sumSq.add(diff.multiply(diff));
        }
        return sumSq.divide(data.size()).sqrt();
    }

    public double stdOutput() {
        double mean = meanOutput();
        double sumSq = 0.0;
        for (Record r : data) {
            double diff = r.getOutput() - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / data.size());
    }

    /**
     * Crear un StandardizedDataset basado en este RawDataset.
     */
    public StandardizedDataset standardize() {
        Vector mean_in = meanInput();
        Vector std_in = stdInput();
        double mean_out = meanOutput();
        double std_out = stdOutput();

        StandardizedDataset sd =
            new StandardizedDataset(dim, mean_in, std_in, mean_out, std_out);

        for (Record r : data) {
            sd.addRecord(sd.transform(r));
        }

        return sd;
    }

    @Override
    public Record transform(Record r) {
        // RawDataset no transforma nada
        return r;
    }

    @Override
    public double output(double y) {
        // No transformación inversa en RawDataset
        return y;
    }
}

