public class Problem04_FitZoneMembershipDesk {
    interface MembershipPlan {
        String getName();

        int getMonths();

        double calculateFee();
    }

    static class MonthlyPlan implements MembershipPlan {
        public String getName() {
            return "Monthly";
        }

        public int getMonths() {
            return 1;
        }

        public double calculateFee() {
            return 1000.0;
        }
    }

    static class QuarterlyPlan implements MembershipPlan {
        public String getName() {
            return "Quarterly";
        }

        public int getMonths() {
            return 3;
        }

        public double calculateFee() {
            return 1000.0 * 3 * 0.90;
        }
    }

    static class AnnualPlan implements MembershipPlan {
        public String getName() {
            return "Annual";
        }

        public int getMonths() {
            return 12;
        }

        public double calculateFee() {
            return 1000.0 * 12 * 0.75;
        }
    }

    enum MembershipStatus {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    static class Member {
        private final String name;
        private final Membership membership;

        public Member(String name, MembershipPlan plan) {
            this.name = name;
            this.membership = new Membership(this, plan);

            System.out.printf(
                    "%s membership created for %s. "
                            + "Fee: ₹%.2f. Status: %s%n",
                    plan.getName(),
                    name,
                    plan.calculateFee(),
                    membership.getStatus());
        }

        public String getName() {
            return name;
        }

        public Membership getMembership() {
            return membership;
        }
    }

    static class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private MembershipStatus status =
                MembershipStatus.ACTIVE;

        public Membership(
                Member member,
                MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
        }

        public MembershipPlan getPlan() {
            return plan;
        }

        public MembershipStatus getStatus() {
            return status;
        }

        public void checkIn() {
            if (status == MembershipStatus.ACTIVE) {
                System.out.printf(
                        "%s checked in successfully with %s plan.%n",
                        member.getName(),
                        plan.getName());
            } else {
                System.out.printf(
                        "Check-in denied: %s's membership is %s.%n",
                        member.getName(),
                        status);
            }
        }

        public void freeze() {
            if (status == MembershipStatus.ACTIVE) {
                status = MembershipStatus.FROZEN;

                System.out.printf(
                        "%s's %s membership frozen. Status: %s%n",
                        member.getName(),
                        plan.getName(),
                        status);
            } else {
                System.out.printf(
                        "Cannot freeze a %s membership.%n",
                        status);
            }
        }

        public void unfreeze() {
            if (status == MembershipStatus.FROZEN) {
                status = MembershipStatus.ACTIVE;

                System.out.printf(
                        "%s's %s membership reactivated. Status: %s%n",
                        member.getName(),
                        plan.getName(),
                        status);
            } else {
                System.out.printf(
                        "Cannot unfreeze a %s membership.%n",
                        status);
            }
        }

        public void expire() {
            if (status != MembershipStatus.EXPIRED) {
                status = MembershipStatus.EXPIRED;

                System.out.printf(
                        "%s's %s membership expired. Status: %s%n",
                        member.getName(),
                        plan.getName(),
                        status);
            }
        }
    }

    public static void main(String[] args) {
        Member asha = new Member(
                "Asha",
                new QuarterlyPlan());

        Member ravi = new Member(
                "Ravi",
                new MonthlyPlan());

        asha.getMembership().checkIn();
        asha.getMembership().freeze();
        asha.getMembership().checkIn();

        ravi.getMembership().expire();
        ravi.getMembership().freeze();
    }
}