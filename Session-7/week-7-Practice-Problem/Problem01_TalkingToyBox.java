public class Problem01_TalkingToyBox {
    static abstract class Toy {
        private static int nextId = 1000;
        private final String toyId;
        protected final String name;

        protected Toy(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Toy name cannot be blank.");
            }

            this.name = name;
            nextId++;
            this.toyId = "TOY-" + nextId;
        }

        public abstract String makeSound();

        public String getToyId() {
            return toyId;
        }
    }

    static class ToyCar extends Toy {
        public ToyCar(String name) {
            super(name);
        }

        @Override
        public String makeSound() {
            return name + ": Vroom vroom!";
        }
    }

    static class ToyRobot extends Toy {
        public ToyRobot(String name) {
            super(name);
        }

        @Override
        public String makeSound() {
            return name + ": Beep boop!";
        }
    }

    public static void main(String[] args) {
        ToyCar car = new ToyCar("Speedster");
        ToyRobot robot = new ToyRobot("Bolt");

        System.out.println(car.makeSound());
        System.out.println(robot.makeSound());
        System.out.println(car.getToyId());
        System.out.println(robot.getToyId());
    }
}