import java.util.Scanner;

public class Problem05_BankTransactionReferenceValidator {
    public static String normalizeReference(String raw) {
        String trimmedReference = raw.trim();

        if (trimmedReference.length() < 3) {
            return trimmedReference.toUpperCase();
        }

        String bankCode = trimmedReference.substring(0, 3).toUpperCase();
        String remainingPart = trimmedReference.substring(3);

        return bankCode + remainingPart;
    }

    public static String validateAndFormat(String reference) {
        String normalizedReference = normalizeReference(reference);

        if (normalizedReference.length() != 14) {
            return "Invalid: reference must contain exactly 14 characters";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalizedReference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 3; i < normalizedReference.length(); i++) {
            if (!Character.isDigit(normalizedReference.charAt(i))) {
                return "Invalid: reference body must contain only digits";
            }
        }

        String bankCode = normalizedReference.substring(0, 3);
        String date = normalizedReference.substring(3, 9);
        String sequence = normalizedReference.substring(9);

        StringBuilder result = new StringBuilder();
        result.append("[")
                .append(bankCode)
                .append("] DATE: ")
                .append(date.substring(0, 2))
                .append("/")
                .append(date.substring(2, 4))
                .append("/")
                .append(date.substring(4, 6))
                .append(" | SEQ: ")
                .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String reference = scanner.nextLine();

        System.out.println(validateAndFormat(reference));

        scanner.close();
    }
}