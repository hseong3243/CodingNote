package 프로그래머스.level3.스티커_모으기_2;

public class Solution {

    public int solution(int[] sticker) {
        int n = sticker.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        if(n == 1) {
            return sticker[0];
        }
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }
        dp2[0] = 0;
        dp2[1] = sticker[1];
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        return Math.max(dp1[n - 2], dp2[n - 1]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] sticker = new int[]{14, 6, 5, 11, 3, 9, 2, 10};
        int result = solution.solution(sticker);
        System.out.println(result);
    }
}
