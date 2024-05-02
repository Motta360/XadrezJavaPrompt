
package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
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
    private boolean checkMate = false;
    
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();
    
    
    public ChessMatch(){
        board = new Board(8, 8);
        currentplayer = Color.WHITE;
        initialSetup();
    }

    public boolean getCheck() {
        return check;
    }
    
    public boolean getCheckMate(){
        return checkMate;
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
        ChessPiece p =(ChessPiece) board.removepiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removepiece(target);
        board.placePiece(target, p);
        
        if (capturedPiece != null) {
           piecesOnTheBoard.remove(capturedPiece);
           capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    
    private void undoMove(Position source,Position target ,Piece capturedPiece){
        
        ChessPiece p = (ChessPiece)board.removepiece(target);
        p.decreaseMoveCount();
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
        if(testCheckMate(opponent(currentplayer))){
            checkMate = true;
        }else{   
            nextTurn();
        }
        
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
    
    private boolean testCheckMate(Color cor){
        if(!testCheck(cor)) return false;
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor()==cor).collect(Collectors.toList());
        for (Piece piece : list) {
            boolean[][] mat = piece.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if(mat[i][j]){
                        Position source = ((ChessPiece) piece).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(cor);
                        undoMove(source, target, capturedPiece);
                        if(!testCheck){
                            return false;
                        }
                    }
                }
                
            }
            
        }
        return true;
    }
    private void placeNewPiece(char column,int row, ChessPiece chesspeice){
        board.placePiece( new ChessPosition(column, row).toPosition(),chesspeice);
        piecesOnTheBoard.add(chesspeice);
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
    
}
