package strategy;

import java.util.List;

public class Board {
    private static final String EMPTY_SQUARE = "□";
    private static final String AVAILABLE_MOVE = "■";
    private static final String PIECE_POSITION = "◉";

    public static void displayBoard(String pieceColumn, int pieceRow, List<String> availableMoves) {
        System.out.println("\n   A  B  C  D  E  F  G  H");
        System.out.println(" -------------------------");

        for (int row = 8; row >= 1; row--) {
            System.out.print(row + "| ");

            for (String column : ChessUtils.COLUMNS) {
                String position = column + row;

                if (column.equals(pieceColumn) && row == pieceRow) {
                    System.out.print(PIECE_POSITION);
                } else if (availableMoves.contains(position)) {
                    System.out.print(AVAILABLE_MOVE);
                } else {
                    System.out.print(EMPTY_SQUARE);
                }
                System.out.print("  "); // Espace après chaque case
            }

            System.out.println("| " + row);
        }

        System.out.println(" -------------------------");
        System.out.println("   A  B  C  D  E  F  G  H");

        System.out.println("\nLégende: ");
        System.out.println(PIECE_POSITION + " : Position actuelle de la pièce");
        System.out.println(AVAILABLE_MOVE + " : Déplacement possible");
        System.out.println(EMPTY_SQUARE + " : Case vide");
    }
}