package 프로그래머스.level3.거스름돈;

public class Solution {

    public static void main(String[] args) {
        int n = 5;
        int[] money = {1, 2, 5};
        int result = new Solution().solution(n, money);
        System.out.println(result);
    }

    public int solution(int n, int[] money) {
        int moneyLength = money.length;
        int[][] dp = new int[moneyLength+1][n+1];

        for(int i=0; i<=moneyLength; i++) {
            dp[i][0] = 1;
        }
        for(int i=1; i<=moneyLength; i++) {
            for(int j=1; j<=n; j++) {
                if(j < money[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = (dp[i][j - money[i-1]] + dp[i - 1][j])%1000000007;
                }
            }
        }

        return dp[moneyLength][n];
    }
}
