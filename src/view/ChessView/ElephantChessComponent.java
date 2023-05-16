package view.ChessView;


import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ElephantChessComponent extends All {
    private PlayerColor owner;

    private boolean selected;

    public ElephantChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
        this.size = size;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon pic = new ImageIcon("resource\\RElephant.png");
        if (owner == PlayerColor.BLUE){
            pic = new ImageIcon("resource\\BElephant.png");
        }
        Image image = pic.getImage();
        pic = new ImageIcon(image.getScaledInstance(size,size,Image.SCALE_REPLICATE));
        JLabel label = new JLabel(pic);
        label.setSize(size, size);
        add(label);
    }
}
