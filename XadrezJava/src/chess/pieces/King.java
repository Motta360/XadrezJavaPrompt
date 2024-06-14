package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import com.sun.source.tree.InstanceOfTree;

/**
 *
 * @author lucas
 */
public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(color, board);
        this.chessMatch = chessMatch;
    }
    
    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }
    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColomn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }
        //below

        p.setValues(position.getRow() + 1, position.getColomn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }
        //left

        p.setValues(position.getRow(), position.getColomn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }

        //right
        p.setValues(position.getRow(), position.getColomn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }

        //nw
        p.setValues(position.getRow() - 1, position.getColomn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }

        //ne
        p.setValues(position.getRow() - 1, position.getColomn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }

        //sw
        p.setValues(position.getRow() + 1, position.getColomn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }

        //se
        p.setValues(position.getRow() + 1, position.getColomn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColomn()] = true;
        }
        
        //Special Move Castleling
        
        if( getMoveCount() == 0 && !chessMatch.getCheck()){
            //kingside
            Position posR1 = new Position(position.getRow(), position.getColomn() + 3);
            if(testRookCastling(posR1)){
                Position p1 = new Position(position.getRow(), position.getColomn() + 1);
                Position p2 = new Position(position.getRow(), position.getColomn() + 2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColomn()+2] = true;
                }
            }
             //Queenside
            Position posR2 = new Position(position.getRow(), position.getColomn() - 4);
            if(testRookCastling(posR2)){
                Position p1 = new Position(position.getRow(), position.getColomn() - 1);
                Position p2 = new Position(position.getRow(), position.getColomn() - 2);
                Position p3 = new Position(position.getRow(), position.getColomn() - 3);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][position.getColomn()-2] = true;
                }
            }
        }
        return mat;
    }

}
