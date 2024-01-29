package 프로그래머스.level3.카운트_다운;

public class Solution {

    public static void main(String[] args) {
        int target = 62;
        int[] result = new Solution().solution(target);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] solution(int target) {
        int[][] dp = new int[100001][2];
        for (int i = 1; i <= 60; i++) {
            if (i <= 20) {
                dp[i][0] = 1;
                dp[i][1] = 1;
            } else if (i <= 40) {
                if (i % (i / 2) == 0 || i % (i / 3) == 0) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = 2;
                    dp[i][1] = 2;
                }
            } else if (i < 50) {
                if (i % (i / 3) == 0) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = 2;
                    dp[i][1] = 1;
                }
            } else if (i == 50) {
                dp[i][0] = 1;
                dp[i][1] = 1;
            } else {
                if (i % (i / 3) == 0) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = 2;
                    dp[i][1] = 2;
                }
            }
            System.out.println(i + ":" + dp[i][0] + " " + dp[i][1]);
        }
        for (int i = 61; i <= target; i++) {
            dp[i][0] = dp[i - 1][0] + dp[1][0];
            dp[i][1] = dp[i - 1][1] + dp[1][1];
            for(int j=1; j <= 60; j++) {
                if(dp[i-j][0] + dp[j][0] < dp[i][0]
                || (dp[i-j][0] + dp[j][0] == dp[i][0] && dp[i-j][1] + dp[j][1] > dp[i][1])) {
                    dp[i][0] = dp[i - j][0] + dp[j][0];
                    dp[i][1] = dp[i - j][1] + dp[j][1];
                }
            }
        }
        return dp[target];
    }
}
