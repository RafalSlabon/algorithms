import java.util.Random;

public class PercolationStats {

    private int N;
    private int T;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;

        Percolation p = new Percolation(N);

        while(!p.percolates()){
        new PercolationPrinter(p).print();

        int r = generateRandomInt();
        int c = generateRandomInt();
        while(p.isOpen(r,c)){
            r = generateRandomInt();
            c = generateRandomInt();
        }
        p.open(r,c);
        new PercolationPrinter(p).print();
        }
        new PercolationPrinter(p).print();
    }

    private int generateRandomInt() {
        return new Random().nextInt(N) + 1;
    }


    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return 0;
    }

    /**
     * sample standard deviation of percolation threshold
     */

    public double stddev() {
        return 0;
    }

    /**
     * returns lower bound of the 95% confidence interval
     */

    public double confidenceLo() {
        return 0;
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return 0;
    }

    public static void main(String[] args) {
      new PercolationStats(5, 1);
    }
}