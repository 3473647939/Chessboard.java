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
        setTitle("斗兽棋");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,768);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel titleLabel = new JLabel("斗兽棋", SwingConstants.CENTER);
        titleLabel.setSize(400,200);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 100));
        panel.add(titleLabel);
//
        ImageIcon bg = new ImageIcon("resource\\background.png");
        // 创建JLabel，并设置背景
        JLabel label = new JLabel(bg);

        label.setBounds(0, 0, 1024, 768);
        this.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));
        ((JPanel)getContentPane()).setOpaque(false); //设置透明
        setLayout(new FlowLayout());
  //
        JPanel buttonPanel = new JPanel(new FlowLayout());

        startButton_TwoPlayers = new JButton("本地双人");
        startButton_TwoPlayers.addActionListener(this);
        buttonPanel.add(startButton_TwoPlayers);

        startButton_AiSimple = new JButton("人机对战——简单");
        startButton_AiSimple.addActionListener(this);
        buttonPanel.add(startButton_AiSimple);

        startButton_AiMid = new JButton("人机对战——中等");
        startButton_AiMid.addActionListener(this);
        buttonPanel.add(startButton_AiMid);

        startButton_AiHard = new JButton("人机对战——困难");
        startButton_AiHard.addActionListener(this);
        buttonPanel.add(startButton_AiHard);

        exitButton = new JButton("退出游戏");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);
        setLocationRelativeTo(null);
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
                mainFrame.addRegretButton(gameController);
                mainFrame.setLayout(null);
                setVisible(false);
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
                mainFrame.addRegretButton(gameController);
                mainFrame.setLayout(null);
                setVisible(false);
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
                mainFrame.addRegretButton(gameController);
                mainFrame.setLayout(null);
                setVisible(false);
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
                mainFrame.addRegretButton(gameController);
                mainFrame.setLayout(null);
                setVisible(false);
            });
        } else if (e.getSource()==exitButton) {
            System.exit(0);
        }
    }

}

