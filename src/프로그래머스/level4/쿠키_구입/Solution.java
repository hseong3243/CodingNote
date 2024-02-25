package 프로그래머스.level4.쿠키_구입;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] cookie = {1, 2, 4, 5};
        int result = new Solution().solution(cookie);
        System.out.println(result);
    }

    public int solution(int[] cookie) {
        int length = cookie.length;
        int[][] sumArr = new int[length][length];
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += cookie[j];
                sumArr[i][j] = sum;
            }
        }
        int result = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (sumArr[i][j] < result * 2) {
                    continue;
                }
                if(sumArr[i][j] % 2 != 0) {
                    continue;
                }
                for (int k = i; k < j; k++) {
                    if (sumArr[i][k] == sumArr[k + 1][j]) {
                        result = Math.max(result, sumArr[i][k]);
                    }
                }
            }
        }

        return result;
    }
}
