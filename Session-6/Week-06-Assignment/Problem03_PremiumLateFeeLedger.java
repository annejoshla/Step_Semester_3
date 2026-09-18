import java.util.Arrays;

public class Problem03_PremiumLateFeeLedger {
    static class GymMember {
        protected String memberId;
        protected int monthlyFee;

        private final int[] lateFeeHistory = new int[10];
        private int lateFeeCount = 0;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty()) {
                throw new IllegalArgumentException("Member ID cannot be blank");
            }

            if (monthlyFee <= 0) {
                throw new IllegalArgumentException("Monthly fee must be positive");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount] = amount;
                lateFeeCount++;
            }
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, lateFeeCount);
        }

        public int getTotalLateFees() {
            int total = 0;

            for (int i = 0; i < lateFeeCount; i++) {
                total += lateFeeHistory[i];
            }

            return total;
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
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember premiumMember =
                new PremiumMember("MEM5", 2000, "Coach Riya");

        premiumMember.chargeLateFee(200);

        System.out.println(premiumMember.getTotalLateFees());

        int[] history = premiumMember.getLateFeeHistory();
        history[0] = 999;

        System.out.println(Arrays.toString(
                premiumMember.getLateFeeHistory()));
    }
}