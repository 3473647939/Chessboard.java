package view;


import controller.AI;
import controller.GameController;
import model.*;
import view.ChessView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    private final CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    public final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();
    private Set<ChessboardPoint> trapCell = new HashSet<>();
    private Set<ChessboardPoint> denCell = new HashSet<>();
    private GameController gameController;
    private boolean aiPlay = false;


    public Set<ChessboardPoint> getTrapCell() {
        return trapCell;
    }

    public Set<ChessboardPoint> getRiverCell() {
        return riverCell;
    }

    public Set<ChessboardPoint> getDenCell() {
        return denCell;
    }

    public ChessboardComponent(int chessSize) {
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents();
    }


    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                    if (grid[i][j].getPiece() != null) {
                        gridComponents[i][j].removeAll();
                        ChessPiece chess = grid[i][j].getPiece();
                        if (chess.getName().equals("Elephant")) {
                            gridComponents[i][j].add(new ElephantChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Lion")) {
                            gridComponents[i][j].add(new LionChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Tiger")) {
                            gridComponents[i][j].add(new TigerChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Leopard")) {
                            gridComponents[i][j].add(new LeopardChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Wolf")) {
                            gridComponents[i][j].add(new WolfChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Dog")) {
                            gridComponents[i][j].add(new DogChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Cat")) {
                            gridComponents[i][j].add(new CatChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                        if (chess.getName().equals("Rat")) {
                            gridComponents[i][j].add(new RatChessComponent(chess.getOwner(), CHESS_SIZE));
                        }
                    }
                    else gridComponents[i][j].removeAll();
            }
        }
    }

    public void removeChessComponent() {
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                try {
                    gridComponents[i][j].remove(0);
                } catch (Exception e) {
                }
            }
        }

    }

    public void initiateGridComponents() {

        riverCell.add(new ChessboardPoint(3, 1));
        riverCell.add(new ChessboardPoint(3, 2));
        riverCell.add(new ChessboardPoint(4, 1));
        riverCell.add(new ChessboardPoint(4, 2));
        riverCell.add(new ChessboardPoint(5, 1));
        riverCell.add(new ChessboardPoint(5, 2));

        riverCell.add(new ChessboardPoint(3, 4));
        riverCell.add(new ChessboardPoint(3, 5));
        riverCell.add(new ChessboardPoint(4, 4));
        riverCell.add(new ChessboardPoint(4, 5));
        riverCell.add(new ChessboardPoint(5, 4));
        riverCell.add(new ChessboardPoint(5, 5));

        trapCell.add(new ChessboardPoint(8, 2));
        trapCell.add(new ChessboardPoint(8, 4));
        trapCell.add(new ChessboardPoint(7, 3));

        trapCell.add(new ChessboardPoint(0, 2));
        trapCell.add(new ChessboardPoint(0, 4));
        trapCell.add(new ChessboardPoint(1, 3));

        denCell.add(new ChessboardPoint(8, 3));
        denCell.add(new ChessboardPoint(0, 3));

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else if (trapCell.contains(temp)) {//添加图片
                    ImageIcon pic = new ImageIcon("resource\\Trap.png");
                    Image image = pic.getImage();
                    pic = new ImageIcon(image.getScaledInstance(CHESS_SIZE, CHESS_SIZE, Image.SCALE_REPLICATE));
                    JLabel label = new JLabel(pic);
                    label.setSize(CHESS_SIZE, CHESS_SIZE);
                    cell = new CellComponent(Color.GREEN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                    add(label);
                } else {
                    cell = new CellComponent(Color.LIGHT_GRAY, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                }
                gridComponents[i][j] = cell;//
            }
        }
    }


    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, All chess) {
        getGridComponentAt(point).add(chess);
    }

    public All removeChessComponentAtGrid(ChessboardPoint point) {
        // Note re-validation is required after remove / removeAll.
        All chess = (All) getGridComponentAt(point).getComponents()[0];
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    private CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }
    public void autosave(int k) {
        File file = new File("resource\\autosave\\"+k+".txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    Cell cell = gameController.getModel().getGrid()[i][j];
                    if (cell.getPiece() !=null) {
                        bufferedWriter.write(i + "\t" + j + "\t" + (cell.getPiece().getOwner().equals(PlayerColor.BLUE) ?  "Blue":"Red" ) + "\t" + cell.getPiece().getName() + "\t" + cell.getPiece().getRank() + "\n");
                    }
                }
            }
            bufferedWriter.write(gameController.getCurrentPlayer().equals(PlayerColor.BLUE)?"Blue":"Red");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y / CHESS_SIZE + ", " + point.x / CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y / CHESS_SIZE, point.x / CHESS_SIZE);
    }

    public Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (All) clickedComponent.getComponents()[0]);
            }
        }
    }
    public boolean isAiPlay(){
        return aiPlay;
    }
    public void setAiPlay(boolean b){
        this.aiPlay = aiPlay;
    }

    public void showLegalMove(ArrayList<ChessboardPoint> legalMove){
        for (ChessboardPoint e: legalMove){
            CellComponent component = getGridComponentAt(e);
            component.setSeUI(true);
            component.repaint();
        }
    }
    public void closeLegalMove(ArrayList<ChessboardPoint> legalMove){
        for (ChessboardPoint e:legalMove){
            CellComponent component = getGridComponentAt(e);
            component.setSeUI(false);
            component.repaint();
        }
    }

}


