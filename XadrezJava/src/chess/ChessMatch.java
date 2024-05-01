
package chess;

import boardGame.Board;
import boardGame.Piece;
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
    private Piece makeMove(Position source,Position target){
        Piece p = board.removepiece(source);
        Piece capturedPiece = board.removepiece(target);
        board.placePiece(target, p);
        return capturedPiece;
    }
    
    public ChessPiece performChessmove(ChessPosition sourcePosition,ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source,target);
        return (ChessPiece)capturedPiece;
    }
    
    private void validateSourcePosition(Position position){
        if(!board.thereIsaPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for this piece");
        }
    }
    
    private void placeNewPiece(char column,int row, ChessPiece chesspeice){
        board.placePiece( new ChessPosition(column, row).toPosition(),chesspeice);
    }

    private void inicialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
    
}
