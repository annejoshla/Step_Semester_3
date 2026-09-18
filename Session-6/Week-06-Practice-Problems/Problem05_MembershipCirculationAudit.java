public class Problem05_MembershipCirculationAudit {
    static class LibraryMember {
        private static int membersEnrolled;
        protected int borrowLimit;
        protected int booksBorrowed;
        protected final String memberNumber;

        public LibraryMember(int borrowLimit) {
            if (borrowLimit <= 0) {
                throw new IllegalArgumentException(
                        "Borrow limit must be positive");
            }

            membersEnrolled++;
            memberNumber = "LIB-"
                    + (100 + membersEnrolled);
            this.borrowLimit = borrowLimit;
        }

        public void borrowBook() {
            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public void borrowBook(String genre) {
            borrowBook();
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public static boolean isValidRenewalCode(
                String code) {

            if (code == null || code.length() != 4) {
                return false;
            }

            return code.charAt(0) == 'R'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    static class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(
                int borrowLimit,
                String department) {

            super(borrowLimit);
            if (department == null || department.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Department cannot be blank");
            }
            this.department = department.trim();
        }

        public String getDepartment() {
            return department;
        }
    }

    public static String processNightlyAudit(
            LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember member = new LibraryMember(3);

        member.borrowBook();
        member.borrowBook("Fiction");

        System.out.println("Member number: "
                + member.memberNumber);

        System.out.println("Books borrowed: "
                + member.getBooksBorrowed());

        System.out.println(
                LibraryMember.isValidRenewalCode("R12A"));

        System.out.println(
                LibraryMember.isValidRenewalCode("R1A"));

        LibraryMember[] members = {
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        };

        System.out.println(
                processNightlyAudit(members));
    }
}