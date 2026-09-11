public class Problem04_LibraryMemberJavaBean {
    static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswerHash;

        public LibraryMember() {
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {
            if (membershipId == null) {
                membershipId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premium) {
            this.premiumMember = premium;
        }

        public void setSecurityAnswer(String answer) {
            if (answer != null) {
                securityAnswerHash = Integer.toHexString(
                        answer.hashCode());
            }
        }

        public String getSecurityAnswerHash() {
            return securityAnswerHash;
        }
    }

    public static void main(String[] args) {
        LibraryMember member = new LibraryMember();

        member.setMembershipId("LIB-8841");
        member.setName("Priya Nair");
        member.setPremiumMember(true);
        member.setSecurityAnswer("BlueMountain");

        member.setMembershipId("FAKE-0000");

        System.out.println("Membership ID: "
                + member.getMembershipId());

        System.out.println("Name: " + member.getName());
        System.out.println("Premium member: "
                + member.isPremiumMember());
        System.out.println("Security answer hash: "
                + member.getSecurityAnswerHash());
        System.out.println("Security answer stored securely.");
    }
}