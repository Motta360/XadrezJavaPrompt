
package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author lucas
 */
public class Queen extends ChessPiece{
    
    public Queen(Board board, Color color) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "Q";
    }
    
     @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
         Position p = new Position(0, 0);
         
         //above
         
         p.setValues(position.getRow()-1, position.getColomn());
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setRow(p.getRow()-1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         
          //left
         
         p.setValues(position.getRow(), position.getColomn()-1);
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setColomn(p.getColomn()-1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         
         //right
         
         p.setValues(position.getRow(), position.getColomn()+1);
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setColomn(p.getColomn()+1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         
         //below
         
         p.setValues(position.getRow()+1, position.getColomn());
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setRow(p.getRow()+1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         //nw
         
         p.setValues(position.getRow()-1, position.getColomn()-1);
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setValues(p.getRow()-1, p.getColomn()-1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         
          //ne
         
         p.setValues(position.getRow()-1, position.getColomn()+1);
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setValues(p.getRow()-1, p.getColomn()+1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         
         //se
         
         p.setValues(position.getRow()+1, position.getColomn()+1);
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setValues(p.getRow()+1,p.getColomn()+1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
         
         //sw
         
         p.setValues(position.getRow()+1, position.getColomn()-1);
         while(getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
             p.setValues(p.getRow()+1, p.getColomn()-1);
         }
         if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
             mat[p.getRow()][p.getColomn()] = true;
         }
        return mat;
    }
    
}
