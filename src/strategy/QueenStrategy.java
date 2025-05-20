package strategy;

import java.util.ArrayList;
import java.util.List;

public class QueenStrategy implements IPieceStrategy {
    public static final String BLACK_QUEEN = "♕";
    public static final String WHITE_QUEEN = "♛";

    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        List<String> allowedPositions = new ArrayList<>();

        allowedPositions.addAll(MovementUtils.getStraightPositions(column, row));
        allowedPositions.addAll(MovementUtils.getDiagonalPositions(column, row));

        return allowedPositions;
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_QUEEN : BLACK_QUEEN;
    }
}