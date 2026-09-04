import java.util.Scanner;

public class Problem02_PalindromeChecker {
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        return isPalindromeRecursive(text, 0, text.length() - 1);
    }

    public static boolean isPalindromeRecursive(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindromeRecursive(text, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] characters = text.toCharArray();

        for (int i = 0; i < characters.length / 2; i++) {
            char temporary = characters[i];
            characters[i] = characters[characters.length - 1 - i];
            characters[characters.length - 1 - i] = temporary;
        }

        String reversedText = new String(characters);

        return text.equals(reversedText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase: ");
        String input = scanner.nextLine();

        String text = input.replaceAll("\\s+", "").toLowerCase();

        boolean iterativeResult = isPalindromeIterative(text);
        boolean recursiveResult = isPalindromeRecursive(text);
        boolean arrayReversalResult = isPalindromeArrayReversal(text);

        System.out.println("Iterative: "
                + (iterativeResult ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: "
                + (recursiveResult ? "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal: "
                + (arrayReversalResult ? "Palindrome" : "Not Palindrome"));

        scanner.close();
    }
}