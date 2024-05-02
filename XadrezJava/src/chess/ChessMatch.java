
package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lucas
 */
public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentplayer;
    private boolean check = false;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();
    
    
    public ChessMatch(){
        board = new Board(8, 8);
        currentplayer = Color.WHITE;
        inicialSetup();
    }

    public boolean getCheck() {
        return check;
    }
    
    

    public int getTurn() {
        return turn;
    }

    public Color getCurrentplayer() {
        return currentplayer;
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
    
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    private Piece makeMove(Position source,Position target){
        Piece p = board.removepiece(source);
        Piece capturedPiece = board.removepiece(target);
        board.placePiece(target, p);
        
        if (capturedPiece != null) {
           piecesOnTheBoard.remove(capturedPiece);
           capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    
    private void undoMove(Position source,Position target ,Piece capturedPiece){
        
        Piece p = board.removepiece(target);
        board.placePiece(source, p);
        
        if(capturedPiece != null){
            board.placePiece(target, capturedPiece);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
        
    }
    public ChessPiece performChessmove(ChessPosition sourcePosition,ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturedPiece = makeMove(source,target);
        if(testCheck(currentplayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You cant put yourself at check");
        }
        
        check = (testCheck(opponent(currentplayer))) ? true : false;
        
        
        nextTurn();
        return (ChessPiece)capturedPiece;
    }
    
    
    private void validateSourcePosition(Position position){
        if(!board.thereIsaPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        if(currentplayer != ((ChessPiece) board.piece(position)).getColor()){
            throw new ChessException("The chosen piece is not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for this piece");
        }
    }
    private void validateTargetPosition(Position source,Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The piece cant move to this position");
        }
    }
    private void nextTurn(){
        turn++;
        currentplayer = (currentplayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    private Color opponent(Color color){
        if(color == Color.WHITE){
            return Color.BLACK;
        }else{
            return color.WHITE;
        }
    }
    
    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor()==color).collect(Collectors.toList());
        for (Piece piece : list) {
            if(piece instanceof King){
                return (ChessPiece)piece;
            }
        }
        throw new IllegalStateException("There is no "+ color+" King on the board");
    }
    
    private boolean testCheck(Color cor){
        Position KingPosition = king(cor).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor()== opponent(cor)).collect(Collectors.toList());
        for (Piece opponentPiece : opponentPieces) {
            boolean[][] mat = opponentPiece.possibleMoves();
            if(mat[KingPosition.getRow()][KingPosition.getColomn()]){
                return true;
            }
            
        }
        return false;
    } 
    private void placeNewPiece(char column,int row, ChessPiece chesspeice){
        board.placePiece( new ChessPosition(column, row).toPosition(),chesspeice);
        piecesOnTheBoard.add(chesspeice);
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
