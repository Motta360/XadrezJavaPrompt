
package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author lucas
 */
public class Pawn extends ChessPiece{

    public Pawn(Color color, Board board) {
        super(color, board);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        
        if(getColor() == Color.WHITE){
            p.setValues(position.getRow()-1, position.getColomn());
            if(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
                mat[p.getRow()][p.getColomn()] = true;
            }
            p.setValues(position.getRow()-2, position.getColomn());
            Position p2 = new Position(position.getRow()-1, position.getColomn());
            if(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsaPiece(p2) && getMoveCount() ==0){
                mat[p.getRow()][p.getColomn()] = true;
            }
            p.setValues(position.getRow()-1, position.getColomn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColomn()] = true;
            }
            p.setValues(position.getRow()-1, position.getColomn()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColomn()] = true;
            }
        }else{
            p.setValues(position.getRow()+1, position.getColomn());
            if(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
                mat[p.getRow()][p.getColomn()] = true;
            }
            p.setValues(position.getRow()+2, position.getColomn());
            Position p2 = new Position(position.getRow()+1, position.getColomn());
            if(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsaPiece(p2) && getMoveCount() ==0){
                mat[p.getRow()][p.getColomn()] = true;
            }
            p.setValues(position.getRow()+1, position.getColomn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColomn()] = true;
            }
            p.setValues(position.getRow()+1, position.getColomn()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColomn()] = true;
            
        }
    }
    return mat;
}

    @Override
    public String toString() {
        return "P";
    }
    
}