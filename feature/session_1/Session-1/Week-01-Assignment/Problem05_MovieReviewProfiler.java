import java.util.Scanner;

public class Problem05_MovieReviewProfiler {
    public static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (int i = 0; i < words.length; i++) {
            int wordLength = words[i].length();

            if (wordLength >= 1 && wordLength <= 4) {
                shortWords++;
            } else if (wordLength >= 5 && wordLength <= 8) {
                mediumWords++;
            } else if (wordLength >= 9) {
                longWords++;
            }
        }

        System.out.println("Short: " + shortWords
                + " | Medium: " + mediumWords
                + " | Long: " + longWords);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter movie review: ");
        String review = scanner.nextLine();

        classifyWordLengths(review);

        scanner.close();
    }
}