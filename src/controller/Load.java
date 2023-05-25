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
    private Cell[][] grid;

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

                }
            }
        }
    }
}
