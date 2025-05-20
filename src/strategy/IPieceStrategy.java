package strategy;

import java.util.List;

public interface IPieceStrategy {
    List<String> getAllowedPositions(String column, int row, Color color);

    String getPieceSymbol(Color color);
}
