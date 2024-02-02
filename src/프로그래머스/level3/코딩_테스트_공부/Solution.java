package 프로그래머스.level3.코딩_테스트_공부;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
//        int alp = 10;
//        int cop = 10;
//        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
//        int alp = 0;
//        int cop = 0;
//        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};
        int alp = 10;
        int cop = 1;
        int[][] problems = {{1, 1, 1, 1, 1}, {5, 5, 1, 1, 3}};
        int result = new Solution().solution(alp, cop, problems);
        System.out.println(result);
    }

    private int clearAlp = 0;
    private int clearCop = 0;

    public int solution(int alp, int cop, int[][] problems) {
        for (int[] problem : problems) {
            clearAlp = Math.max(clearAlp, problem[0]);
            clearCop = Math.max(clearCop, problem[1]);
        }
        int[][] dp = new int[clearAlp + 2][clearCop + 2];
        int startAlp = Math.min(alp, clearAlp);
        int startCop = Math.min(cop, clearCop);
        for (int i = startAlp; i < clearAlp+2; i++) {
            for (int j = startCop; j < clearCop+2; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[startAlp][startCop] = 0;

        for (int i = startAlp; i <= clearAlp; i++) {
            for (int j = startCop; j <= clearCop; j++) {
                dp[i + 1][j] = Math.min(dp[i][j] + 1, dp[i + 1][j]);
                dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j + 1]);
                for (int[] problem : problems) {
                    int reqAlp = problem[0];
                    int reqCop = problem[1];
                    int upAlp = problem[2];
                    int upCop = problem[3];
                    int cost = problem[4];
                    if (i >= reqAlp && j >= reqCop) {
                        if(i+upAlp > clearAlp && j+upCop > clearCop) {
                            dp[clearAlp][clearCop] = Math.min(dp[i][j] + cost,
                                dp[clearAlp][clearCop]);
                        } else if(i+upAlp > clearAlp) {
                            dp[clearAlp][j + upCop] = Math.min(dp[i][j] + cost,
                                dp[clearAlp][j + upCop]);
                        } else if(j+upCop > clearCop) {
                            dp[i + upAlp][clearCop] = Math.min(dp[i][j] + cost,
                                dp[i + upAlp][clearCop]);
                        } else {
                            dp[i + upAlp][j + upCop] = Math.min(dp[i][j] + cost,
                                dp[i + upAlp][j + upCop]);
                        }
                    }
                }
            }
        }
        return dp[clearAlp][clearCop];
    }
}
