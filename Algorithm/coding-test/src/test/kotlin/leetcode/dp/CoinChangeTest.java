package leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {
    @Test
    void test() {
        assertEquals(new CoinChange().coinChange(new int[]{1, 2, 5}, 11), 3);
        assertEquals(new CoinChange().coinChange(new int[]{2}, 3), -1);
    }
}