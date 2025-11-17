public class StandardizedDataset extends Dataset{
    private Vector mean_in;
    private Vector std_in;
    private double mean_out;
    private double std_out;

    public StandardizedDataset(int dim, Vector mean_in, Vector std_in, double mean_out, double std_out){
        super(dim);
        this.mean_in = mean_in;
        this.std_in = std_in;
        this.mean_out = mean_out;
        this.std_out = std_out;
    }

    @Override
    public Record transform(Record r) {
        Vector x = r.getInput().subtract(mean_in).divide(std_in);
        double y = (r.getOutput() - mean_out) / std_out;
        return new Record(x, y);
    }

    @Override
    public double output(double y) {
        return y * std_out + mean_out;
    }

    
}
