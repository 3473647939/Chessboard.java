package listener;

import model.ChessboardPoint;
import view.CellComponent;
import view.ChessView.All;
import view.ChessView.ElephantChessComponent;

public interface GameListener {

    void onPlayerClickCell(ChessboardPoint point, CellComponent component);


    void onPlayerClickChessPiece(ChessboardPoint point, All component);

}
