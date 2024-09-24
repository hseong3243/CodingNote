package 백준.gold.파일_합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test = 0; test < t; test++) {
            int k = Integer.parseInt(br.readLine());
            int[] sum = new int[k + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                sum[i] = Integer.parseInt(st.nextToken()) + sum[i - 1];
            }
            new Main().solution(k, sum);
        }
    }

    private void solution(int k, int[] sum) {
        int[][] dp = new int[k + 1][k + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        for (int i = 0; i <= k; i++) {
            dp[i][i] = 0;
        }
        for (int i = 1; i < k; i++) {
            for (int begin = 1; begin + i <= k; begin++) {
                int end = begin + i;
                for (int e = begin; e < end; e++) {
                    dp[begin][end] = Math.min(
                        dp[begin][end],
                        dp[begin][e] + dp[e + 1][end] + sum[end] - sum[begin-1]
                    );
                }
            }
        }

        System.out.println(dp[1][k]);
    }
}
