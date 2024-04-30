
package application;

import boardGame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Programa {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         ChessMatch cm1 = new ChessMatch();
         try {
            while(true){
            UI.clearScreen();
            UI.printBoard(cm1.getPieces());
             System.out.println("");
             System.out.println("Print Source: ");
             ChessPosition source = UI.readChessPosition(sc);
             System.out.println("");
             System.out.println("Target: ");
             ChessPosition target = UI.readChessPosition(sc);
             ChessPiece capturedpiece = cm1.performChessmove(source, target);
         }
        } catch (ChessException e) {
             System.out.println(e.getMessage());
             sc.nextLine();
        }
         catch (InputMismatchException e) {
             System.out.println(e.getMessage());
             sc.nextLine();
        }
         
    }
}
