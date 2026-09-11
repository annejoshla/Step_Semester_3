public class Problem02_SubclassTicketAccess {
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
    }

    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}