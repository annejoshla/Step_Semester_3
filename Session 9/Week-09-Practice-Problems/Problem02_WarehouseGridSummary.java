public class Problem02_WarehouseGridSummary {
    static class Summary {
        private final int totalItems;
        private final int maxRow;
        private final int maxColumn;

        public Summary(
                int totalItems,
                int maxRow,
                int maxColumn) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxColumn = maxColumn;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", ("
                    + maxRow + ", " + maxColumn + "))";
        }
    }

    public static Summary warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return new Summary(0, -1, -1);
        }

        int totalItems = 0;
        int maximum = -1;
        int maxRow = -1;
        int maxColumn = -1;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0;
                    column < grid[row].length;
                    column++) {

                int currentValue = grid[row][column];
                totalItems += currentValue;

                /*
                 * Use > instead of >= so the first maximum is kept
                 * when duplicate maximum values exist.
                 */
                if (currentValue > maximum) {
                    maximum = currentValue;
                    maxRow = row;
                    maxColumn = column;
                }
            }
        }

        return new Summary(
                totalItems,
                maxRow,
                maxColumn);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {4, 9, 2},
                {7, 1, 6},
                {3, 12, 5}
        };

        System.out.println(warehouseSummary(grid));
    }
}