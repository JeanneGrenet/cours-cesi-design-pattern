package strategy;

import java.util.ArrayList;
import java.util.List;

public class PawnStrategy implements IPieceStrategy {
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        List<String> allowedPositions = new ArrayList<>();
        int columnIndex = ChessUtils.COLUMNS.indexOf(column);

        int direction = (color == Color.WHITE) ? 1 : -1;
        int startRow = (color == Color.WHITE) ? 2 : 7;

        int newRow = row + direction;
        if (ChessUtils.isValidPosition(columnIndex, newRow)) {
            allowedPositions.add(column + newRow);

            if (row == startRow) {
                int doubleMove = row + (2 * direction);
                if (ChessUtils.isValidPosition(columnIndex, doubleMove)) {
                    allowedPositions.add(column + doubleMove);
                }
            }
        }

        return allowedPositions;
    }
}