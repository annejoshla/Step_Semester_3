public class Problem04_MovieBookingProfile {
    static class MovieBookingProfile {
        private String name;
        private boolean confirmed;
        private String otp;

        public MovieBookingProfile() {
        }

        public MovieBookingProfile(String name) {
            this();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            if (otp != null
                    && otp.matches("\\d{4,6}")) {
                this.otp = otp;
            }
        }
    }

    public static void main(String[] args) {
        MovieBookingProfile profile =
                new MovieBookingProfile("Rahul Dev");

        profile.setConfirmed(true);
        profile.setOtp("4471");

        System.out.println("Name: " + profile.getName());
        System.out.println("Confirmed: " + profile.isConfirmed());
        System.out.println("OTP valid: " + (profile.getOtp() != null));
    }
}
