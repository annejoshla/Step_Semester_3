public class Problem04_WeeklyCirculationReport {
    static class LibraryMember {
        protected int booksBorrowed;

        public LibraryMember(
                String memberId,
                int borrowLimit) {

            if (memberId == null
                    || memberId.trim().length() < 4) {
                throw new IllegalArgumentException(
                        "Invalid member ID");
            }

            if (borrowLimit <= 0) {
                throw new IllegalArgumentException(
                        "Borrow limit must be positive");
            }
        }

        public void displayInfo() {
            System.out.print("General | Books: "
                    + booksBorrowed);
        }
    }

    static class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }

        @Override
        public void displayInfo() {
            System.out.print("Student | Course: "
                    + course
                    + " | Books: "
                    + booksBorrowed);
        }
    }

    public static String batchPrint(
            LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            member.displayInfo();

            if (member instanceof StudentMember) {
                StudentMember student =
                        (StudentMember) member;

                report.append("Student | Course: ")
                        .append(student.getCourse())
                        .append(" | Books: ")
                        .append(student.booksBorrowed)
                        .append(" [Course via downcast: ")
                        .append(student.getCourse())
                        .append("] | ");
            } else {
                report.append("General | Books: ")
                        .append(member.booksBorrowed)
                        .append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
                new LibraryMember("LB5", 3),
                new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}