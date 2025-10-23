package Lab2;

public class StandardizedDataset extends Dataset{
    private Vector mean_in;
    private Vector std_in;
    private Vector mean_out;
    private Vector std_out;

    public StandardizedDataset (Vector mean_in, Vector std_in, Vector mean_out, Vector std_out){
        this.mean_in = mean_in;
        this.std_in = std_in;
        this.mean_out = mean_out;
        this.std_out = std_out;
    }

    
}
