package 프로그래머스.level4.도둑질;

import java.util.Arrays;

public class Solution {

    public int solution(int[] money) {
        int n = money.length;
        int[] dpA = new int[n];
        int[] dpB = new int[n];
        dpA[0] = money[0];
        dpA[1] = money[0];
        dpB[0] = 0;
        dpB[1] = money[1];
        for (int i = 2; i < n - 1; i++) {
            dpA[i] = Math.max(dpA[i - 1], dpA[i - 2] + money[i]);
        }
        for (int i = 2; i < n; i++) {
            dpB[i] = Math.max(dpB[i - 1], dpB[i - 2] + money[i]);
        }
        if(n == 3) {
            return Arrays.stream(money)
                .max().getAsInt();
        }
        return Math.max(dpA[n-2], dpB[n-1]);
    }

    public static void main(String[] args) {
        int[] money = {4, 2, 3};
        int result = new Solution().solution(money);
        System.out.println(result);
    }
}
