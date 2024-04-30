
package chess.pieces;

import boardGame.Board;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author lucas
 */
public class Rook extends ChessPiece{
    
    public Rook(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "R";
    }
    
}
