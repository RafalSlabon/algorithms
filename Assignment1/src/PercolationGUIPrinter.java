import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PercolationGUIPrinter extends JFrame {

    public static final int MAX_SCREEN_SIZE = 850;

    private PercolationGUIPrinter(Percolation p) {
        super("PercolationGUIPrinter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MAX_SCREEN_SIZE, MAX_SCREEN_SIZE);
        setVisible(true);
        JPanel grid = new PercolationGrid(p);
        add(grid);
    }

    public static void print(final Percolation p) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PercolationGUIPrinter(p);
            }
        });
    }

    private static class PercolationGrid extends JPanel {
        public int squareSize;
        private Graphics2D g2d;
        private Percolation p;

        public PercolationGrid(Percolation p) {
            this.p = p;
            squareSize = (int)((double) (MAX_SCREEN_SIZE - 50) / p.N);
            setPreferredSize(new Dimension(400, 400));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g;
            createGrid();
        }

        private void createGrid() {
            Rectangle2D[][] grid = new Rectangle2D[p.N][p.N];
            for (int i = 0; i < p.N; i++) {
                for (int j = 0; j < p.N; j++) {
                    Color c = chooseColor(i, j);
                    grid[i][j] = drawSquare(j * squareSize,i * squareSize, c);
                }
            }
        }

        private Color chooseColor(int i, int j) {
            Color c = Color.BLACK;
            if (p.isFull(i + 1, j + 1)) {
                c = Color.BLUE;
            } else if (p.isOpen(i + 1, j + 1)) {
                c = Color.WHITE;
            }
            return c;
        }

//        private void changeColor(Rectangle2D s, Color c) {
//            g2d.setPaint(c);
//            g2d.fill(s);
//            g2d.draw(s);
//        }

        private Rectangle2D drawSquare(int x, int y, Color c) {
            Rectangle2D square = new Rectangle2D.Double(x, y, squareSize, squareSize);
            g2d.setPaint(c);
            g2d.fill(square);
            g2d.draw(square);
            return square;
        }
    }
}