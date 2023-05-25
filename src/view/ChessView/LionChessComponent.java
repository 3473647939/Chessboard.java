package view.ChessView;

import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class LionChessComponent extends All{

        /**
         * This is the equivalent of the ChessPiece class,
         * but this class only cares how to draw Chess on ChessboardComponent
         */
            private PlayerColor owner;

            private boolean selected;

            public LionChessComponent (PlayerColor owner, int size) {
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
                ImageIcon pic = new ImageIcon();
                if (owner == PlayerColor.RED) pic = new ImageIcon("resource\\RLion.png");
                if (owner == PlayerColor.BLUE) pic = new ImageIcon("resource\\BLion.png");
                Image image = pic.getImage();
                pic = new ImageIcon(image.getScaledInstance(size*5/6,size*5/6,Image.SCALE_SMOOTH));
                JLabel label = new JLabel(pic);
                label.setSize(size, size);
                add(label);
                if (owner ==PlayerColor.RED){
                    g.setColor(Color.RED);
                    g.drawRoundRect(1, 1, this.getWidth() - 1, this.getHeight() - 1,size/4 , size/4);}else {
                    g.setColor(Color.BLUE);
                    g.drawRoundRect(1, 1, this.getWidth() - 1, this.getHeight() - 1,size/4 , size/4);}
            }
        }

