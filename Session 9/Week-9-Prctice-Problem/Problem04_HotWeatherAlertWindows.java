public class Problem04_HotWeatherAlertWindows {
    public static int countAlerts(
            int[] readings,
            int k,
            int threshold) {

        if (readings == null
                || k < 1
                || k > readings.length) {
            throw new IllegalArgumentException(
                    "k must be between 1 and the readings length.");
        }

        long windowSum = 0;

        for (int index = 0; index < k; index++) {
            windowSum += readings[index];
        }

        long requiredSum = (long) k * threshold;
        int alertCount = windowSum >= requiredSum ? 1 : 0;

        for (int right = k; right < readings.length; right++) {
            windowSum += readings[right];
            windowSum -= readings[right - k];

            if (windowSum >= requiredSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};

        System.out.println(countAlerts(readings, 3, 4));
    }
}