import controller.GameController;
import controller.Level;
import model.Chessboard;
import view.ChessGameFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(),Level.AI_LittleHard);
            mainFrame.setVisible(true);
            mainFrame.addSaveButton(gameController);
            mainFrame.addLoadButton(gameController);
            mainFrame.addTurns(gameController);
        });
    }
}
