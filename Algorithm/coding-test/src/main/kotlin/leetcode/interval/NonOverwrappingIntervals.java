package leetcode.interval;

import java.util.Arrays;

/**
 * 435. Non-overlapping Intervals (https://leetcode.com/problems/non-overlapping-intervals/)
 */
public class NonOverwrappingIntervals {
    public static void main(String[] args) {
        System.out.println(new Solution61().eraseOverlapIntervals(new int[][] { {1, 100}, {11, 22}, {1, 11}, {2, 12} }));
        System.out.println(new Solution61().eraseOverlapIntervals(new int[][] { {0, 1}, {3, 4}, {1, 2}}));
    }
}


class Solution61 {
    public int eraseOverlapIntervals(int[][] intervals) {
        int overwapCount = 0;
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) return Integer.compare(b[0], a[0]);
            return Integer.compare(a[1], b[1]);
        });

        int last = -Integer.MAX_VALUE;

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] < last) {
                overwapCount++;
            } else {
                last = intervals[i][1];
            }
        }
        return overwapCount;
    }
}
