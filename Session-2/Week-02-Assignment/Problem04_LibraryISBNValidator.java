import java.util.Scanner;

public class Problem04_LibraryISBNValidator {
    public static String normalizeCode(String raw) {
        String trimmedCode = raw.trim();

        if (trimmedCode.length() < 3) {
            return trimmedCode.toUpperCase();
        }

        String publisherCode = trimmedCode.substring(0, 3).toUpperCase();
        String remainingPart = trimmedCode.substring(3);

        return publisherCode + remainingPart;
    }

    public static String validateAndFormat(String code) {
        String normalizedCode = normalizeCode(code);

        if (normalizedCode.length() != 13) {
            return "Invalid: code must contain exactly 13 characters";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalizedCode.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < normalizedCode.length(); i++) {
            if (!Character.isDigit(normalizedCode.charAt(i))) {
                return "Invalid: code body must contain only digits";
            }
        }

        String publisherCode = normalizedCode.substring(0, 3);
        String year = normalizedCode.substring(3, 7);
        String catalogNumber = normalizedCode.substring(7);

        StringBuilder result = new StringBuilder();
        result.append("[")
                .append(publisherCode)
                .append("] YEAR: ")
                .append(year)
                .append(" | CATALOG: ")
                .append(catalogNumber);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ISBN-style code: ");
        String code = scanner.nextLine();

        System.out.println(validateAndFormat(code));

        scanner.close();
    }
}