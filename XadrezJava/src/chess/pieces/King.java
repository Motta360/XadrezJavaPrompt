
package chess.pieces;

import boardGame.Board;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author lucas
 */
public class King extends ChessPiece{
    
    public King(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "K";
    }
    
    
    
}
