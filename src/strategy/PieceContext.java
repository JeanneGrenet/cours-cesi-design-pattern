package strategy;

import java.util.List;

public class PieceContext {
    private IPieceStrategy strategy;

    public PieceContext(IPieceStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IPieceStrategy strategy) {
        this.strategy = strategy;
    }

    public List<String> getAllowedPositions(String column, int row, Color color) {
        return strategy.getAllowedPositions(column, row, color);
    }
}
