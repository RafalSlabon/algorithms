public class Percolation {
    int N;
    private UF unionFind;
    private SiteStatus[][] grid;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        this.unionFind = new UF(unionFindSize());
        createBlockedGrid();
        connectTopElements();
        connectBottomElements();
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        validateRange(i, j);
        grid[i - 1][j - 1] = SiteStatus.OPEN;

        //site up
        int i1 = i - 2;
        int j1 = j - 1;
        if(i1 >=0 && j1 >= 0 && grid[i1][j1] == SiteStatus.OPEN){
            if(isFull(i - 1, j)){
                unionFind.union(topElement(), site(i, j));
            }
        }

        //site left
        i1 = i - 1;
        j1 = j - 2;
        if(i1 >=0 && j1 >= 0 && j < N && grid[i1][j1] == SiteStatus.OPEN){
            if(isFull(i, j - 1)){
                unionFind.union(topElement(), site(i, j));
            }
        }

        //site right
        i1 = i - 1;
        j1 = j;
        if(i1 >=0 && j1 >= 0 && j < N && grid[i1][j1] == SiteStatus.OPEN){
            if(isFull(i, j + 1)){
                unionFind.union(topElement(), site(i, j));
            }
        }

        //site down
        i1 = i;
        j1 = j - 1;
        if(i1 >=0 && i1< N && j1 >= 0 && j1 < N && grid[i1][j1] == SiteStatus.OPEN){
            if(isFull(i + 1, j)){
                unionFind.union(topElement(), site(i, j));
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validateRange(i, j);
        return grid[i - 1][j - 1] == SiteStatus.OPEN;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validateRange(i, j);
        return isOpen(i, j) && unionFind.connected(topElement(), site(i, j));
    }

    private int site(int i, int j) {
        return (i - 1) * N + j;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(topElement(), bottomElement());
    }

    private void createBlockedGrid() {
        this.grid = new SiteStatus[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = SiteStatus.BLOCKED;
            }
        }
    }

    private void connectBottomElements() {
        int bottomElement = bottomElement();
        for (int i = bottomElement - 1; i >= bottomElement - N; i--) {
            unionFind.union(bottomElement, i);
        }
    }

    private void connectTopElements() {
        for (int i = 1; i <= N; i++) {
            unionFind.union(topElement(), i);
        }
    }

    private int topElement() {
        return 0;
    }

    private int bottomElement() {
        return unionFindSize() - 1;
    }

    private int unionFindSize() {
        return N * N + 2;
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

    private enum SiteStatus {
        BLOCKED, OPEN
    }
}
