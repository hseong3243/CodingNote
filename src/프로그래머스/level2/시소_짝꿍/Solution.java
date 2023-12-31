package 프로그래머스.level2.시소_짝꿍;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[] weights = {100, 180, 360, 100, 270};
        long result = new Solution().solution(weights);
        System.out.println(result);
    }

    public long solution(int[] weights) {
        long result = 0;
        Arrays.sort(weights);

        Set<Integer> oneByOne = new HashSet<>();
        for (int weight : weights) {
            result += getTargetCount(weight * 4, weights, 3);
            result += getTargetCount(weight * 4, weights, 2);
            result += getTargetCount(weight * 3, weights, 2);
            int targetCount = getTargetCount(weight, weights, 1);
            if(oneByOne.add(weight)) {
                result += (long) targetCount * (targetCount - 1) / 2;
                oneByOne.add(weight);
            }
        }

        return result;
    }

    private int getTargetCount(int target, int[] weights, int n) {
        int left = findLeft(target, weights, n);
        int right = findRight(target, weights, n);
        return right - left + 1;
    }

    private int findRight(int target, int[] weights, int n) {
        int min = 0;
        int max = weights.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (weights[mid] * n > target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

    private int findLeft(int target, int[] weights, int n) {
        int min = 0;
        int max = weights.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (weights[mid] * n >= target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
