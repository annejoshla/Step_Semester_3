import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem04_FirstNonRepeatingCharacter {
    public static char findFirstNonRepeatingChar(String text) {
        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (characterFrequency.containsKey(character)) {
                characterFrequency.put(character, characterFrequency.get(character) + 1);
            } else {
                characterFrequency.put(character, 1);
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (characterFrequency.get(character) == 1) {
                return character;
            }
        }

        return '\0';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or sentence: ");
        String text = scanner.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        }

        scanner.close();
    }
}