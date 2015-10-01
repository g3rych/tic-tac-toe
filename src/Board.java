public class Board {
    public static int ROWS = 3;
    public static int COLS = 3;
    Cell cells[][] = new Cell[ROWS][COLS];

    public Board() {
        init();

    }

    public void init() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new Cell(row, col);
                cells[row][col].paint();
            }
        }
    }
}
