package strategy;

import java.util.List;

public class BishopStrategy implements IPieceStrategy {
    public static final String BLACK_BISHOP = "♗";
    public static final String WHITE_BISHOP = "♝";

    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        return ChessUtils.getDiagonalPositions(column, row);
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_BISHOP : BLACK_BISHOP;
    }
}