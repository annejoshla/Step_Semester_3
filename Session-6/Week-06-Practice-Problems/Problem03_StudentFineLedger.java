import java.util.Arrays;

public class Problem03_StudentFineLedger {
    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        private int[] fineHistory = new int[10];
        private int fineCount;

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

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        protected void chargeFine(int amount) {
            if (fineCount < fineHistory.length) {
                fineHistory[fineCount] = amount;
                fineCount++;
            }
        }

        public int[] getFineHistory() {
            return Arrays.copyOf(fineHistory, fineCount);
        }

        public int getTotalFine() {
            int total = 0;

            for (int i = 0; i < fineCount; i++) {
                total += fineHistory[i];
            }

            return total;
        }
    }

    static class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);

            if (course == null || course.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Course cannot be blank");
            }

            this.course = course.trim();
        }

        public String getCourse() {
            return course;
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {
        StudentMember student =
                new StudentMember("STU5", 3, "CSE");

        System.out.println("Course: " + student.getCourse());

        student.chargeFine(100);

        System.out.println("Total fine: "
                + student.getTotalFine());

        int[] history = student.getFineHistory();
        history[0] = 999;

        System.out.println("Fine history: "
                + Arrays.toString(student.getFineHistory()));
    }
}