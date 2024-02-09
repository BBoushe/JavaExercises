package Random.TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement))
                return new int[]{numMap.get(complement), i};
            numMap.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] testCase1 = {-3, 2, 3, -1, 3};
        System.out.println(Arrays.toString(twoSum(testCase1, 0)));
    }
}
