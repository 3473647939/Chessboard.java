package controller;

import model.Cell;
import model.ChessPiece;
import model.Constant;
import model.PlayerColor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Load {//悔棋试着加上次数限制
    public Cell[][] grid;
    public void load(GameController gameController){
        File file=new File("Save");
        file.getName().endsWith(".txt");//选择txt文件，不完善的功能
        FileSystemView fsv = FileSystemView.getFileSystemView();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("resource\\Save"));//学习如何筛选文件
        fileChooser.setAcceptAllFileFilterUsed(false);//使之不能选择其他文件
        fileChooser.setDialogTitle("请选择存档");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {
            for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
                for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                    grid[i][j].removePiece();
                }
            }
            long count;
            String path=fileChooser.getSelectedFile().getPath();
            try {
                Path path1 = Paths.get(path);
                count = Files.lines(path1).count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                BufferedReader br=new BufferedReader(new FileReader(path));
                for (int i=1;i<count;i++){
                    String s;
                    try {
                        s = br.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }//从index=4开始找到两个空格所在位置s1,s2  依次读取5——s1-1；s1+1——s2-1；s2+1
                    //找到对应的信息
                    int y= Integer.parseInt(s.substring(0,1));
                    int x= Integer.parseInt(s.substring(2,3));
                    String color=s.substring(5,s.indexOf("\t",5));//color存在问题
                    PlayerColor playerColor;
                    if (color.equals("Blue")){playerColor=PlayerColor.BLUE;}
                    else {playerColor=PlayerColor.RED;}
                    String name=s.substring(s.indexOf("\t",5)+1,s.indexOf("\t",s.indexOf("\t",5)+1));
                    int rank= Integer.parseInt(s.substring(s.indexOf("\t",s.indexOf("\t",5)+1)+1));
                    //转录到棋盘上
                    grid[y][x].setPiece(new ChessPiece(playerColor,name,rank));
                }
                try {//设定操作方
                    String player= br.readLine();
                    PlayerColor playerColor;
                    if (player.equals("Blue")){playerColor=PlayerColor.BLUE;}
                    else {playerColor=PlayerColor.RED;}
                    gameController.setCurrentPlayer(playerColor);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
