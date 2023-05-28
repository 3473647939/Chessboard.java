package view;

import controller.GameController;
import controller.Load;

import controller.Save;
import controller.replay;
import model.Cell;
import model.PlayerColor;
import view.ChessView.ChessStartFrame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    private final int ONE_CHESS_SIZE;
    private ChessboardComponent chessboardComponent;
    private String Name;
    private Clip clip;
    private boolean isPlaying = true;
    private JButton playBtn;
    private JLabel turns;
    private JLabel player;


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

    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    public void addSaveButton(GameController gameController) {
        JButton button = new JButton("存档");
        button.addActionListener((e) -> {
            try {
                // 加载音频文件
                File soundFile = new File("resource\\voice.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioIn.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                // 打开数据行并开始播放音频
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            this.Name = JOptionPane.showInputDialog("请输入存档名");
            Save s = new Save();
            s.save(gameController, this.Name);
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
    }

    public void addRegretButton(GameController gameController) {
        JButton button = new JButton("悔棋");
        button.addActionListener((e) -> {
            try {
                // 加载音频文件
                File soundFile = new File("resource\\voice.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioIn.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                // 打开数据行并开始播放音频
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            Load l = new Load();
            l.regret(gameController);
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 420);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
    }

    public void addLoadButton(GameController gameController) {
        JButton button = new JButton("读取存档");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            try {
                // 加载音频文件
                File soundFile = new File("resource\\voice.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioIn.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                // 打开数据行并开始播放音频
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            Load l = new Load();
            l.load(gameController);
        });
    }

    public void addRestartButton(GameController gameController) {
        JButton button = new JButton("重新开始");
        button.setLocation(HEIGTH, HEIGTH / 10 + 300);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            try {
                // 加载音频文件
                File soundFile = new File("resource\\voice.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioIn.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                // 打开数据行并开始播放音频
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            int userOption = JOptionPane.showConfirmDialog(null, "要重新开始吗？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);    //确认对话框
            if (userOption == JOptionPane.OK_OPTION) {
                gameController.restart();
            } else {
                System.out.println("否");
            }
        });
    }
    public void addReplayButton(GameController gameController){
        JButton button = new JButton("棋局回放");
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            try {
                // 加载音频文件
                File soundFile = new File("resource\\voice.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioIn.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                // 打开数据行并开始播放音频
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            replay r=new replay();
            r.Replay(gameController);
        });
    }

    public void addTurns(GameController gameController) {
        turns = new JLabel("回合数: " + gameController.turn);
        player = new JLabel("当前玩家: " + gameController.getCurrentPlayer());
        turns.setSize(200, 20);
        player.setSize(250, 20);
        turns.setFont(new Font("宋体", Font.BOLD, 16));
        player.setFont(new Font("宋体", Font.BOLD, 16));
        turns.setLocation(HEIGTH, HEIGTH / 10 + 50);
        player.setLocation(HEIGTH, HEIGTH / 10 + 80);
        if (gameController.getCurrentPlayer() == PlayerColor.BLUE) player.setForeground(Color.blue);
        if (gameController.getCurrentPlayer() == PlayerColor.RED) player.setForeground(Color.red);
        add(player);
        add(turns);
    }

    public void addBgmButton() {
        playBtn = new JButton("暂停音乐");
        playBtn.setLocation(HEIGTH, HEIGTH / 10 + 360);
        playBtn.setSize(200, 60);
        playBtn.setFont(new Font("宋体", Font.BOLD, 20));
        add(playBtn);
        try {
            File musicFile = new File("resource\\OCTOPATH TRAVELER II.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        playBtn.addActionListener(e -> {
            if (e.getSource() == playBtn) {
                try {
                    if (!isPlaying) { // 如果当前没有正在播放，开始播放音乐
                        File musicFile = new File("resource\\OCTOPATH TRAVELER II.wav");
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        isPlaying = true;
                        playBtn.setText("暂停播放");
                    } else { // 如果当前正在播放，暂停播放
                        clip.close();
                        isPlaying = false;
                        playBtn.setText("播放音乐");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }//背景音乐

    public JLabel getTurns() {
        return turns;
    }

    public JLabel getPlayer() {
        return player;
    }
    public void addQuitButton(){
        JButton button = new JButton("返回主菜单");
        button.setLocation(HEIGTH, HEIGTH / 10 + 540);
        button.setSize(200, 60);
        button.setFont(new Font("宋体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            try {
                // 加载音频文件
                File soundFile = new File("resource\\voice.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioIn.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                // 打开数据行并开始播放音频
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            dispose();
            clip.close();
            ChessStartFrame l=new ChessStartFrame();
            l.setVisible(true);
        });

    }
}
