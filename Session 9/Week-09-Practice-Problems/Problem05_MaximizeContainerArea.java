public class Problem05_MaximizeContainerArea {
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;
        int maximumArea = 0;

        while (left < right) {
            int width = right - left;
            int shorterHeight = Math.min(
                    heights[left],
                    heights[right]);

            int currentArea = shorterHeight * width;
            maximumArea = Math.max(
                    maximumArea,
                    currentArea);

            /*
             * Moving the taller wall cannot increase the area
             * because the shorter wall limits the height.
             */
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maximumArea;
    }

    public static void main(String[] args) {
        int[] heights = {
                1, 8, 6, 2, 5, 4, 8, 3, 7
        };

        System.out.println(
                maxContainerArea(heights));
    }
}