package strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<String, ChessPiece> pieces = new HashMap<>();

    public Board() {
        placePiece("A1", new ChessPiece("tour", Color.WHITE));
        placePiece("B1", new ChessPiece("cavalier", Color.WHITE));
        placePiece("C1", new ChessPiece("fou", Color.WHITE));
        placePiece("D1", new ChessPiece("dame", Color.WHITE));
        placePiece("E1", new ChessPiece("roi", Color.WHITE));
        placePiece("F1", new ChessPiece("fou", Color.WHITE));
        placePiece("G1", new ChessPiece("cavalier", Color.WHITE));
        placePiece("H1", new ChessPiece("tour", Color.WHITE));

        for (int i = 0; i < 8; i++) {
            String column = MovementUtils.COLUMNS.get(i);
            placePiece(column + "2", new ChessPiece("pion", Color.WHITE));
        }

        placePiece("A8", new ChessPiece("tour", Color.BLACK));
        placePiece("B8", new ChessPiece("cavalier", Color.BLACK));
        placePiece("C8", new ChessPiece("fou", Color.BLACK));
        placePiece("D8", new ChessPiece("dame", Color.BLACK));
        placePiece("E8", new ChessPiece("roi", Color.BLACK));
        placePiece("F8", new ChessPiece("fou", Color.BLACK));
        placePiece("G8", new ChessPiece("cavalier", Color.BLACK));
        placePiece("H8", new ChessPiece("tour", Color.BLACK));

        for (int i = 0; i < 8; i++) {
            String column = MovementUtils.COLUMNS.get(i);
            placePiece(column + "7", new ChessPiece("pion", Color.BLACK));
        }
    }

    public void placePiece(String position, ChessPiece piece) {
        pieces.put(position, piece);
    }

    public boolean movePiece(String fromPosition, String toPosition, Color currentPlayer) {
        if (!pieces.containsKey(fromPosition)) {
            System.out.println("Aucune pièce à cette position!");
            return false;
        }

        ChessPiece piece = pieces.get(fromPosition);

        if (piece.getColor() != currentPlayer) {
            System.out.println("Cette pièce n'est pas à vous!");
            return false;
        }

        String column = fromPosition.substring(0, 1);
        int row = Integer.parseInt(fromPosition.substring(1));

        PieceContext context = new PieceContext(piece.getStrategy());
        List<String> allowedMoves = context.getAllowedPositions(column, row, piece.getColor(), this);

        if (!allowedMoves.contains(toPosition)) {
            System.out.println("Ce mouvement n'est pas autorisé pour cette pièce!");
            return false;
        }

        if (pieces.containsKey(toPosition)) {
            ChessPiece targetPiece = pieces.get(toPosition);

            if (targetPiece.getColor() == currentPlayer) {
                System.out.println("Vous ne pouvez pas capturer votre propre pièce!");
                return false;
            }

            System.out.println("Vous avez capturé un(e) " + targetPiece.getType() + " adverse!");
        }

        pieces.put(toPosition, piece);
        pieces.remove(fromPosition);

        return true;
    }

    public ChessPiece getPiece(String position) {
        return pieces.get(position);
    }

    public boolean hasPiece(String position) {
        return pieces.containsKey(position);
    }

    public void displayBoard(String selectedPosition, List<String> availableMoves) {
        final String MOVE_MARKER = "●";

        System.out.println();
        System.out.print("    ");
        for (String col : MovementUtils.COLUMNS) {
            System.out.print(col + "   ");
        }
        System.out.println();

        System.out.print("  ┌");
        for (int i = 0; i < 8; i++) {
            System.out.print("───");
            if (i < 7) System.out.print("┬");
        }
        System.out.println("┐");

        for (int row = 8; row >= 1; row--) {
            System.out.print(row + " │");

            for (int col = 0; col < 8; col++) {
                String column = MovementUtils.COLUMNS.get(col);
                String position = column + row;

                String cellContent = " ";

                if (pieces.containsKey(position)) {
                    cellContent = pieces.get(position).getSymbol();
                } else if (availableMoves != null && availableMoves.contains(position)) {
                    cellContent = MOVE_MARKER;
                }

                System.out.print(" " + cellContent + " │");
            }

            System.out.println(" " + row);

            if (row > 1) {
                System.out.print("  ├");
                for (int i = 0; i < 8; i++) {
                    System.out.print("───");
                    if (i < 7) System.out.print("┼");
                }
                System.out.println("┤");
            }
        }

        System.out.print("  └");
        for (int i = 0; i < 8; i++) {
            System.out.print("───");
            if (i < 7) System.out.print("┴");
        }
        System.out.println("┘");

        System.out.print("    ");
        for (String col : MovementUtils.COLUMNS) {
            System.out.print(col + "   ");
        }
        System.out.println();

        if (availableMoves != null && !availableMoves.isEmpty()) {
            System.out.println("\nLégende: " + MOVE_MARKER + " = Mouvement possible");
        }
    }

    public List<String> getValidMovesForPiece(String position) {
        if (!pieces.containsKey(position)) {
            return List.of();
        }

        ChessPiece piece = pieces.get(position);
        String column = position.substring(0, 1);
        int row = Integer.parseInt(position.substring(1));

        PieceContext context = new PieceContext(piece.getStrategy());
        return context.getAllowedPositions(column, row, piece.getColor(), this);
    }
}