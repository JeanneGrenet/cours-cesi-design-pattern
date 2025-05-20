package strategy;

import java.util.ArrayList;
import java.util.List;

public class KingStrategy implements IPieceStrategy {
    public static final String BLACK_KING = "♔";
    public static final String WHITE_KING = "♚";

    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        List<String> allowedPositions = new ArrayList<>();

        allowedPositions.addAll(MovementUtils.getStraightPositionsOneStep(column, row));
        allowedPositions.addAll(MovementUtils.getDiagonalPositionsOneStep(column, row));

        return allowedPositions;
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_KING : BLACK_KING;
    }
}