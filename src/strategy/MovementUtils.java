package strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovementUtils {
    public static final ArrayList<String> COLUMNS = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
    public static final ArrayList<Integer> ROWS = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    public static final int[][] STRAIGHT_DIRECTIONS = {
            {0, 1},  // haut
            {1, 0},  // droite
            {0, -1}, // bas
            {-1, 0}  // gauche
    };

    public static final int[][] DIAGONAL_DIRECTIONS = {
            {1, 1},   // haut-droite
            {1, -1},  // bas-droite
            {-1, -1}, // bas-gauche
            {-1, 1}   // haut-gauche
    };

    public static final int[][] KNIGHT_MOVES = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
    };

    /**
     * Obtient les positions en ligne droite
     */
    public static List<String> getStraightPositions(String column, int row, Board board) {
        List<String> positions = new ArrayList<>();
        int columnIndex = COLUMNS.indexOf(column);

        for (int[] dir : STRAIGHT_DIRECTIONS) {
            int newColumnIndex = columnIndex;
            int newRow = row;

            while (true) {
                newColumnIndex += dir[0];
                newRow += dir[1];

                if (newColumnIndex < 0 || newColumnIndex >= 8 || newRow < 1 || newRow > 8) {
                    break;
                }

                String newPosition = COLUMNS.get(newColumnIndex) + newRow;
                if (board.hasPiece(newPosition)) {
                    ChessPiece targetPiece = board.getPiece(newPosition);
                    ChessPiece sourcePiece = board.getPiece(column + row);
                    if (targetPiece.getColor() != sourcePiece.getColor()) {
                        positions.add(newPosition);
                    }
                }

                positions.add(newPosition);
            }
        }
        return positions;
    }

    /**
     * Obtient les positions en diagonale
     */
    public static List<String> getDiagonalPositions(String column, int row, Board board) {
        List<String> positions = new ArrayList<>();
        int columnIndex = COLUMNS.indexOf(column);

        for (int[] dir : DIAGONAL_DIRECTIONS) {
            int newColumnIndex = columnIndex;
            int newRow = row;

            while (true) {
                newColumnIndex += dir[0];
                newRow += dir[1];

                if (newColumnIndex < 0 || newColumnIndex >= 8 || newRow < 1 || newRow > 8) {
                    break;
                }

                String newPosition = COLUMNS.get(newColumnIndex) + newRow;
                if (board.hasPiece(newPosition)) {
                    ChessPiece targetPiece = board.getPiece(newPosition);
                    ChessPiece sourcePiece = board.getPiece(column + row);


                    if (targetPiece.getColor() != sourcePiece.getColor()) {
                        positions.add(newPosition);
                    }
                    break;
                }

                positions.add(newPosition);
            }
        }

        return positions;
    }

    /**
     * Obtient les positions en ligne droite dans un rayon de 1 case
     */
    public static List<String> getStraightPositionsOneStep(String column, int row) {
        List<String> positions = new ArrayList<>();
        int columnIndex = COLUMNS.indexOf(column);

        for (int[] dir : STRAIGHT_DIRECTIONS) {
            int newColumnIndex = columnIndex + dir[0];
            int newRow = row + dir[1];

            // Vérifier si la nouvelle position est sur l'échiquier
            if (newColumnIndex >= 0 && newColumnIndex < 8 && newRow >= 1 && newRow <= 8) {
                positions.add(COLUMNS.get(newColumnIndex) + newRow);
            }
        }

        return positions;
    }

    /**
     * Obtient les positions en diagonale dans un rayon de 1 case
     */
    public static List<String> getDiagonalPositionsOneStep(String column, int row) {
        List<String> positions = new ArrayList<>();
        int columnIndex = COLUMNS.indexOf(column);

        for (int[] dir : DIAGONAL_DIRECTIONS) {
            int newColumnIndex = columnIndex + dir[0];
            int newRow = row + dir[1];

            if (newColumnIndex >= 0 && newColumnIndex < 8 && newRow >= 1 && newRow <= 8) {
                positions.add(COLUMNS.get(newColumnIndex) + newRow);
            }
        }

        return positions;
    }

    /**
     * Vérifie si une position est sur l'échiquier
     */
    public static boolean isValidPosition(int columnIndex, int row) {
        return columnIndex >= 0 && columnIndex < 8 && row >= 1 && row <= 8;
    }
}