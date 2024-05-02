
package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author lucas
 */
public class King extends ChessPiece{
    
    public King(Board board, Color color) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "K";
    }
    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece( position);
        return p== null || p.getColor() != getColor();
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        
        //above
        
        p.setValues(position.getRow()-1, position.getColomn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        //below
        
        p.setValues(position.getRow()+1, position.getColomn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        //left
        
        p.setValues(position.getRow(), position.getColomn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        //right
        
        p.setValues(position.getRow(), position.getColomn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        //nw
        
        p.setValues(position.getRow()-1, position.getColomn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        //ne
        
        p.setValues(position.getRow()-1, position.getColomn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        //sw
        
        p.setValues(position.getRow()+1, position.getColomn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        //se
        
        p.setValues(position.getRow()+1, position.getColomn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        return mat;
    }
    
    
    
}
