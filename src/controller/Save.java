package controller;

import model.Cell;
import model.PlayerColor;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public void save(GameController gameController, String name) {
        File file = new File("resource\\Save\\" + name + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    Cell cell = gameController.getModel().getGrid()[i][j];
                    if (cell.getPiece() != null) {
                        bufferedWriter.write(i + " " + j + " " + (cell.getPiece().getOwner().equals(PlayerColor.BLUE) ? "Blue" : "Red") + " " + cell.getPiece().getName() + " " + cell.getPiece().getRank()  + "\n");
                    }
                }
            }
            bufferedWriter.write(gameController.getCurrentPlayer().equals(PlayerColor.BLUE) ? "Blue" : "Red");
            bufferedWriter.write("\n"+gameController.getModel().redOver+"\n");
            bufferedWriter.write(gameController.getModel().blueOver+"\n");
            bufferedWriter.write(gameController.turn+"\n");
            bufferedWriter.close();
            JOptionPane.showMessageDialog(null, "存档成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}