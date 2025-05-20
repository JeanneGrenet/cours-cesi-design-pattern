package strategy;

import java.util.ArrayList;
import java.util.List;

public class KingStrategy implements IPieceStrategy {
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        List<String> allowedPositions = new ArrayList<>();

        allowedPositions.addAll(ChessUtils.getStraightPositionsOneStep(column, row));
        allowedPositions.addAll(ChessUtils.getDiagonalPositionsOneStep(column, row));

        return allowedPositions;
    }
}