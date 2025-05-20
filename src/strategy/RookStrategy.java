package strategy;

import java.util.List;

public class RookStrategy implements IPieceStrategy {
    public static final String WHITE_ROOK = "♜";
    public static final String BLACK_ROOK = "♖";
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color) {
        return ChessUtils.getStraightPositions(column, row);
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_ROOK : BLACK_ROOK;
    }

}