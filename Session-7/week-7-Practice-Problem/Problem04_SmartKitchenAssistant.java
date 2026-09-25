public class Problem04_SmartKitchenAssistant {
    static abstract class KitchenTool {
        private int speedLevel = 1;

        public abstract String prepare();

        public int getSpeedLevel() {
            return speedLevel;
        }

        public void setSpeedLevel(int speedLevel) {
            if (speedLevel >= 1 && speedLevel <= 5) {
                this.speedLevel = speedLevel;
            }
        }
    }

    interface Washable {
        String clean();
    }

    static class Blender extends KitchenTool
            implements Washable {

        @Override
        public String prepare() {
            return "Blending at speed "
                    + getSpeedLevel();
        }

        @Override
        public String clean() {
            return "Blender rinsed and dried";
        }
    }

    public static void main(String[] args) {
        Blender blender = new Blender();

        blender.setSpeedLevel(3);
        System.out.println(blender.getSpeedLevel());

        blender.setSpeedLevel(9);
        System.out.println(blender.getSpeedLevel());

        System.out.println(blender.prepare());
        System.out.println(blender.clean());
    }
}