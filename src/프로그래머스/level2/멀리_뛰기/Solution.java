package 프로그래머스.level2.멀리_뛰기;

public class Solution {

    public static void main(String[] args) {
        int n = 4;
        long result = new Solution().solution(n);
        System.out.println(result);
    }

    public long solution(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        if (n == 1) {
            return 1;
        }
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;
        }

        return dp[n];
    }
}
