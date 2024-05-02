
package application;

import boardGame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Programa {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         ChessMatch cm1 = new ChessMatch();
         List<ChessPiece> captured = new ArrayList<>();
         while(true){
         try {
            
            UI.clearScreen();
            UI.printMatch(cm1,captured);
             System.out.println("");
             System.out.println("Print Source: ");
             ChessPosition source = UI.readChessPosition(sc);
             
             boolean[][] possibleMoves = cm1.possibleMoves(source);
             UI.clearScreen();
             UI.printBoard(cm1.getPieces(),possibleMoves);
             System.out.println("");
             System.out.println("Target: ");
             ChessPosition target = UI.readChessPosition(sc);
             ChessPiece capturedpiece = cm1.performChessmove(source, target);
             if(capturedpiece != null){
                 captured.add(capturedpiece);
             }
        } catch (ChessException e) {
             System.out.println(e.getMessage());
             sc.nextLine();
        }
         catch (InputMismatchException e) {
             System.out.println(e.getMessage());
             sc.nextLine();
        }}
         
    }
}
