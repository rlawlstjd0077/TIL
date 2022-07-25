package leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    private static int MAX_VALUE = Integer.MAX_VALUE - 1;
    public int coinChange(int[] coins, int amount) {
        int result = recur(coins, amount, new int[amount + 1]);
        return result == MAX_VALUE ? -1 : result;
    }

    int recur(int[] coins, int amount, int[] amounts) {
        if (amount < 0) {
            return MAX_VALUE;
        }

        if (amount == 0) {
            return 0;
        }

        if (amounts[amount] > 0) {
            return amounts[amount];
        }

        int min = MAX_VALUE;

        for (int coin : coins) {
            int result = recur(coins, amount - coin, amounts) + 1;
            min = Math.min(min, result);
        }

        return amounts[amount] = min;
    }
}