import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author Haibo Yu on 02/24/2017.
 */
public class PercolationStats {
    // Array to store the threhold values for every experiment
    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (trials <= 0 || n <= 0) {
            throw new java.lang.IllegalArgumentException("Error: n and trials must big than 0!");
        }
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            thresholds[i] = 0;
        }
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(row, col)) {
                    thresholds[i]++;
                    perc.open(row, col);
                }
            }
            thresholds[i] = thresholds[i] / (n * n);
        }

    }
    public double mean() {
        return StdStats.mean(thresholds);
    }
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        double temp = 1.96 * stddev
                / java.lang.StrictMath.pow(thresholds.length, 1.0 / 2);
        return mean - temp;
    }
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        double temp = 1.96 * stddev
                / java.lang.StrictMath.pow(thresholds.length, 1.0 / 2);
        return mean + temp;
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percStates = new PercolationStats(n, trials);
        StdOut.println("mean = " + percStates.mean());
        StdOut.println("stddev = " + percStates.stddev());
        StdOut.println("95% confidence interval = " + percStates.confidenceLo()
                + ", " + percStates.confidenceHi());
    }
}
