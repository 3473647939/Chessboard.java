package model;


import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    public Cell[][] grid;

    public ArrayList<Go> gos;
    private Set<ChessboardPoint> trapCellr = new HashSet<>();
    private Set<ChessboardPoint> trapCellb = new HashSet<>();
    public ArrayList<ChessPiece> blueOver;
    public ArrayList<ChessPiece> redOver;

    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19
        gos = new ArrayList<>();
        blueOver = new ArrayList<>();
        redOver = new ArrayList<>();

        initGrid();
        initPieces();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
        trapCellr.add(new ChessboardPoint(8, 2));
        trapCellr.add(new ChessboardPoint(8, 4));
        trapCellr.add(new ChessboardPoint(7, 3));

        trapCellb.add(new ChessboardPoint(0, 2));
        trapCellb.add(new ChessboardPoint(0, 4));
        trapCellb.add(new ChessboardPoint(1, 3));
    }

    private void initPieces() {

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j].removePiece();
            }
        }

                grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant", 8));
                grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant", 8));
                grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion", 7));
                grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion", 7));
                grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6));
                grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6));
                grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5));
                grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5));
                grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4));
                grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4));
                grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3));
                grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3));
                grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2));
                grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2));
                grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat", 1));
                grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Rat", 1));
            }
    private ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }
    public Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    public ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }
    public void intrap(ChessboardPoint point,PlayerColor player){
        if (trapCellr.contains(point)&&player==PlayerColor.BLUE){
            getGridAt(point).getPiece().setRank(0);
        }
        if (trapCellb.contains(point)&&player==PlayerColor.RED) {
            getGridAt(point).getPiece().setRank(0);
        }
    }
    public void outrap(ChessboardPoint point){
            if (getChessPieceAt(point)!=null){
            if (getGridAt(point).getPiece().getName().equals("Rat"))getGridAt(point).getPiece().setRank(1);
            if (getGridAt(point).getPiece().getName().equals("Cat"))getGridAt(point).getPiece().setRank(2);
            if (getGridAt(point).getPiece().getName().equals("Dog"))getGridAt(point).getPiece().setRank(3);
            if (getGridAt(point).getPiece().getName().equals("Wolf"))getGridAt(point).getPiece().setRank(4);
            if (getGridAt(point).getPiece().getName().equals("Leopard"))getGridAt(point).getPiece().setRank(5);
            if (getGridAt(point).getPiece().getName().equals("Tiger"))getGridAt(point).getPiece().setRank(6);
            if (getGridAt(point).getPiece().getName().equals("Lion"))getGridAt(point).getPiece().setRank(7);
            if (getGridAt(point).getPiece().getName().equals("Elephant"))getGridAt(point).getPiece().setRank(8);
        }
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
        if (isOpponentTrap(point, chessPiece.getOwner())){
            chessPiece.rank = 0;
        }else {
            if (chessPiece.getName().equals("Elephant")){
                chessPiece.rank = 8;
            }
            if (chessPiece.getName().equals("Lion")){
                chessPiece.rank = 7;
            }
            if (chessPiece.getName().equals("Tiger")){
                chessPiece.rank = 6;
            }
            if (chessPiece.getName().equals("Leopard")){
                chessPiece.rank = 5;
            }
            if (chessPiece.getName().equals("Wolf")){
                chessPiece.rank = 4;
            }
            if (chessPiece.getName().equals("Dog")){
                chessPiece.rank = 3;
            }
            if (chessPiece.getName().equals("Cat")){
                chessPiece.rank = 2;
            }
            if (chessPiece.getName().equals("Rat")){
                chessPiece.rank = 1;
            }
        }
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("充满想象力的走法");
        }
        ChessPiece chessPiece = removeChessPiece(src);
        setChessPiece(dest,chessPiece);
        gos.add(new Go(src,dest,chessPiece.getOwner()));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("充满想象力的走法");
        }
        ChessPiece eat = removeChessPiece(src);
        setChessPiece(dest,eat);
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece chessPiece = getChessPieceAt(src);
        if (getChessPieceAt(src) == null || (getChessPieceAt(dest) != null)) {
            return false;
        }
        if (isOwnDens(dest,chessPiece.getOwner())) return false;
        boolean a ;
        a = calculateDistance(src,dest) == 1;
        if (chessPiece.getName().equals("Elephant")){
            boolean b = a && !isRiver(dest);
            return b;
        }

        if (chessPiece.getName().equals("Lion")){
            return (a && !isRiver(dest)) || canJumpRiver(src, dest);
        }
        if (chessPiece.getName().equals("Tiger")){
            return (a && !isRiver(dest)) || canJumpRiver(src, dest);
        }
        if (chessPiece.getName().equals("Leopard")){
            boolean b = a && !isRiver(dest);
            return b;
        }
        if (chessPiece.getName().equals("Wolf")){
            boolean b = a && !isRiver(dest);
            return b;
        }
        if (chessPiece.getName().equals("Dog")){
            boolean b = a && !isRiver(dest);
            return b;
        }
        if (chessPiece.getName().equals("Cat")){
            boolean b = a && !isRiver(dest);
            return b;
        }
        if (chessPiece.getName().equals("Rat")){
            return a;
        }
        return false;
    }
    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece eat = getChessPieceAt(src);
        ChessPiece eaten = getChessPieceAt(dest);
        if (eat ==null||eaten==null){
            return false;
        }
        if (eat.getOwner() == eaten.getOwner()){
            return false;
        }
        boolean b = calculateDistance(src,dest) ==1;
        if (eat.getName().equals("Elephant")){
            return b && !isRiver(dest) && eaten.rank!=1;
        }
        if (eat.getName().equals("Lion")){
            return ((b && !isRiver(dest)) || canJumpRiver(src, dest)) && eaten.rank <= 7;
        }
        if (eat.getName().equals("Tiger")){
            return ((b && !isRiver(dest)) || canJumpRiver(src, dest)) && eaten.rank <= 6;
        }
        if (eat.getName().equals("Leopard")){
            return b && !isRiver(dest) && eaten.rank <= 5;
        }
        if (eat.getName().equals("Wolf")){
            return b && !isRiver(dest) && eaten.rank <= 4;
        }
        if (eat.getName().equals("Dog")){
            return b && !isRiver(dest) && eaten.rank <= 3;
        }
        if (eat.getName().equals("Cat")){
            return b && !isRiver(dest) && eaten.rank <= 2;
        }
        if (eat.getName().equals("Rat")){
            return b && (eaten.rank <= 1 || eaten.rank == 8) && !(isRiver(src) && !isRiver(dest));
        }
        return false;
    }
    private boolean isRiver(ChessboardPoint point){
        if (point.getCol()>=1&&point.getCol()<=2&&point.getRow()>=3&&point.getRow()<=5)
            return true;
        return point.getCol() >= 4 && point.getCol() <= 5 && point.getRow() >= 3 && point.getRow() <= 5;
    }

    private boolean isOwnDens(ChessboardPoint point, PlayerColor color){
        if (color == PlayerColor.BLUE){
            return point.getRow() == 8 && point.getCol() == 3;
        } else {
            return point.getRow() == 0 && point.getCol() == 3;
        }
    }

    private boolean isOpponentDens(ChessboardPoint point, PlayerColor color){
        if (color == PlayerColor.RED){
            return point.getRow() == 8 && point.getCol() == 3;
        } else {
            return point.getRow() == 0 && point.getCol() == 3;
        }
    }

    private boolean isOpponentTrap(ChessboardPoint point, PlayerColor color){
        if (color == PlayerColor.BLUE){
            return (point.getRow() == 1 && point.getCol() == 3)
                    || (point.getRow() == 0 && point.getCol() == 2)
                    || (point.getRow() == 0 && point.getCol() == 4);
        } else {
            return (point.getRow() == 7 && point.getCol() == 3)
                    || (point.getRow() == 8 && point.getCol() == 2)
                    || (point.getRow() == 8 && point.getCol() == 4);
        }
    }

    private boolean canJumpRiver(ChessboardPoint src, ChessboardPoint dest){
        if ((src.getRow() == 3 && src.getCol() == 0 && dest.getRow() == 3 && dest.getCol() == 3)
                || (src.getRow() == 3 && src.getCol() == 3 && dest.getRow() == 3 && dest.getCol() == 0)){
            if (getChessPieceAt(new ChessboardPoint(3, 1)) == null
                    && getChessPieceAt(new ChessboardPoint(3, 2)) == null){
                return true;
            }
        }
        if ((src.getRow() == 4 && src.getCol() == 0 && dest.getRow() == 4 && dest.getCol() == 3)
                || (src.getRow() == 4 && src.getCol() == 3 && dest.getRow() == 4 && dest.getCol() == 0)){
            if ( getChessPieceAt(new ChessboardPoint(4, 1)) == null
                    && getChessPieceAt(new ChessboardPoint(4, 2)) == null){
                return true;
            }

        }
        if ((src.getRow() == 5 && src.getCol() == 0 && dest.getRow() == 5 && dest.getCol() == 3)
                || (src.getRow() == 5 && src.getCol() == 3 && dest.getRow() == 5 && dest.getCol() == 0)){
          if (getChessPieceAt(new ChessboardPoint(5, 1)) == null
                  && getChessPieceAt(new ChessboardPoint(5, 2)) == null){
              return true;
          }
        }
        if ((src.getRow() == 3 && src.getCol() == 3 && dest.getRow() == 3 && dest.getCol() == 6)
                || (src.getRow() == 3 && src.getCol() == 6 && dest.getRow() == 3 && dest.getCol() == 3)){
            if (getChessPieceAt(new ChessboardPoint(3, 4)) == null
                    && getChessPieceAt(new ChessboardPoint(3, 5)) == null){
                return true;
            }
        }
        if ((src.getRow() == 4 && src.getCol() == 3 && dest.getRow() == 4 && dest.getCol() == 6)
                || (src.getRow() == 4 && src.getCol() == 6 && dest.getRow() == 4 && dest.getCol() == 3)){
            if (getChessPieceAt(new ChessboardPoint(4, 4)) == null
                    && getChessPieceAt(new ChessboardPoint(4, 5)) == null){
                return true;
            }
        }
        if ((src.getRow() == 5 && src.getCol() == 3 && dest.getRow() == 5 && dest.getCol() == 6)
                || (src.getRow() == 5 && src.getCol() == 6 && dest.getRow() == 5 && dest.getCol() == 3)){
           if (getChessPieceAt(new ChessboardPoint(5, 4)) == null
                   && getChessPieceAt(new ChessboardPoint(5, 5)) == null){
               return true;
           }
        }

        if ((src.getRow() == 2 && src.getCol() == 1 && dest.getRow() == 6 && dest.getCol() == 1)
                || (src.getRow() == 6 && src.getCol() == 1 && dest.getRow() == 2 && dest.getCol() == 1)){
            if (getChessPieceAt(new ChessboardPoint(3, 1)) == null
                    && getChessPieceAt(new ChessboardPoint(4, 1)) == null
                    && getChessPieceAt(new ChessboardPoint(5, 1)) == null){
                return true;
            }
        }
        if ((src.getRow() == 2 && src.getCol() == 2 && dest.getRow() == 6 && dest.getCol() == 2)
                || (src.getRow() == 6 && src.getCol() == 2 && dest.getRow() == 2 && dest.getCol() == 2)){
           if (getChessPieceAt(new ChessboardPoint(3, 2)) == null
                   && getChessPieceAt(new ChessboardPoint(4, 2)) == null
                   && getChessPieceAt(new ChessboardPoint(5, 2)) == null){
               return true;
           }
        }
        if ((src.getRow() == 2 && src.getCol() == 4 && dest.getRow() == 6 && dest.getCol() == 4)
                || (src.getRow() == 6 && src.getCol() == 4 && dest.getRow() == 2 && dest.getCol() == 4)){
            if (getChessPieceAt(new ChessboardPoint(3, 4)) == null
                    && getChessPieceAt(new ChessboardPoint(4, 4)) == null
                    && getChessPieceAt(new ChessboardPoint(5, 4)) == null){
                return true;
            }
        }
        if ((src.getRow() == 2 && src.getCol() == 5 && dest.getRow() == 6 && dest.getCol() == 5)
                || (src.getRow() == 6 && src.getCol() == 5 && dest.getRow() == 2 && dest.getCol() == 5)){
            if (getChessPieceAt(new ChessboardPoint(3, 5)) == null
                    && getChessPieceAt(new ChessboardPoint(4, 5)) == null
                    && getChessPieceAt(new ChessboardPoint(5, 5)) == null){
                return true;
            }
        }
        return false;
    }

}
