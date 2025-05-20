package strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessUtils {
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
    public static List<String> getStraightPositions(String column, int row) {
        List<String> positions = new ArrayList<>();

        for (String c : COLUMNS) {
            if (!c.equals(column)) {
                positions.add(c + row);
            }
        }

        for (int r : ROWS) {
            if (r != row) {
                positions.add(column + r);
            }
        }

        return positions;
    }

    /**
     * Obtient les positions en diagonale
     */
    public static List<String> getDiagonalPositions(String column, int row) {
        List<String> positions = new ArrayList<>();
        int columnIndex = COLUMNS.indexOf(column);

        for (int[] dir : DIAGONAL_DIRECTIONS) {
            int cIndex = columnIndex;
            int r = row;

            while (true) {
                cIndex += dir[0];
                r += dir[1];

                if (cIndex < 0 || cIndex >= 8 || r < 1 || r > 8) {
                    break;
                }

                positions.add(COLUMNS.get(cIndex) + r);
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