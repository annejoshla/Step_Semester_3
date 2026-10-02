import java.util.LinkedHashMap;
import java.util.Map;

public class Problem03_OnlineExaminationSystem {
    static abstract class Question {
        private final String id;
        private final int points;

        protected Question(String id, int points) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Question ID cannot be blank.");
            }

            if (points <= 0) {
                throw new IllegalArgumentException(
                        "Points must be positive.");
            }

            this.id = id;
            this.points = points;
        }

        public String getId() {
            return id;
        }

        public int getPoints() {
            return points;
        }

        public abstract boolean evaluate(String answer);
    }

    static class MultipleChoiceQuestion extends Question {
        private final String correctOption;

        public MultipleChoiceQuestion(
                String id, int points, String correctOption) {
            super(id, points);
            this.correctOption = correctOption;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctOption.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalseQuestion extends Question {
        private final boolean correctAnswer;

        public TrueFalseQuestion(
                String id, int points, boolean correctAnswer) {
            super(id, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return Boolean.toString(correctAnswer)
                    .equalsIgnoreCase(answer);
        }
    }

    static class Examination {
        private final String name;
        private final Map<String, Question> questions =
                new LinkedHashMap<>();

        public Examination(String name) {
            this.name = name;
        }

        public void addQuestion(Question question) {
            questions.put(question.getId(), question);
        }

        public Question getQuestion(String id) {
            return questions.get(id);
        }

        public Map<String, Question> getQuestions() {
            return questions;
        }

        public String getName() {
            return name;
        }
    }

    static class Attempt {
        private final Examination examination;
        private final Map<String, String> answers =
                new LinkedHashMap<>();
        private boolean submitted;

        public Attempt(Examination examination) {
            this.examination = examination;
        }

        public void answer(String questionId, String answer) {
            if (submitted) {
                System.out.println(
                        "Cannot change answers for a submitted "
                                + "examination.");
                return;
            }

            if (!examination.getQuestions().containsKey(questionId)) {
                System.out.println("Question does not exist.");
                return;
            }

            answers.put(questionId, answer);
            System.out.println(
                    "Answer recorded for " + questionId + ".");
        }

        public void submit() {
            if (submitted) {
                return;
            }

            submitted = true;

            int score = 0;
            int total = 0;

            for (Question question :
                    examination.getQuestions().values()) {

                total += question.getPoints();

                String answer = answers.get(question.getId());
                boolean correct = answer != null
                        && question.evaluate(answer);

                if (correct) {
                    score += question.getPoints();
                }

                System.out.printf(
                        "Question %s: %s (%d points)%n",
                        question.getId(),
                        correct ? "Correct" : "Incorrect",
                        correct ? question.getPoints() : 0);
            }

            System.out.printf(
                    "Exam %s submitted. Total score: %d/%d%n",
                    examination.getName(),
                    score,
                    total);
        }
    }

    static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public Attempt startExam(Examination examination) {
            System.out.printf(
                    "Exam %s started by %s.%n",
                    examination.getName(),
                    name);

            return new Attempt(examination);
        }
    }

    public static void main(String[] args) {
        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion("Q1", 5, "C"));

        exam.addQuestion(
                new TrueFalseQuestion("Q2", 5, false));

        Student student = new Student("Student 1");
        Attempt attempt = student.startExam(exam);

        attempt.answer("Q1", "C");
        attempt.answer("Q2", "True");
        attempt.submit();
        attempt.answer("Q1", "A");
    }
}