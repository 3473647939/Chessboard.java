package controller;

import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Load extends Component {
    public void load(GameController gameController) {
        File file = new File("Save");//选择存档
        FileSystemView fsv = FileSystemView.getFileSystemView();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("resource\\Save"));
        fileChooser.setDialogTitle("请选择存档");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {//重置棋盘
            for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
                for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                    ChessboardPoint chessboardPoint = new ChessboardPoint(i, j);
                    if (gameController.getModel().getChessPieceAt(chessboardPoint) != null)
                        gameController.getModel().removeChessPiece(chessboardPoint);
                }
            }
            long count;
            String path = fileChooser.getSelectedFile().getPath();
            try {
                Path path1 = Paths.get(path);
                count = Files.lines(path1).count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                //从index=4开始找到两个空格所在位置s1,s2  依次读取5——s1-1；s1+1——s2-1；s2+1
                //找到对应的信息
                for (int i = 1; i < count; i++) {
                    String s;
                    try {
                        s = br.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int y = Integer.parseInt(s.substring(0, 1));
                    int x = Integer.parseInt(s.substring(2, 3));
                    String color = s.substring(4, s.indexOf(" ", 5));
                    System.out.println(color);
                    String name = s.substring(s.indexOf(" ", 5) + 1, s.indexOf(" ", s.indexOf(" ", 5) + 1));
                    int s1 = s.indexOf(" ", s.indexOf(" ", 5) + 1);
                    int rank = Integer.parseInt(s.substring(s1 + 1, s1 + 2));
                    int turn = Integer.parseInt(s.substring(s1 + 3));
                    PlayerColor playerColor;
                    if (color.equals("Blue")) {
                        playerColor = PlayerColor.BLUE;
                    }
                    else {
                        playerColor = PlayerColor.RED;
                    }
                    //转录到棋盘上
                    ChessboardPoint chessboardPoint = new ChessboardPoint(y, x);
                    gameController.getChessGameFrame().getTurns().setText("回合数: " + turn);
                    gameController.getModel().setChessPiece(chessboardPoint, new ChessPiece(playerColor, name, rank));
                    //重置Component层
                }
                gameController.view.initiateChessComponent(gameController.getModel());
                gameController.view.repaint();
                try {//设定操作方
                    String player = br.readLine();
                    PlayerColor playerColor = null;
                    if (player.equals("Blue")) {
                        playerColor = PlayerColor.BLUE;
                    } else if (player.equals("Red")) {
                        playerColor = PlayerColor.RED;
                    } else {
                        JOptionPane.showMessageDialog(this, "无法读取行动方 错误代码：104");
                    }
                    gameController.setCurrentPlayer(playerColor);
                    gameController.getChessGameFrame().getPlayer().setText("当前玩家: " + playerColor);
                    if (gameController.getCurrentPlayer() == PlayerColor.BLUE)
                        gameController.getChessGameFrame().getPlayer().setForeground(Color.blue);
                    if (gameController.getCurrentPlayer() == PlayerColor.RED)
                        gameController.getChessGameFrame().getPlayer().setForeground(Color.red);
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
