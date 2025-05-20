package strategy;

import java.util.ArrayList;
import java.util.List;

public class KnightStrategy implements IPieceStrategy {
    public static final String BLACK_KNIGHT = "♘";
    public static final String WHITE_KNIGHT = "♞";


    @Override
    public List<String> getAllowedPositions(String column, int row, Color color, Board board) {
        List<String> positions = new ArrayList<>();
        int columnIndex = MovementUtils.COLUMNS.indexOf(column);

        for (int[] move : MovementUtils.KNIGHT_MOVES) {
            int newColumnIndex = columnIndex + move[0];
            int newRow = row + move[1];

            if (newColumnIndex >= 0 && newColumnIndex < 8 && newRow >= 1 && newRow <= 8) {
                positions.add(MovementUtils.COLUMNS.get(newColumnIndex) + newRow);
            }
        }

        return positions;
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT ;
    }
}