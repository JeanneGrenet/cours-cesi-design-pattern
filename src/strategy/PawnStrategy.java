package strategy;

import java.util.ArrayList;
import java.util.List;

public class PawnStrategy implements IPieceStrategy {
    public static final String BLACK_PAWN = "♙";
    public static final String WHITE_PAWN = "♟";

    @Override
    public List<String> getAllowedPositions(String column, int row, Color color, Board board) {
        List<String> allowedPositions = new ArrayList<>();
        int columnIndex = MovementUtils.COLUMNS.indexOf(column);

        int direction = (color == Color.WHITE) ? 1 : -1;
        int startRow = (color == Color.WHITE) ? 2 : 7;

        int newRow = row + direction;
        if (MovementUtils.isValidPosition(columnIndex, newRow)) {
            allowedPositions.add(column + newRow);

            if (row == startRow) {
                int doubleMove = row + (2 * direction);
                if (MovementUtils.isValidPosition(columnIndex, doubleMove)) {
                    allowedPositions.add(column + doubleMove);
                }
            }
        }

        return allowedPositions;
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_PAWN : BLACK_PAWN;
    }
}