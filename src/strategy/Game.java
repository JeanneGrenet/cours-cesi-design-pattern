package strategy;

import java.util.List;
import java.util.Scanner;

public class Game {
    private static boolean isPlaying = true;
    private static int turn = 0;
    private Board board;
    private String selectedPosition = null;
    private List<String> availableMoves = null;
    private Scanner scanner;

    public Game() {
        initialize();
        playGame();
    }

    private void initialize() {
        board = new Board();
        scanner = new Scanner(System.in);
        displayInstructions();
        board.displayBoard(null, null);
    }

    private void playGame() {
        while (isPlaying) {
            processCurrentTurn();
        }
        scanner.close();
    }

    private void displayInstructions() {
        System.out.println("Commandes :");
        System.out.println("- Pour sélectionner une pièce : entrez sa position (ex: 'E2')");
        System.out.println("- Pour déplacer une pièce sélectionnée : entrez la destination (ex: 'E4')");
        System.out.println("- Pour annuler une sélection : tapez 'cancel'");
        System.out.println("- Pour quitter : tapez 'exit'");
        System.out.println();
    }

    private void displayTurnInfo(Color currentPlayer) {
        String playerName = currentPlayer == Color.WHITE ? "Blancs" : "Noirs";
        System.out.println("\nTour " + (turn + 1) + " - Aux " + playerName + " de jouer");
    }

    private String getUserInput() {
        if (selectedPosition == null) {
            System.out.print("Sélectionnez une pièce (ex: 'E2') : ");
        } else {
            ChessPiece piece = board.getPiece(selectedPosition);
            if (piece != null) {
                System.out.println("Pièce sélectionnée : " + piece.getType() + " en " + selectedPosition);
                System.out.print("Entrez la destination ou 'cancel' pour annuler : ");
            }
        }
        return scanner.nextLine().trim().toUpperCase();
    }

    private boolean processSpecialCommands(String input) {
        if (input.equals("EXIT")) {
            System.out.println("Fin du jeu");
            isPlaying = false;
            return true;
        }

        if (input.equals("CANCEL") && selectedPosition != null) {
            System.out.println("Sélection annulée");
            selectedPosition = null;
            availableMoves = null;
            board.displayBoard(null, null);
            return true;
        }

        return false;
    }

    private void processCurrentTurn() {
        Color currentPlayer = getCurrentPlayer();
        displayTurnInfo(currentPlayer);

        String input = getUserInput();

        if (processSpecialCommands(input)) {
            return;
        }

        if (!isValidPosition(input)) {
            System.out.println("Position invalide. Format attendu : lettre (A-H) + chiffre (1-8)");
            return;
        }

        if (selectedPosition == null) {
            selectPiece(input, currentPlayer);
        } else {
            movePiece(input, currentPlayer);
        }
    }

    private Color getCurrentPlayer() {
        return turn % 2 == 0 ? Color.WHITE : Color.BLACK;
    }

    private void selectPiece(String position, Color currentPlayer) {
        if (!board.hasPiece(position)) {
            System.out.println("Aucune pièce à cette position!");
            return;
        }

        ChessPiece piece = board.getPiece(position);
        if (piece.getColor() != currentPlayer) {
            System.out.println("Cette pièce appartient à l'adversaire!");
            return;
        }

        selectedPosition = position;
        availableMoves = board.getValidMovesForPiece(position);

        if (availableMoves.isEmpty()) {
            System.out.println("Cette pièce n'a aucun mouvement possible!");
            selectedPosition = null;
            return;
        }

        System.out.println("Pièce sélectionnée : " + piece.getType() + " en " + position);

        board.displayBoard(selectedPosition, availableMoves);
    }

    private void movePiece(String destination, Color currentPlayer) {
        boolean moved = board.movePiece(selectedPosition, destination, currentPlayer);

        if (moved) {
            System.out.println("✓ Pièce déplacée de " + selectedPosition + " à " + destination);

            turn++;

            selectedPosition = null;
            availableMoves = null;

            board.displayBoard(null, null);
        } else {
            board.displayBoard(selectedPosition, availableMoves);
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