public class Problem02_GymMembershipHierarchy {
    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public void displayInfo() {
            System.out.println("Standard Member | Sessions: "
                    + sessionsAttended);
        }
    }

    static class PremiumMember extends GymMember {
        protected String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        public void displayInfo() {
            System.out.println("Premium Member | Trainer: "
                    + trainerName
                    + " | Sessions: "
                    + sessionsAttended);
        }
    }

    static class EliteMember extends PremiumMember {
        private int guestPasses;

        public EliteMember(
                String memberId,
                int monthlyFee,
                String trainerName,
                int guestPasses) {

            super(memberId, monthlyFee, trainerName);
            this.guestPasses = guestPasses;
        }

        @Override
        public void displayInfo() {
            System.out.println("Elite Member | Trainer: "
                    + trainerName
                    + " | Guest Passes: "
                    + guestPasses
                    + " | Sessions: "
                    + sessionsAttended);
        }
    }

    static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(
                String memberId,
                int monthlyFee,
                String className) {

            super(memberId, monthlyFee);
            this.className = className;
        }

        @Override
        public void displayInfo() {
            System.out.println("Group Class Member | Class: "
                    + className
                    + " | Sessions: "
                    + sessionsAttended);
        }
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {
            return "Direct premium subclass";
        }

        return "Base gym member";
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }

    public static void main(String[] args) {
        PremiumMember premium =
                new PremiumMember("MEM1", 2000, "Coach Riya");

        EliteMember elite =
                new EliteMember("MEM2", 3000, "Coach Sam", 2);

        GroupClassMember group =
                new GroupClassMember("MEM3", 1500, "Zumba");

        premium.attendSession();
        premium.attendSession();

        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();

        GymMember[] members = {premium, elite, group};

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));

        System.out.println("Total sessions attended: "
                + getTotalSessionsAttended(members));
    }
}