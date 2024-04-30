
package application;

import boardGame.Position;
import chess.ChessMatch;

/**
 *
 * @author lucas
 */
public class Programa {
   
    public static void main(String[] args) {
         ChessMatch cm1 = new ChessMatch();
         UI.printBoard(cm1.getPieces());
         
    }
}
