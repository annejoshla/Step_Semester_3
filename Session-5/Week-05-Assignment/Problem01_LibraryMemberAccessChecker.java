public class Problem01_LibraryMemberAccessChecker {
    static class LibraryMember {
        private String membershipPin;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipPin, String branchCode,
                            double finesOwed, String displayName) {
            this.membershipPin = membershipPin;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }

        public String getMembershipPin() {
            return membershipPin;
        }
    }

    static class AccessChecker {
        public static String classifyAccess(
                String fieldModifier,
                String accessorContext) {

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

        public static String summarizeByModifier(
                String[][] attempts) {

            String[] modifiers = {
                    "private",
                    "default",
                    "protected",
                    "public"
            };

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < modifiers.length; i++) {
                int allowed = 0;
                int denied = 0;

                for (String[] attempt : attempts) {
                    if (!attempt[0].equals(modifiers[i])) {
                        continue;
                    }

                    if (classifyAccess(attempt[0], attempt[1])
                            .equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }

                if (i > 0) {
                    result.append(" | ");
                }

                result.append(modifiers[i])
                        .append(": ")
                        .append(allowed)
                        .append(" allowed / ")
                        .append(denied)
                        .append(" denied");
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));
    }
}