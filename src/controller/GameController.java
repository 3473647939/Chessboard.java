package controller;


import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessView.All;
import view.ChessboardComponent;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameController implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;
    public PlayerColor Winner;
    private ChessboardPoint selectedPoint;
    public int turn;

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        turn=0;
        for (File file : new File("D:\\Save\\autosave").listFiles()) {
            file.delete();
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
            }
        }
    }

    // after a valid move swap the player
    public void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
    }
    public boolean win() {
        if (model.grid[0][3]!=null||model.redOver.toArray().length==8){
            Winner=PlayerColor.BLUE;return true;
        }
        if (model.grid[8][3]!=null||model.blueOver.toArray().length==8){
            Winner=PlayerColor.RED;return true;
        }
        return false;//
    }
    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            model.intrap(point,currentPlayer);
            model.outrap(selectedPoint);
            selectedPoint = null;
            win();
            swapColor();
            view.repaint();
            turn++;
            view.autosave(turn);
        }
    }
    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, All component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else if(point!=null){
            if (model.isValidCapture(selectedPoint, point) && model.isValidMove(selectedPoint, point)) {
                model.captureChessPiece(selectedPoint,point);
                model.moveChessPiece(selectedPoint, point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                model.intrap(point,currentPlayer);
                win();
                swapColor();
                view.repaint();
                turn++;
                view.autosave(turn);
            } else if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        }
    }

    public Chessboard getModel() {
        return  model;
    }

    public Object getCurrentPlayer() {
        return currentPlayer;
    }
}
