import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PercolationTest {

    private Percolation p;
    private int N;

    @Before
    public void setup() {
        N = 5;
        p = new Percolation(N);
    }

    @Test
    public void on_start_all_cells_should_be_blocked_and_system_should_not_percolates() {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                assertFalse(p.isFull(row, col));
                assertFalse(p.isOpen(row, col));
            }
        }
        assertFalse(p.percolates());
    }

    @Test
    public void should_throw_ex_when_parameters_of_isFull_are_out_of_range() {
        assertInRangeIsFull(1, N);

        assertOutOfRangeIsFull(-1, 1);
        assertOutOfRangeIsFull(0, 1);
        assertOutOfRangeIsFull(N + 1, 1);

        assertOutOfRangeIsFull(1, -1);
        assertOutOfRangeIsFull(1, 0);
        assertOutOfRangeIsFull(1, N + 1);
    }

    @Test
    public void should_throw_ex_when_parameters_of_open_are_out_of_range() {
        assertInRangeOpen(1, N);

        assertOutOfRangeOpen(-1, 1);
        assertOutOfRangeOpen(0, 1);
        assertOutOfRangeOpen(N + 1, 1);

        assertOutOfRangeOpen(1, -1);
        assertOutOfRangeOpen(1, 0);
        assertOutOfRangeOpen(1, N + 1);
    }

    @Test
    public void should_throw_ex_when_parameters_of_isOpen_are_out_of_range() {
        assertInRangeIsOpen(1, N);

        assertOutOfRangeIsOpen(-1, 1);
        assertOutOfRangeIsOpen(0, 1);
        assertOutOfRangeIsOpen(N + 1, 1);

        assertOutOfRangeIsOpen(1, -1);
        assertOutOfRangeIsOpen(1, 0);
        assertOutOfRangeIsOpen(1, N + 1);
    }


    @Test
    public void all_top_row_cells_after_open_should_be_full_open() {
        for(int i=1;i<=N;i++){
            p.open(1, i);
            assertTrue(p.isFull(1, i));
        }
    }

    @Test
    public void blocked_cell_after_open_cell_should_be_open() {;
        openAndCheckIfOpen(1,1);
    }

    @Test
    public void if_there_is_path_of_open_cells_from_top_to_bottom_system_should_percolate() {
        // X * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(1, 1);
        assertFalse(p.percolates());

        // X * * * *
        // X * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 1);
        assertFalse(p.percolates());

        // X * * * *
        // X * * * *
        // X * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(3, 1);
        assertFalse(p.percolates());

        // X * * * *
        // X * * * *
        // X * * * *
        // X * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(4, 1);
        assertFalse(p.percolates());

        // X * * * *
        // X * * * *
        // X * * * *
        // X * * * *
        // X * * * *
        openAndCheckIfIsFullOpen(5, 1);
        assertTrue(p.percolates());
        printPercolationGrid();
    }

    @Test
    public void should_full_open_neighboring_sites() {
        openAndCheckIfOpen(2,1);
        openAndCheckIfIsFullOpen(1,1);
        printPercolationGrid();
        assertTrue(p.isFull(2,1));
    }

    private void openAndCheckIfOpen(int i, int j){
        assertFalse(p.isOpen(i, j));
        p.open(i,j);
        assertTrue(p.isOpen(i, j));
    }

    @Test
    public void should_full_open_from_up_site() {
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(1, 3);

        //full open from up
        // * * X * *
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 3);
        printPercolationGrid();
    }

    @Test
    public void should_full_open_from_left_site() {
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(1, 3);

        // * * X * *
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 3);

        //full open from left

        // * * X * *
        // * * X X *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 4);
        printPercolationGrid();
    }

    @Test
    public void should_full_open_from_right_site() {
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(1, 3);

        // * * X * *
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 3);

        //full open from right

        // * * X * *
        // * X X * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 2);
        printPercolationGrid();
    }

    @Test
    public void should_full_open_from_down_site() {
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(1, 3);

        // * * X * *
        // * * X * *
        // * * * * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 3);

        // * * X * *
        // * * X * *
        // * * X * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(3, 3);

        // * * X * *
        // * * X * *
        // * X X * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(3, 2);

        // * * X * *
        // * * X * *
        // X X X * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(3, 1);

        //full open from down

        // * * X * *
        // X * X * *
        // X X X * *
        // * * * * *
        // * * * * *
        openAndCheckIfIsFullOpen(2, 1);
        printPercolationGrid();
    }

    private void openAndCheckIfIsFullOpen(int i, int j) {
        assertFalse(p.isOpen(i, j));
        p.open(i,j);
        assertTrue(p.isOpen(i, j));
        assertTrue(p.isFull(i,j));
    }

    private void assertOutOfRangeIsFull(int r, int c) {
        try {
            p.isFull(r, c);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void assertInRangeIsFull(int r, int c) {
        p.isFull(r, c);
    }

    private void assertOutOfRangeOpen(int r, int c) {
        try {
            p.open(r, c);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void assertInRangeOpen(int r, int c) {
        p.open(r, c);
    }

    private void assertOutOfRangeIsOpen(int r, int c) {
        try {
            p.isOpen(r, c);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void assertInRangeIsOpen(int r, int c) {
        p.isOpen(r, c);
    }

    private void printPercolationGrid() {
        new PercolationPrinter(p).print();
    }
}
