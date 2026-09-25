public class Problem01_MorningWakeUpCircuit {
    interface Ringable {
        String ring();
    }

    static class AlarmClock implements Ringable {
        private final String time;

        public AlarmClock(String time) {
            if (time == null || time.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Time cannot be blank.");
            }

            this.time = time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + time;
        }
    }

    static class Doorbell implements Ringable {
        private final String location;

        public Doorbell(String location) {
            if (location == null || location.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Location cannot be blank.");
            }

            this.location = location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + location;
        }
    }

    public static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }

    public static void main(String[] args) {
        AlarmClock alarmClock = new AlarmClock("7:00 AM");
        Doorbell doorbell = new Doorbell("Front Door");

        ringAll(new Ringable[]{alarmClock, doorbell});
    }
}