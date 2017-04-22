import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Haibo Yu on 02/24/2017.
 */
public class Percolation {

    //The number of the grid
    private int siteSize;

    //The 2-dim array to store the open status for every site
    private boolean[][] openStatus;

    //The 2-dim array to store the full status for every site
    private boolean[][] fullStatus;

    //The number of open sites
    private int numOfOpenSites;

    private WeightedQuickUnionUF siteOperator;


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Error: The number of site must big than 0!");
        siteSize = n;

        siteOperator = new WeightedQuickUnionUF(siteSize);
        openStatus = new boolean[siteSize][siteSize];
        fullStatus = new boolean[siteSize][siteSize];
    }
    public void open(int row, int col) {
        validateSitePosition(row,col);

    }

    /**
     * Check if the pot(row,col) is open
     * @param row The row
     * @param col The col
     * @return The open status of the site
     */
    public boolean isOpen(int row, int col) {
        validateSitePosition(row,col);
        return openStatus[row - 1][col - 1];
    }

    /**
     * Check if the pot is full
     * @param row The row
     * @param col The col
     * @return The full status of the site
     */
    public boolean isFull(int row, int col){
        validateSitePosition(row,col);
        return fullStatus[row - 1][col - 1];
    }

    /**
     * Validate if the postition are correct
     * @param row The row
     * @param col The column
     */
    private void validateSitePosition(int row, int col){
        if(row <0 || row >siteSize || col <0 || col > siteSize){
            throw new IndexOutOfBoundsException("The row number and column number must be less than total number!");
        }
    }


    /**
     * The number of open sites
     * @return The number of open sties
     */
    public int numberOfOpenSites(){
        return numOfOpenSites;
    }

    /**
     * Check if the sites percolates
     * @return The status of if it percolate
     */
    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {

    }
}
