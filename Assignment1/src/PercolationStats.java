import java.util.Random;

public class PercolationStats {

    private int N;
    private int T;
    private double[] percolationThresholds;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;
        percolationThresholds = new double[T];
        for (int i = 0; i < T; i++) {
            double threshold = (double) monteCarloSimulation() / (N * N);
            percolationThresholds[i] = threshold;
        }
    }

    private int monteCarloSimulation() {
        Percolation p = new Percolation(N);
        while (!p.percolates()) {
            int r = generateRandomInt();
            int c = generateRandomInt();
            while (p.isOpen(r, c)) {
                r = generateRandomInt();
                c = generateRandomInt();
            }
            p.open(r, c);
        }
        return computeNumberOfOpenSites(p);
    }

    private int computeNumberOfOpenSites(Percolation p) {
        int numOfOpenSites = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (p.isOpen(r, c)) {
                    numOfOpenSites++;
                }
            }
        }
        return numOfOpenSites;
    }

    private int generateRandomInt() {
        return StdRandom.uniform(1, N + 1);
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(percolationThresholds);
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(percolationThresholds);
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
        PercolationStats stats = new PercolationStats(400, 1000);
        System.out.println("Mean " + stats.mean());
        System.out.println("Stddev " + stats.stddev());
    }
}