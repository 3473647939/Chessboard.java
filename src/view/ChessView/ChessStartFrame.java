package view.ChessView;

import controller.GameController;
import controller.Level;
import model.Chessboard;
import view.ChessGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessStartFrame extends JFrame implements ActionListener {
    private JButton startButton_TwoPlayers;
    private JButton startButton_AiSimple;
    private JButton startButton_AiMid;
    private JButton startButton_AiHard;
    private JButton exitButton;

    public ChessStartFrame() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500 );

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel titleLabel = new JLabel("Welcome to Chess Game", SwingConstants.CENTER);
        panel.add(titleLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        startButton_TwoPlayers = new JButton("Start_TwoPlayers");
        startButton_TwoPlayers.addActionListener(this);
        buttonPanel.add(startButton_TwoPlayers);

        startButton_AiSimple = new JButton("Start_AiSimple");
        startButton_AiSimple.addActionListener(this);
        buttonPanel.add(startButton_AiSimple);

        startButton_AiMid = new JButton("Start_AiMid");
        startButton_AiMid.addActionListener(this);
        buttonPanel.add(startButton_AiMid);

        startButton_AiHard = new JButton("Start_AiHard");
        startButton_AiHard.addActionListener(this);
        buttonPanel.add(startButton_AiHard);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        panel.add(buttonPanel);
        add(panel);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton_TwoPlayers) {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Level.TwoPlayers, mainFrame);
                mainFrame.setVisible(true);
                mainFrame.addSaveButton(gameController);
                mainFrame.addLoadButton(gameController);
                mainFrame.addTurns(gameController);
                mainFrame.addRestartButton(gameController);
                mainFrame.addBgmButton();
            });
        }else if (e.getSource()==startButton_AiSimple) {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Level.AI_Simple, mainFrame);
                mainFrame.setVisible(true);
                mainFrame.addSaveButton(gameController);
                mainFrame.addLoadButton(gameController);
                mainFrame.addTurns(gameController);
                mainFrame.addRestartButton(gameController);
                mainFrame.addBgmButton();
            });
        }else if (e.getSource()==startButton_AiMid) {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Level.AI_Mid, mainFrame);
                mainFrame.setVisible(true);
                mainFrame.addSaveButton(gameController);
                mainFrame.addLoadButton(gameController);
                mainFrame.addTurns(gameController);
                mainFrame.addRestartButton(gameController);
                mainFrame.addBgmButton();
            });
        }else if (e.getSource()==startButton_AiHard) {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Level.AI_LittleHard,mainFrame);
                mainFrame.setVisible(true);
                mainFrame.addSaveButton(gameController);
                mainFrame.addLoadButton(gameController);
                mainFrame.addTurns(gameController);
                mainFrame.addRestartButton(gameController);
                mainFrame.addBgmButton();
            });
        } else if (e.getSource()==exitButton) {
            System.exit(0);
        }
    }

}

