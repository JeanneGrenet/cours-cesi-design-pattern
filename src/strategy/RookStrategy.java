package strategy;

import java.util.List;

public class RookStrategy implements IPieceStrategy {
    public static final String WHITE_ROOK = "♜";
    public static final String BLACK_ROOK = "♖";
    @Override
    public List<String> getAllowedPositions(String column, int row, Color color, Board board) {
        return MovementUtils.getStraightPositions(column, row, board);
    }

    @Override
    public String getPieceSymbol(Color color) {
        return color == Color.WHITE ? WHITE_ROOK : BLACK_ROOK;
    }

}