public class Problem05_AccountBatchPayments {
    static class FeeAccount {
        public void pay(double amount) {
            System.out.println("Paid in one go (day-scholar account)");
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        public void payInInstallments(double amount) {
            System.out.println("Paid in two installments (hostel account)");
        }
    }

    public static void processPayment(
            FeeAccount account,
            double amount) {

        if (account instanceof HostelFeeAccount) {
            HostelFeeAccount hostelAccount =
                    (HostelFeeAccount) account;

            hostelAccount.payInInstallments(amount);
        } else {
            account.pay(amount);
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new FeeAccount(),
                new FeeAccount()
        };

        double amount = 60000;
        int hostelAccountsProcessed = 0;
        int dayScholarAccountsProcessed = 0;

        for (FeeAccount account : accounts) {
            processPayment(account, amount);

            if (account instanceof HostelFeeAccount) {
                hostelAccountsProcessed++;
            } else {
                dayScholarAccountsProcessed++;
            }
        }

        System.out.println("Hostel accounts processed: "
                + hostelAccountsProcessed
                + " | Day-scholar accounts processed: "
                + dayScholarAccountsProcessed);
    }
}