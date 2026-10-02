public class Problem01_HostelLaundryQueue {
    interface WashType {
        String getName();

        int getDuration();

        double getCharge();
    }

    static class QuickWash implements WashType {
        public String getName() {
            return "Quick";
        }

        public int getDuration() {
            return 30;
        }

        public double getCharge() {
            return 20.0;
        }
    }

    static class NormalWash implements WashType {
        public String getName() {
            return "Normal";
        }

        public int getDuration() {
            return 45;
        }

        public double getCharge() {
            return 30.0;
        }
    }

    static class HeavyWash implements WashType {
        public String getName() {
            return "Heavy";
        }

        public int getDuration() {
            return 60;
        }

        public double getCharge() {
            return 45.0;
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

    static class WashCycle {
        private final Student student;
        private final WashingMachine machine;
        private final WashType washType;
        private boolean completed;

        public WashCycle(
                Student student,
                WashingMachine machine,
                WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public Student getStudent() {
            return student;
        }

        public void complete() {
            if (!completed) {
                completed = true;
                machine.completeCycle();

                System.out.printf(
                        "%s cycle completed for %s. "
                                + "%s is now free.%n",
                        washType.getName(),
                        student.getName(),
                        machine.getId());
            }
        }
    }

    static class WashingMachine {
        private final String id;
        private WashCycle activeCycle;

        public WashingMachine(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public boolean isFree() {
            return activeCycle == null;
        }

        public WashCycle startWash(
                Student student,
                WashType washType) {

            if (!isFree()) {
                System.out.printf(
                        "Machine %s is currently busy.%n",
                        id);
                return null;
            }

            WashCycle cycle = new WashCycle(
                    student,
                    this,
                    washType);

            activeCycle = cycle;

            System.out.printf(
                    "%s wash started on %s for %s "
                            + "(%d min). Charge: ₹%.2f%n",
                    washType.getName(),
                    id,
                    student.getName(),
                    washType.getDuration(),
                    washType.getCharge());

            return cycle;
        }

        private void completeCycle() {
            activeCycle = null;
        }
    }

    public static void main(String[] args) {
        WashingMachine machine1 = new WashingMachine("M1");
        WashingMachine machine2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashCycle cycle1 = machine1.startWash(
                asha,
                new QuickWash());

        machine1.startWash(ravi, new HeavyWash());

        machine2.startWash(ravi, new HeavyWash());

        if (cycle1 != null) {
            cycle1.complete();
        }

        machine1.startWash(neha, new NormalWash());
    }
}