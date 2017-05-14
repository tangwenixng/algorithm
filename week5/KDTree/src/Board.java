import java.util.Stack;

/**
 * @author Haibo Yu on 05/08/2017.
 */
public class Board {
    private int size;
    private final int[][] tiles;

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        size = blocks.length;
        tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = blocks[i][j];
            }
        }
    }

    private int[][] createGoalBoard() {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = goalValueAt(i, j);
            }
        }

        return array;
    }

    // (where blocks[i][j] = block in row i, column j)
    // board dimension n
    public int dimension() {
        return size;
    }

    // number of blocks out of place
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != goalValueAt(i, j) && !isEnd(i, j)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private int goalValueAt(int i, int j) {
        if (isEnd(i, j)) {
            return 0;
        }
        return 1 + i * size + j;
    }

    private boolean isEnd(int i, int j) {
        return i == size - 1 && j == size - 1;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = tiles[i][j];
                if (value != 0 && value != goalValueAt(i, j)) {
                    int initialX = (value - 1) / size;
                    int initialY = value - 1 - initialX * size;
                    int distance = Math.abs(i - initialX)
                            + Math.abs(j - initialY);
                    sum += distance;
                }
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int[][] second = this.createGoalBoard();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != second[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board board = new Board(tiles);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (tiles[i][j] != 0 && tiles[i][j + 1] != 0) {
                    board.swap(i, j, i, j + 1);
                    return board;
                }
            }
        }

        return board;
    }

    private boolean swap(int i, int j, int it, int jt) {
        if (it < 0 || it >= size || jt < 0 || jt >= size) {
            return false;
        }
        int temp = tiles[i][j];
        tiles[i][j] = tiles[it][jt];
        tiles[it][jt] = temp;
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;
        boolean titleEquals = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != that.tiles[i][j]) {
                    titleEquals = false;
                }
            }
        }
        return this.size == that.size && titleEquals;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int i0 = 0, j0 = 0;
        boolean found = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        Stack<Board> boards = new Stack<Board>();
        Board board = new Board(tiles);
        boolean isNeighbor = board.swap(i0, j0, i0 - 1, j0);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(tiles);
        isNeighbor = board.swap(i0, j0, i0, j0 - 1);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(tiles);
        isNeighbor = board.swap(i0, j0, i0 + 1, j0);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(tiles);
        isNeighbor = board.swap(i0, j0, i0, j0 + 1);
        if (isNeighbor) {
            boards.push(board);
        }

        return boards;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
