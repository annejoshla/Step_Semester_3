import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem05_CampusNoticeBroadcaster {
    interface NotificationChannel {
        String getName();

        void send(Student student, Notice notice);
    }

    static class EmailChannel implements NotificationChannel {
        public String getName() {
            return "Email";
        }

        public void send(Student student, Notice notice) {
            System.out.printf(
                    "[Email → %s] %s%n",
                    student.getName(),
                    notice.getTitle());
        }
    }

    static class SmsChannel implements NotificationChannel {
        public String getName() {
            return "SMS";
        }

        public void send(Student student, Notice notice) {
            System.out.printf(
                    "[SMS → %s] %s%n",
                    student.getName(),
                    notice.getTitle());
        }
    }

    static class AppChannel implements NotificationChannel {
        public String getName() {
            return "App";
        }

        public void send(Student student, Notice notice) {
            System.out.printf(
                    "[App → %s] %s%n",
                    student.getName(),
                    notice.getTitle());
        }
    }

    static class Student {
        private final String name;
        private final String department;
        private final List<NotificationChannel> channels =
                new ArrayList<>();

        public Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public void addChannel(NotificationChannel channel) {
            channels.add(channel);
        }

        public List<NotificationChannel> getChannels() {
            return channels;
        }
    }

    static class Notice {
        private final String title;
        private final Set<String> departments;

        public Notice(
                String title,
                Set<String> departments) {

            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Notice title cannot be blank.");
            }

            if (departments == null || departments.isEmpty()) {
                throw new IllegalArgumentException(
                        "At least one target department is required.");
            }

            this.title = title;
            this.departments = new HashSet<>(departments);
        }

        public String getTitle() {
            return title;
        }

        public boolean targets(String department) {
            return departments.contains(department);
        }
    }

    static class NoticeBoard {
        private final List<Student> students =
                new ArrayList<>();

        public void addStudent(Student student) {
            students.add(student);
        }

        public void post(Notice notice) {
            System.out.printf(
                    "Notice '%s' posted.%n",
                    notice.getTitle());

            for (Student student : students) {
                if (notice.targets(student.getDepartment())) {
                    for (NotificationChannel channel :
                            student.getChannels()) {
                        channel.send(student, notice);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.post(new Notice(
                "Lab Closed Tomorrow",
                Set.of("CSE")));

        board.post(new Notice(
                "Fee Deadline Extended",
                Set.of("CSE", "ECE")));

        try {
            board.post(new Notice(
                    "Sports Day",
                    Set.of()));
        } catch (IllegalArgumentException exception) {
            System.out.println(
                    "Cannot post notice: "
                            + exception.getMessage());
        }
    }
}