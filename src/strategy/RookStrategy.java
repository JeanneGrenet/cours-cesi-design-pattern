package strategy;

import java.util.List;

public class RookStrategy implements IPieceStrategy {
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        return ChessUtils.getStraightPositions(column, row);
    }
}