import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Haibo Yu on 02/24/2017.
 */
public class Percolation {

    // The number of the grid
    private int siteSize;

    // The 2-dim array to store the open status for every site
    private boolean[][] openStatus;

    // The number of open sites
    private int numOfOpenSites;

    private WeightedQuickUnionUF siteOperator;


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Error: The number of site must big than 0!");
        siteSize = n;
        numOfOpenSites = 0;

        siteOperator = new WeightedQuickUnionUF(siteSize*siteSize+2);

        openStatus = new boolean[siteSize+1][siteSize+1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                openStatus[i][j] = false;
            }
        }
    }

    /**
     * open the site if it's still not open
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validateSitePosition(row, col);
        if (openStatus[row][col]) {
            return;
        }

        numOfOpenSites++;

        openStatus[row][col] = true;

        // Connect to top if row>1
        if (row > 1 && openStatus[row - 1][col]) {
            siteOperator.union((row - 1) * siteSize + col, (row - 2) * siteSize + col);
        }
        // Connect to bottom if row<siteSize
        if (row < siteSize && openStatus[row + 1][col]) {
            siteOperator.union((row - 1) * siteSize + col, (row) * siteSize + col);
        }
        // Connect to left if col>1
        if (col > 1 && openStatus[row][col - 1]) {
            siteOperator.union((row - 1) * siteSize + col, (row - 1) * siteSize + col - 1);
        }
        // Connect to right if col<siteSize
        if (col < siteSize && openStatus[row][col + 1]) {
            siteOperator.union((row - 1) * siteSize + col, (row - 1) * siteSize + col + 1);
        }
        // Connect to top virtual site if row=1
        if (row == 1) {
            siteOperator.union((row - 1) * siteSize + col, 0);
        }
        // Connect to bottom virtual site if row=siteSize
        if (row == siteSize) {
            siteOperator.union((row - 1) * siteSize + col, siteSize * siteSize + 1);
        }
    }

    /**
     * Check if the pot(row,col) is open
     * @param row The row
     * @param col The col
     * @return The open status of the site
     */
    public boolean isOpen(int row, int col) {
        validateSitePosition(row, col);
        return openStatus[row][col];
    }

    /**
     * Check if the pot is full
     * @param row The row
     * @param col The col
     * @return The full status of the site
     */
    public boolean isFull(int row, int col) {
        validateSitePosition(row, col);
        return siteOperator.connected((row - 1) * siteSize + col, 0);
    }

    /**
     * Validate if the postition are correct
     * @param row The row
     * @param col The column
     */
    private void validateSitePosition(int row, int col) {
        if (row <= 0 || row > siteSize || col <= 0 || col > siteSize) {
            throw new IndexOutOfBoundsException("The row number and column number must be less than total number!");
        }
    }


    /**
     * The number of open sites
     * @return The number of open sties
     */
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    /**
     * Check if the sites percolates, when the top virtual node connect
     * to the bottom birtual node means it percolates
     * @return The status of if it percolate
     */
    public boolean percolates() {
        return siteOperator.connected(siteSize * siteSize + 1, 0);
    }

    public static void main(String[] args) {
        int size = StdIn.readInt();
        Percolation percolation = new Percolation(size);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                StdOut.println(row + " " + col);
            }
        }
    }
}
