package strategy;

import java.util.List;

public class BishopStrategy implements IPieceStrategy {
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        return ChessUtils.getDiagonalPositions(column, row);
    }
}