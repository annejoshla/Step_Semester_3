public class Problem01_MovieTicketAccessChecker {
    static class MovieTicket {
        private int seatNumber;
        String screenId;
        protected double ticketPrice;
        public String movieTitle;

        public int getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(int seatNumber) {
            this.seatNumber = seatNumber;
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
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals("SUBCLASS")
                        ? "ALLOWED"
                        : "DENIED";
            }

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        public static String summarizeBatch(String[][] attempts) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                if (classifyAccess(attempt[0], attempt[1])
                        .equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            }

            return "Allowed: " + allowed + " | Denied: " + denied;
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        MovieTicket ticket = new MovieTicket();
        ticket.setSeatNumber(18);

        System.out.println("Seat Number: " + ticket.getSeatNumber());
        System.out.println(AccessChecker.classifyAccess(
                "private", "SAME_CLASS"));

        System.out.println(AccessChecker.classifyAccess(
                "protected", "DIFFERENT_PACKAGE"));

        System.out.println(AccessChecker.summarizeBatch(attempts));
    }
}