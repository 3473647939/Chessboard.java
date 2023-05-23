package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * This is the equivalent of the Cell class,
 * but this class only cares how to draw Cells on ChessboardComponent
 */

public class CellComponent extends JPanel {
    private Color background;
    private boolean seUI;
    int size;

    public CellComponent(Color background, Point location, int size) {
        setLayout(new GridLayout(1,1));
        setLocation(location);
        setSize(size, size);
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        if (seUI){
        g2d.setColor(Color.orange);}
        else {
        g2d.setColor(background);}
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(1, 1, this.getWidth() - 1, this.getHeight() - 1,size/4 , size/4);
        g2d.fill(roundedRectangle);
        g2d.fillRect(1, 1, this.getWidth()-1, this.getHeight()-1);
    }
    public void setSeUI(boolean seUI){
        this.seUI = seUI;
    }
}
