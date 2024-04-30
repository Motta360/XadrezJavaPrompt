
package boardGame;

/**
 *
 * @author lucas
 */
public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows<1 || columns<1) {
            throw new BoardException("É necessário ao menos uma linha e uma coluna para criar o tabuleiro.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    
    public Piece piece(int row,int column){
        if(!positionExists(row, column)){
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }
    
    public Piece piece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return  pieces[position.getRow()][position.getColomn()];
    }
    public void placePiece(Position position,Piece piece){
        if(thereIsaPiece(position)){
            throw new BoardException("There is already a piece on position: "+ position);
        }
        pieces[position.getRow()][position.getColomn()] = piece;
        piece.position = position;
    }
    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColomn());
    }
    private boolean positionExists(int row , int column){
       return row>= 0 && row < rows && column >=0 && column < columns;
    }
    public boolean thereIsaPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
        
    public Piece removepiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        if(piece(position) == null){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColomn()] = null;
        return aux;
        
    }
}
