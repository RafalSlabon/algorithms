public class Percolation {
    int N;
    private UF grid;
    private boolean[] openSites;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        this.grid = new UF(gridSize());
        this.openSites = new boolean[gridSize()];
    }

    // open site (row i, column j) if it is not already
    public void open(int r, int c) {
        validateRange(r, c);
        if (!isOpen(r, c)) {
            doOpen(r, c);
        }
    }

    private void doOpen(int r, int c) {
        int site = site(r, c);
        openSites[site] = true;
        if (r == 1) {
            grid.union(topElement(), site);
        }
        if (r == N) {
            grid.union(bottomElement(), site);
        }
        int up = r - 1;
        if (r > 1 && isOpen(up, c)) {
            grid.union(site(up, c), site);
        }
        int left = c - 1;
        if (c > 1 && isOpen(r, left)) {
            grid.union(site(r, left), site);
        }
        int right = c + 1;
        if (c < N && isOpen(r, right)) {
            grid.union(site(r, right), site);
        }
        int down = r + 1;
        if (r < N && isOpen(down, c)) {
            grid.union(site(down, c), site);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validateRange(i, j);
        int site = site(i, j);
        return openSites[site];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validateRange(i, j);
        boolean isConnectedWithTop = grid.connected(topElement(), site(i, j));
        boolean isConnectedWithBottom = grid.connected(bottomElement(), site(i, j));
        return isOpen(i, j) && (isConnectedWithTop || isConnectedWithBottom);
    }

    private int site(int i, int j) {
        return (i - 1) * N + j;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(topElement(), bottomElement());
    }

    private int topElement() {
        return 0;
    }

    private int bottomElement() {
        return gridSize() - 1;
    }

    private int gridSize() {
        int top = 1;
        int bottom = 1;
        return N * N + top + bottom;
    }

    private void validateRange(int row, int col) {
        validateRowRange(row);
        validateColRange(col);
    }

    private void validateColRange(int col) {
        if (col < 1 || col > N) {
            throw new IndexOutOfBoundsException("Column is invalid! Should <1,N>");
        }
    }

    private void validateRowRange(int row) {
        if (row < 1 || row > N) {
            throw new IndexOutOfBoundsException("Row is invalid! Should be <1,N>");
        }
    }
}
