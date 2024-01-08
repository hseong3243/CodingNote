package 프로그래머스.level3.보행자_천국;

public class Solution {

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
//        int[][] cityMap = {{0,0,0},{0,0,0},{0,0,0}};
        int result = new Solution().solution(m, n, cityMap);
        System.out.println(result);
    }

    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        long[][][] dp = new long[m + 1][n + 1][2];
        for(int i=1; i<=m; i++) {
            if(cityMap[i-1][0] == 1) {
                break;
            }
            dp[i][1][0] = 1;
        }
        for(int j=1; j<=n; j++) {
            if(cityMap[0][j-1] == 1) {
                break;
            }
            dp[1][j][1] = 1;
        }
        for(int i=2; i<=m; i++) {
            for(int j=2; j<=n; j++) {
                if(cityMap[i-1][j-1] == 1) {
                    continue;
                }
                if(cityMap[i-2][j-1] == 0) {
                    dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
                } else if(cityMap[i-2][j-1] == 2) {
                    dp[i][j][0] = dp[i-1][j][0];
                }
                if(cityMap[i-1][j-2] == 0) {
                    dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
                } else if(cityMap[i-1][j-2] == 2) {
                    dp[i][j][1] = dp[i][j-1][1];
                }
            }
        }

        return (int) ((dp[m][n][0] + dp[m][n][1]) % MOD);
    }
}
