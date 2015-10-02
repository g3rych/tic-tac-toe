import javax.swing.*;

public class TTT {
    public static void main(String[] args) {
        Board board = new Board();
        GameMain gameMain = new GameMain(board);
        BoardView view = new BoardView(board, gameMain);
        new JFrame() {
            {
                setLocationRelativeTo(null);
                setVisible(true);
                add(view);
                pack();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        };
    }
}
