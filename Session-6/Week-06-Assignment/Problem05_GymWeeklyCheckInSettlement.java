public class Problem05_GymWeeklyCheckInSettlement {
    static class GymMember {
        private static int membersEnrolled = 0;

        public final String membershipNumber;

        protected int monthlyFee;
        private int feesPaid;
        private String lastPaymentMode;

        public GymMember(int monthlyFee) {
            if (monthlyFee <= 0) {
                throw new IllegalArgumentException(
                        "Monthly fee must be positive");
            }

            membersEnrolled++;
            this.membershipNumber = "GYM-" + (2000 + membersEnrolled);
            this.monthlyFee = monthlyFee;
        }

        public void payFee(int amount) {
            if (amount > 0) {
                feesPaid += amount;
            }
        }

        public void payFee(int amount, String mode) {
            this.lastPaymentMode = mode;
            payFee(amount);
        }

        public int getFeesPaid() {
            return feesPaid;
        }

        public String getLastPaymentMode() {
            return lastPaymentMode;
        }

        public static boolean isValidReferralCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }

            return code.charAt(0) == 'G'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupMembers = 0;
        int individualMembers = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember) {
                groupMembers++;
            } else {
                individualMembers++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + groupMembers + " group | "
                + individualMembers + " individual";
    }

    public static void main(String[] args) {
        GymMember member = new GymMember(1000);

        System.out.println(member.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));

        member.payFee(500);
        member.payFee(500, "UPI");

        System.out.println(member.getFeesPaid());

        GymMember[] members = {
                new GroupClassMember(1500, "Zumba"),
                null,
                new GymMember(1000)
        };

        System.out.println(processWeeklyCheckIn(members));
    }
}