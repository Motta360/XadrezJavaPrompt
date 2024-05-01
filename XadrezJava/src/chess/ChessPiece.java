
package chess;

import boardGame.Board;
import boardGame.Piece;

/**
 *
 * @author lucas
 */
public abstract class ChessPiece extends Piece{
    private Color color;

    public ChessPiece(Color color, Board board) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    
    
    
}
