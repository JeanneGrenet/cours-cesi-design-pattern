package strategy;

public class ChessPiece {
    private final String symbol;
    private final Color color;
    private final IPieceStrategy strategy;
    private final String type;

    public ChessPiece(String type, Color color) {
        this.type = type;
        this.color = color;

        this.strategy = switch (type) {
            case "roi" -> new KingStrategy();
            case "dame" -> new QueenStrategy();
            case "tour" -> new RookStrategy();
            case "fou" -> new BishopStrategy();
            case "cavalier" -> new KnightStrategy();
            case "pion" -> new PawnStrategy();
            default -> throw new IllegalArgumentException("Type de pièce inconnu: " + type);
        };
        this.symbol = this.strategy.getPieceSymbol(color);
    }

    public String getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public IPieceStrategy getStrategy() {
        return strategy;
    }

    public String getType() {
        return type;
    }
}