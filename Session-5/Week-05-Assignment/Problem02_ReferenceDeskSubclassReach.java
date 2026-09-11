public class Problem02_ReferenceDeskSubclassReach {
    static class AccessChecker {
        public static String classifyAccess(
                String fieldModifier,
                String accessorContext) {

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return fieldModifier.equals("protected")
                        || fieldModifier.equals("public")
                        ? "ALLOWED"
                        : "DENIED";
            }

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
                return fieldModifier.equals("public")
                        ? "ALLOWED"
                        : "DENIED";
            }

            if (fieldModifier.equals("private")) {
                return accessorContext.equals("SAME_CLASS")
                        ? "ALLOWED"
                        : "DENIED";
            }

            if (fieldModifier.equals("default")) {
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED"
                        : "DENIED";
            }

            if (fieldModifier.equals("protected")) {
                return accessorContext.equals("DIFFERENT_PACKAGE")
                        ? "DENIED"
                        : "ALLOWED";
            }

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        public static String firstDeniedAttempt(
                String[][] attempts) {

            for (int i = 0; i < attempts.length; i++) {
                String result = classifyAccess(
                        attempts[i][0],
                        attempts[i][1]);

                if (result.equals("DENIED")) {
                    return attempts[i][0]
                            + " via "
                            + attempts[i][1]
                            + " (attempt #" + (i + 1) + ")";
                }
            }

            return "None Denied";
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
                {"public",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
                AccessChecker.firstDeniedAttempt(attempts));
    }
}
