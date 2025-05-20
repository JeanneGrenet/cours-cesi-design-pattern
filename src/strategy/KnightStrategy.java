package strategy;

import java.util.ArrayList;
import java.util.List;

public class KnightStrategy implements IPieceStrategy {
    public static final String BLACK_KNIGHT = "♘";
    public static final String WHITE_KNIGHT = "♞";


    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        List<String> positions = new ArrayList<>();
        int columnIndex = ChessUtils.COLUMNS.indexOf(column);

        for (int[] move : ChessUtils.KNIGHT_MOVES) {
            int newColumnIndex = columnIndex + move[0];
            int newRow = row + move[1];

            if (newColumnIndex >= 0 && newColumnIndex < 8 && newRow >= 1 && newRow <= 8) {
                positions.add(ChessUtils.COLUMNS.get(newColumnIndex) + newRow);
            }
        }

        return positions;
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT ;
    }
}