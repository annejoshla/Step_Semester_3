public class Problem03_LateFeeCalculator {
    static class FeeAccount {
        String registrationNumber;
        double totalFee;

        public FeeAccount(String registrationNumber, double totalFee) {
            this.registrationNumber = registrationNumber;
            this.totalFee = totalFee;
        }

        public final double calculateLateFee(int daysLate) {
            return daysLate * 0.01 * totalFee;
        }

        public final void printSummary(int daysLate) {
            double lateFee = calculateLateFee(daysLate);

            System.out.println(registrationNumber
                    + " | Total Fee: Rs " + totalFee
                    + " | Late Fee: Rs " + lateFee);
        }
    }

    public static void main(String[] args) {
        String[] registrationNumbers = {
                "RA001",
                "RA002",
                "RA003",
                "RA004"
        };

        double[] totalFees = {
                200000,
                150000,
                180000,
                220000
        };

        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < registrationNumbers.length; i++) {
            FeeAccount account = new FeeAccount(
                    registrationNumbers[i],
                    totalFees[i]);

            if (daysLate[i] > 0) {
                account.printSummary(daysLate[i]);
            } else {
                System.out.println(registrationNumbers[i]
                        + " - On time, no late fee");
            }
        }
    }
}