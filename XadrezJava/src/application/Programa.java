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
        while (!cm1.getCheckMate()) {
            try {

                UI.clearScreen();
                UI.printMatch(cm1, captured);
                System.out.println("");
                System.out.println("Print Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = cm1.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(cm1.getPieces(), possibleMoves);
                System.out.println("");
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece capturedpiece = cm1.performChessmove(source, target);
                if (capturedpiece != null) {
                    captured.add(capturedpiece);
                }

                if (cm1.getPromoted() != null) {
                    System.out.println("Enter Piece for Promotion (B/N/R/Q)");
                    String q = sc.nextLine().toUpperCase();
                    while(!q.equals("B") && !q.equals("N") && !q.equals("R")&& !q.equals("Q")){
                        System.out.println("Invalid value!Enter Piece for Promotion (B/N/R/Q)"); 
                        q =  sc.nextLine().toUpperCase();
                    }
                    cm1.replacePromotedPiece(q);
                }
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(cm1, captured);

    }

}
