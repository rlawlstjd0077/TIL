package leetcode.interval;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonOverwrappingIntervalsTest {
    @Test
    void test() {
        assertEquals(new NonOverwrappingIntervals().eraseOverlapIntervals(new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}}), 2);
        assertEquals(new NonOverwrappingIntervals().eraseOverlapIntervals(new int[][]{{0, 1}, {3, 4}, {1, 2}}), 0);
    }
}