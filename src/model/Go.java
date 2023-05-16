package model;

public class Go {
    public ChessboardPoint src;
    public ChessboardPoint des;
    public PlayerColor color;
    public ChessPiece captured;

    public Go(ChessboardPoint src, ChessboardPoint des, PlayerColor color){
        this.src = src;
        this.des = des;
        this.color = color;
        captured = null;
    }

    public Go(ChessboardPoint src, ChessboardPoint des, PlayerColor color, ChessPiece captured){
        this.src = src;
        this.des = des;
        this.color = color;
        this.captured = captured;
    }

    @Override
    public String toString(){
        if (captured == null)
            return (color==PlayerColor.BLUE ? "blue": "red")+
                    "("+src.getRow()+","+src.getCol()+")"+"("+des.getRow()+","+
                    des.getCol()+")"+"null";
        else
            return (color==PlayerColor.BLUE ? "blue": "red")+
                    "("+src.getRow()+","+src.getCol()+")"+"("+des.getRow()+","+
                    des.getCol()+")"+captured.getName();
    }
}

