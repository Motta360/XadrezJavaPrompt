
package chess;

import boardGame.Board;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/**
 *
 * @author lucas
 */
public class ChessMatch {
    private Board board;
    
    public ChessMatch(){
        board = new Board(8, 8);
        inicialSetup();
    }
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    private void inicialSetup(){
        board.placePiece(new Rook(Color.White,board), new Position(2, 1));
        board.placePiece(new King(Color.Black, board), new Position(0,4));
    }
}
