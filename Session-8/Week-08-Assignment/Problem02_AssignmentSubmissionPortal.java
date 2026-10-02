import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Problem02_AssignmentSubmissionPortal {
    enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    static abstract class Assignment {
        private final String title;
        private final int maximumMarks;
        private final LocalDate dueDate;

        protected Assignment(
                String title,
                int maximumMarks,
                LocalDate dueDate) {
            this.title = title;
            this.maximumMarks = maximumMarks;
            this.dueDate = dueDate;
        }

        public String getTitle() {
            return title;
        }

        public int getMaximumMarks() {
            return maximumMarks;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public abstract double applyLatePenalty(
                double marks,
                long lateDays);
    }

    static class CodingAssignment extends Assignment {
        public CodingAssignment(
                String title,
                int maximumMarks,
                LocalDate dueDate) {
            super(title, maximumMarks, dueDate);
        }

        @Override
        public double applyLatePenalty(
                double marks,
                long lateDays) {
            double penalty = lateDays * 0.10;
            return Math.max(0, marks * (1 - penalty));
        }
    }

    static class WrittenAssignment extends Assignment {
        public WrittenAssignment(
                String title,
                int maximumMarks,
                LocalDate dueDate) {
            super(title, maximumMarks, dueDate);
        }

        @Override
        public double applyLatePenalty(
                double marks,
                long lateDays) {
            double penalty = lateDays * 0.20;
            return Math.max(0, marks * (1 - penalty));
        }
    }

    static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Submission {
        private final Student student;
        private final Assignment assignment;
        private final LocalDate submissionDate;
        private SubmissionStatus status =
                SubmissionStatus.SUBMITTED;
        private double finalMarks;

        public Submission(
                Student student,
                Assignment assignment,
                LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;

            long lateDays = calculateLateDays();

            System.out.printf(
                    "%s's submission for '%s' received "
                            + "(%s). Status: Submitted.%n",
                    student.getName(),
                    assignment.getTitle(),
                    lateDays == 0
                            ? "on time"
                            : lateDays + " days late");
        }

        private long calculateLateDays() {
            if (!submissionDate.isAfter(
                    assignment.getDueDate())) {
                return 0;
            }

            return ChronoUnit.DAYS.between(
                    assignment.getDueDate(),
                    submissionDate);
        }

        public void grade(double awardedMarks) {
            if (status == SubmissionStatus.GRADED) {
                System.out.println(
                        "Submission has already been graded.");
                return;
            }

            if (awardedMarks < 0
                    || awardedMarks
                    > assignment.getMaximumMarks()) {
                throw new IllegalArgumentException(
                        "Marks are outside the valid range.");
            }

            long lateDays = calculateLateDays();

            finalMarks = assignment.applyLatePenalty(
                    awardedMarks,
                    lateDays);

            status = SubmissionStatus.GRADED;

            System.out.printf(
                    "%s graded: %.0f/%d. Status: Graded.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaximumMarks());
        }

        public void resubmit() {
            if (status == SubmissionStatus.GRADED) {
                System.out.printf(
                        "Cannot resubmit: '%s' has already "
                                + "been graded.%n",
                        assignment.getTitle());
            }
        }
    }

    public static void main(String[] args) {
        Assignment coding = new CodingAssignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSubmission = new Submission(
                asha,
                coding,
                LocalDate.of(2026, 3, 10));

        Submission raviSubmission = new Submission(
                ravi,
                written,
                LocalDate.of(2026, 3, 14));

        ashaSubmission.grade(45);
        raviSubmission.grade(40);
        ashaSubmission.resubmit();
    }
}