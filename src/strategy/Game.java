package strategy;

import java.util.List;
import java.util.Scanner;

public class Game {
    private static boolean isPlaying = true;
    private static int turn = 0;

    public Game() {
        while (isPlaying) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez une pièce et une position (ex: 'tour B3') :");

            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ");

            if (parts.length == 1 && parts[0].equals("exit")) {
                System.out.println("Fin du jeu");
                isPlaying = false;
                return;
            }

            if (parts.length != 2) {
                System.out.println("Format invalide. Exemple attendu : 'tour B3'");
                continue;
            }

            String piece = parts[0];
            String position = parts[1].toUpperCase();

            String column = position.substring(0, 1);
            int row = Integer.parseInt(position.substring(1));
            Color color = turn % 2 == 0 ? Color.WHITE : Color.BLACK;

            IPieceStrategy strategy = switch (piece) {
                case "tour" -> new RookStrategy();
                case "pion" -> new PawnStrategy();
                case "cavalier" -> new KnightStrategy();
                case "fou" -> new BishopStrategy();
                case "roi" -> new KingStrategy();
                case "dame" -> new QueenStrategy();
                default -> {
                    System.out.println("Pièce inconnue : " + piece);
                    yield null;
                }
            };

            if (strategy == null) return;

            PieceContext context = new PieceContext(strategy);

            List<String> moves = context.getAllowedPositions(column, row, color);

            System.out.println("Déplacements possibles pour la " + piece + " en " + position + " :");
            for (String move : moves) {
                System.out.print(move + " ");
            }
            System.out.println("\n");
            turn++;
        }
    }
}

