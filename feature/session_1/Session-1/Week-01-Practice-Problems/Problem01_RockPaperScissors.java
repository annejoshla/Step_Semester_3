import java.util.Random;
import java.util.Scanner;

public class Problem01_RockPaperScissors {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        boolean playerWins =
                (playerMove.equalsIgnoreCase("Rock")
                        && computerMove.equalsIgnoreCase("Scissors"))
                || (playerMove.equalsIgnoreCase("Paper")
                        && computerMove.equalsIgnoreCase("Rock"))
                || (playerMove.equalsIgnoreCase("Scissors")
                        && computerMove.equalsIgnoreCase("Paper"));

        return playerWins ? "Player Wins" : "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int rounds = 5;
        int wins = 0;
        int losses = 0;
        int draws = 0;

        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        for (int i = 0; i < rounds; i++) {
            System.out.print("Round " + (i + 1)
                    + " - Enter Rock, Paper, or Scissors: ");

            String playerMove = scanner.nextLine().trim();

            while (!isValidMove(playerMove)) {
                System.out.print("Invalid input. Enter Rock, Paper, or Scissors: ");
                playerMove = scanner.nextLine().trim();
            }

            String computerMove = MOVES[random.nextInt(MOVES.length)];
            String result = playRound(playerMove, computerMove);

            playerMoves[i] = formatMove(playerMove);
            computerMoves[i] = computerMove;
            results[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("\nFinal Summary");
        System.out.printf("%-8s %-15s %-15s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");

        for (int i = 0; i < rounds; i++) {
            System.out.printf("%-8d %-15s %-15s %-15s%n",
                    i + 1,
                    playerMoves[i],
                    computerMoves[i],
                    results[i]);
        }

        double winPercentage = (wins * 100.0) / rounds;

        System.out.printf(
                "Wins: %d | Losses: %d | Draws: %d | Win Percentage: %.2f%%%n",
                wins,
                losses,
                draws,
                winPercentage);

        scanner.close();
    }

    private static boolean isValidMove(String move) {
        return move.equalsIgnoreCase("Rock")
                || move.equalsIgnoreCase("Paper")
                || move.equalsIgnoreCase("Scissors");
    }

    private static String formatMove(String move) {
        return move.substring(0, 1).toUpperCase()
                + move.substring(1).toLowerCase();
    }
}