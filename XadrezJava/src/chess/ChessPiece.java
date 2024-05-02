
package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

/**
 *
 * @author lucas
 */
public abstract class ChessPiece extends Piece{
    private Color color;
    private int moveCount = 0;

    public ChessPiece(Color color, Board board) {
        super(board);
        this.color = color;
    }
    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }
    public Color getColor() {
        return color;
    }
    
    public void increaseMoveCount(){
        moveCount++;
    }
    public void decreaseMoveCount(){
        moveCount--;
    }

    public int getMoveCount() {
        return moveCount;
    }
    

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p !=null && p.getColor() != color;
        
    }

    
    
    
}
