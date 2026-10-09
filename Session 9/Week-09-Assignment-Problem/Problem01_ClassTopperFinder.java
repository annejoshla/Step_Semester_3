public class Problem01_ClassTopperFinder {
    static class TopperResult {
        private final int rowIndex;
        private final int total;

        public TopperResult(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    public static TopperResult findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException(
                    "At least one student's marks are required.");
        }

        int bestRow = 0;
        int bestTotal = -1;

        for (int row = 0; row < marks.length; row++) {
            int total = 0;

            for (int mark : marks[row]) {
                total += mark;
            }

            // Strictly greater keeps the smallest row index on ties.
            if (total > bestTotal) {
                bestTotal = total;
                bestRow = row;
            }
        }

        return new TopperResult(bestRow, bestTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
                {78, 85, 90},
                {88, 92, 79},
                {65, 70, 95}
        };

        System.out.println(findTopper(marks));
    }
}