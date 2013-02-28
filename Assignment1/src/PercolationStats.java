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
        return mean() - (1.96 * stddev()) / T;
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / T;
    }

    public static void main(String[] args) {
        //args = new String[]{"50","30"};
        if (args.length != 2) {
            throw new IllegalArgumentException("Proper number of arguments is 2 e.g. java PercolationStats 200 100");
        }
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        System.out.println("Statistic for N = " + N + " and T = " + T);
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());

    }
}