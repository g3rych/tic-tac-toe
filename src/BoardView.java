import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    Board board;
    GameMain gameMain;


    public BoardView() {
        setLayout(new GridLayout(3, 3));
    }

    public BoardView(Board board, GameMain gameMain) {
        this();
        this.board = board;
        this.gameMain = gameMain;
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                board.cells[row][col] = new Cell(row, col);
                //board.cells[row][col].paint();
                add(board.cells[row][col]);
                board.cells[row][col].addActionListener(evt -> {
                    Cell source = (Cell) evt.getSource();
                    if (gameMain.isValidInput(source.getRow(), source.getCol()))
                        gameMain.playerMove(source.getRow(), source.getCol());
                    paint();
                    gameMain.checkWinner();
                });
            }
        }
    }

    public void paint() {
        for (int row = 0; row < Board.ROWS; row++)
            for (int col = 0; col < Board.COLS; col++)
                board.cells[row][col].paint();
    }
}
