package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 377. Combination Sum IV (https://leetcode.com/problems/combination-sum-iv/)
 */
public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(new Solution42().combinationSum4(new int[]{1, 2, 3}, 4));
    }
}

class Solution42 {
    private Map<Integer, Integer> cache = new HashMap();

    public int combinationSum4(int[] nums, int target) {
        int[] arr = new int[target + 1];


        arr[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    arr[i] += arr[i - nums[j]];
                    System.out.println(i + ", " + j + " - " + arr[i]);
                }
            }
        }
        return arr[target];
    }
}
