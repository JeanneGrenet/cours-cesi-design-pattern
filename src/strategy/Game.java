package strategy;

import java.util.List;
import java.util.Scanner;

public class Game {
    private static boolean isPlaying = true;
    private static int turn = 0;
    private Board board;
    private String selectedPosition = null;
    private List<String> availableMoves = null;

    public Game() {
        board = new Board();

        System.out.println("Commandes :");
        System.out.println("- Pour sélectionner une pièce : entrez sa position (ex: 'E2')");
        System.out.println("- Pour déplacer une pièce sélectionnée : entrez la destination (ex: 'E4')");
        System.out.println("- Pour annuler une sélection : tapez 'cancel'");
        System.out.println("- Pour quitter : tapez 'exit'");
        System.out.println();

        board.displayBoard(null, null);

        while (isPlaying) {
            Color currentPlayer = turn % 2 == 0 ? Color.WHITE : Color.BLACK;
            String playerName = currentPlayer == Color.WHITE ? "Blancs" : "Noirs";

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n╔═══════════════════════════════╗");
            System.out.println("║ Tour " + (turn + 1) + " - Au " + playerName + " de jouer ║");
            System.out.println("╚═══════════════════════════════╝");

            if (selectedPosition == null) {
                System.out.print("Sélectionnez une pièce (ex: 'E2') : ");
            } else {
                ChessPiece piece = board.getPiece(selectedPosition);
                if (piece != null) {
                    System.out.println("Pièce sélectionnée : " + piece.getType() + " en " + selectedPosition);
                    System.out.print("Entrez la destination ou 'cancel' pour annuler : ");
                }
            }

            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Fin du jeu");
                isPlaying = false;
                continue;
            }

            if (input.equals("CANCEL")) {
                System.out.println("Sélection annulée");
                selectedPosition = null;
                availableMoves = null;
                board.displayBoard(null, null);
                continue;
            }

            if (!isValidPosition(input)) {
                System.out.println("Position invalide. Format attendu : lettre (A-H) + chiffre (1-8)");
                continue;
            }

            if (selectedPosition == null) {
                if (!board.hasPiece(input)) {
                    System.out.println("Aucune pièce à cette position!");
                    continue;
                }

                ChessPiece piece = board.getPiece(input);
                if (piece.getColor() != currentPlayer) {
                    System.out.println("Cette pièce appartient à l'adversaire!");
                    continue;
                }

                selectedPosition = input;
                availableMoves = board.getValidMovesForPiece(selectedPosition);

                if (availableMoves.isEmpty()) {
                    System.out.println("Cette pièce n'a aucun mouvement possible!");
                    selectedPosition = null;
                    continue;
                }

                System.out.println("Pièce sélectionnée : " + piece.getType() + " en " + selectedPosition);

                board.displayBoard(selectedPosition, availableMoves);
            }
            else {
                boolean moved = board.movePiece(selectedPosition, input, currentPlayer);

                if (moved) {
                    System.out.println("✓ Pièce déplacée de " + selectedPosition + " à " + input);

                    turn++;

                    selectedPosition = null;
                    availableMoves = null;

                    board.displayBoard(null, null);
                } else {
                    board.displayBoard(selectedPosition, availableMoves);
                }
            }
        }
    }

    private boolean isValidPosition(String position) {
        if (position.length() != 2) return false;

        char column = position.charAt(0);
        if (column < 'A' || column > 'H') return false;

        try {
            int row = Integer.parseInt(position.substring(1));
            return row >= 1 && row <= 8;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}