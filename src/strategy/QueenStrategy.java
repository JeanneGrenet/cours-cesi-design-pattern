package strategy;

import java.util.ArrayList;
import java.util.List;

public class QueenStrategy implements IPieceStrategy {
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        List<String> allowedPositions = new ArrayList<>();

        allowedPositions.addAll(ChessUtils.getStraightPositions(column, row));
        allowedPositions.addAll(ChessUtils.getDiagonalPositions(column, row));

        return allowedPositions;
    }
}