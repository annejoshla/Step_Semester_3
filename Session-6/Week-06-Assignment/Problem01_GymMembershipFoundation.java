public class Problem01_GymMembershipFoundation {
    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            if (monthlyFee <= 0) {
                throw new IllegalArgumentException("Monthly fee must be positive");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public static String enrollBatch(String[] memberIds, int monthlyFee) {
            int enrolled = 0;
            int rejected = 0;

            for (String memberId : memberIds) {
                try {
                    new GymMember(memberId, monthlyFee);
                    enrolled++;
                } catch (IllegalArgumentException exception) {
                    rejected++;
                }
            }

            return "Enrolled: " + enrolled + " | Rejected: " + rejected;
        }
    }

    static class PremiumMember extends GymMember {
        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
        }
    }

    public static void main(String[] args) {
        PremiumMember premiumMember =
                new PremiumMember("MEM5", 2000, "Coach Riya");

        premiumMember.attendSession();
        premiumMember.attendSession();

        System.out.println("Sessions attended: "
                + premiumMember.getSessionsAttended());

        String[] memberIds = {
                "MEM1",
                "M1",
                "MEM2",
                " ",
                "MEM3"
        };

        System.out.println(GymMember.enrollBatch(memberIds, 1000));
    }
}