package leetcode.interval;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertIntervalTest {

    @Test
    void test() {
        assertArrayEquals(new InsertInterval().insert(new int[][]{{1, 5}}, new int[]{2, 3}), new int[][]{{1, 5}});
        assertArrayEquals(new InsertInterval().insert(new int[][]{{0, 2}, {3, 9}}, new int[] { 6, 8 }), new int[][]{{0, 2}, {3, 9}});
        assertArrayEquals(new InsertInterval().insert(new int[][]{{1, 5}}, new int[]{0, 0}), new int[][]{{0, 0}, {1, 5}});
        assertArrayEquals(new InsertInterval().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}), new int[][]{{1, 5}, {6, 9}});
    }
}