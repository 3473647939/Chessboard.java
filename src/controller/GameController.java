package controller;


import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessGameFrame;
import view.ChessView.All;
import view.ChessboardComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameController implements GameListener {


    private Chessboard model;
    public ChessboardComponent view;
    private ChessGameFrame chessGameFrame;
    private PlayerColor currentPlayer;
    public PlayerColor Winner;
    private ChessboardPoint selectedPoint;
    public int turn;
    public Level level;
    public AI ai;
    private ArrayList<ChessboardPoint> validMoves;
    private String winner;
    public boolean tchange=false;

    public GameController(ChessboardComponent view, Chessboard model,Level level,ChessGameFrame chessGameFrame) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        this.level = level;
        this.chessGameFrame=chessGameFrame;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();

        if (level == Level.AI_Simple||level==Level.AI_Mid||level == Level.AI_LittleHard){
            ai = new AI(model,level);
        }
    }
    public void restart(){
        this.currentPlayer = PlayerColor.BLUE;
        this.model =new Chessboard();
        this.view.initiateChessComponent(model);
        chessGameFrame.getTurns().setText("回合数: "+0);
        chessGameFrame.getPlayer().setText("当前玩家: "+getCurrentPlayer());
        if (currentPlayer== PlayerColor.BLUE)chessGameFrame.getPlayer().setForeground(Color.blue);
        if (currentPlayer==PlayerColor.RED)chessGameFrame.getPlayer().setForeground(Color.red);
        this.view.repaint();
        initialize();
    }
    public void aiStart(){
        if (ai!=null&&currentPlayer!=PlayerColor.BLUE){
            Go aiGo = ai.AIGo(currentPlayer);
            if (aiGo!=null){
                view.setAiPlay(true);
                selectedPoint = aiGo.src;
                if (model.getChessPieceAt(aiGo.des)==null){
                    model.moveChessPiece(selectedPoint,aiGo.des);}else {
                    model.captureChessPiece(selectedPoint,aiGo.des);
                    view.removeChessComponentAtGrid(aiGo.des);
                }
                view.setChessComponentAtGrid(aiGo.des, view.removeChessComponentAtGrid(selectedPoint));
                model.intrap(aiGo.des,currentPlayer);
                model.outrap(selectedPoint);
                selectedPoint = null;
                win();
                turn++;chessGameFrame.getTurns().setText("回合数: "+turn);
                swapColor();chessGameFrame.getPlayer().setText("当前玩家: "+getCurrentPlayer());
                if (currentPlayer== PlayerColor.BLUE)chessGameFrame.getPlayer().setForeground(Color.blue);
                if (currentPlayer==PlayerColor.RED)chessGameFrame.getPlayer().setForeground(Color.red);
                view.repaint();
            }
        }
    }

    private void initialize() {
        turn=0;
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
        if (model.grid[0][3].getPiece()!=null||model.redOver.toArray().length==8){
            Winner=PlayerColor.BLUE;winner="蓝方";return true;
        }
        if (model.grid[8][3].getPiece()!=null||model.blueOver.toArray().length==8){
            Winner=PlayerColor.RED;winner="红方";return true;
        }
        else return false;
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
            turn++;chessGameFrame.getTurns().setText("回合数: "+turn);
            swapColor();
            chessGameFrame.getPlayer().setText("当前玩家: "+getCurrentPlayer());
            if (currentPlayer== PlayerColor.BLUE)chessGameFrame.getPlayer().setForeground(Color.blue);
            if (currentPlayer==PlayerColor.RED)chessGameFrame.getPlayer().setForeground(Color.red);
            if (level!=Level.TwoPlayers)
                aiStart();
            view.repaint();
            if (win()){JOptionPane.showMessageDialog(null,winner+"胜利");}

        }
    }
    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, All component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                showLegalMove(point);
                component.repaint();

            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            closeLegalMove(point);
            component.repaint();
        } else if(point!=null){
            if (model.isValidCapture(selectedPoint, point)) {
                model.captureChessPiece(selectedPoint,point);
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                model.intrap(point,currentPlayer);
                win();
                if (level==Level.TwoPlayers)
                turn++;
                chessGameFrame.getTurns().setText("回合数: "+turn);
                chessGameFrame.getPlayer().setText("当前玩家: "+getCurrentPlayer());
                if (currentPlayer== PlayerColor.BLUE)chessGameFrame.getPlayer().setForeground(Color.blue);
                if (currentPlayer==PlayerColor.RED)chessGameFrame.getPlayer().setForeground(Color.red);
                component.repaint();
                view.repaint();

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

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Object getCurrentPlayer() {
        return currentPlayer;
    }
    public void showLegalMove(ChessboardPoint point){
        validMoves = model.getLegalMovePoints(point);
        view.showLegalMove(validMoves);
    }
    public void closeLegalMove(ChessboardPoint point){
        validMoves = model.getLegalMovePoints(point);
        view.closeLegalMove(validMoves);
    }
//Ai 吃子 ，bug， 显示bug

    public ChessGameFrame getChessGameFrame() {
        return chessGameFrame;
    }
}
