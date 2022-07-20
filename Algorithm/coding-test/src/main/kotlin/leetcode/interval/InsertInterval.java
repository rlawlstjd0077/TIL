package leetcode.interval;


import java.util.Arrays;

/**
 * 57. Insert Interval (https://leetcode.com/problems/insert-interval/)
 */
public class InsertInterval {


    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][] { newInterval };
        }

        int[][] result = new int[intervals.length + 1][2];
        for (int[] e : result) {
            Arrays.fill(e, -1);
        }

        if (!isContains(new int[] { intervals[0][0], intervals[intervals.length - 1][1] }, newInterval)) {
            if (intervals[0][0] > newInterval[1]) {
                System.arraycopy(intervals, 0, result, 1, intervals.length);
                result[0] = newInterval;
            } else {
                System.arraycopy(intervals, 0, result, 0, intervals.length);
                result[intervals.length] = newInterval;
            }
            return result;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int index = 0;


        for (int i = 0; i < intervals.length; i++) {
            if (isContains(newInterval, intervals[i])) {
                int currentMin = Math.min(intervals[i][0], newInterval[0]);
                int currentMax = Math.max(intervals[i][1], newInterval[1]);

                if (min > currentMin) {
                    min = currentMin;
                }

                if (max < currentMax) {
                    max = currentMax;
                }

                if ((i + 1) == intervals.length || !isContains(newInterval, intervals[i + 1])) {
                    result[index] = new int[]{min, max};
                    index++;
                    continue;
                }

            } else {
                result[index] = intervals[i];
                index++;
            }

            if (intervals[i][1] < newInterval[0] && intervals[i + 1][1] > newInterval[1]) {
                result[index] = newInterval;
                index++;
            }
        }

        return Arrays.stream(result)
                .filter(i -> i[0] != -1)
                .toArray(int[][]::new);
    }

    private boolean isContains(int[] sourceInterval, int[] targetInterval) {
        return (targetInterval[0] >= sourceInterval[0] && targetInterval[0] <= sourceInterval[1]) ||
                (targetInterval[1] >= sourceInterval[0] && targetInterval[1] <= sourceInterval[1]) ||
                (sourceInterval[0] >= targetInterval[0] && sourceInterval[0] <= targetInterval[1]) ||
                (sourceInterval[1] >= targetInterval[0] && sourceInterval[1] <= targetInterval[1]);
    }
}
