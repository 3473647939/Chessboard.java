package view;

import controller.GameController;
import controller.Load;
import controller.Save;
import model.PlayerColor;
import javax.swing.*;
import java.awt.*;

public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    private final int ONE_CHESS_SIZE;
    private ChessboardComponent chessboardComponent;
    private String Name;

    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    public void addSaveButton(GameController gameController) {
        JButton button = new JButton("存档");
        button.addActionListener((e) -> {
            this.Name = JOptionPane.showInputDialog("请输入存档名");
            Save s = new Save();
            s.save(gameController, this.Name);
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
    }
    public void addRegretButton(GameController gameController){
        JButton button = new JButton("Regret");

    }
    public void addLoadButton(GameController gameController) {
        JButton button = new JButton("读取存档");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            Load l=new Load();
            l.load(gameController);
        });
    }
    public void addRestartButton(GameController gameController){
        JButton button = new JButton("重新开始");
        button.setLocation(HEIGTH, HEIGTH / 10 + 300);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e ->{
        int userOption =  JOptionPane.showConfirmDialog(null,"要重新开始吗？","提示", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);	//确认对话框
        if (userOption == JOptionPane.OK_OPTION) {
            gameController.restart();
        }else {
            System.out.println("否");
        }
        });
    }
    public void addTurns(GameController gameController){
        JLabel turns=new JLabel("回合数: "+gameController.turn);
        JLabel player=new JLabel("当前玩家: "+gameController.getCurrentPlayer());
        turns.setSize(200,20);
        player.setSize(250,20);
        turns.setFont(new Font("宋体", Font.BOLD, 16));
        player.setFont(new Font("宋体", Font.BOLD, 16));
        turns.setLocation(HEIGTH,HEIGTH/10+50);
        player.setLocation(HEIGTH,HEIGTH/10+80);
        if (gameController.getCurrentPlayer()== PlayerColor.BLUE)player.setForeground(Color.blue);
        if (gameController.getCurrentPlayer()==PlayerColor.RED)player.setForeground(Color.red);
        add(player);
        add(turns);
    }
}
