public class Problem04_MonthlyAttendanceAnnouncer {
    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium | Trainer: " + trainerName
                    + " | Sessions: " + sessionsAttended;
        }
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();

        for (GymMember member : members) {
            announcement.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;

                announcement.append(" [Trainer via downcast: ")
                        .append(premiumMember.getTrainerName())
                        .append("]");
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}