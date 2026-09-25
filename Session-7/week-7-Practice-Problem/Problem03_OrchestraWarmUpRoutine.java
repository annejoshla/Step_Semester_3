public class Problem03_OrchestraWarmUpRoutine {
    static abstract class Instrument {
        public abstract String play();
    }

    static class StringInstrument extends Instrument {
        public StringInstrument() {
            super();
        }

        @Override
        public String play() {
            return "Strumming the strings";
        }
    }

    static class Violin extends StringInstrument {
        public Violin() {
            super();
        }

        @Override
        public String play() {
            String baseMessage = super.play();

            return baseMessage
                    + ", with a bow drawn across four strings";
        }
    }

    public static void main(String[] args) {
        StringInstrument stringInstrument = new StringInstrument();
        Violin violin = new Violin();

        System.out.println(stringInstrument.play());
        System.out.println(violin.play());
    }
}