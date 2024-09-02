package 백준.silver.스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            for (int i = 0; i < 2; i++) {
                String[] split = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(split[j]);
                }
            }
            new Main().solution(n, arr);
        }
    }

    private void solution(int n, int[][] arr) {
        int[][] dp = new int[2][n];
        dp[0][0] = arr[0][0];
        dp[1][0] = arr[1][0];
        int result = Math.max(dp[0][0], dp[1][0]);
        if(n > 1) {
            dp[0][1] = arr[0][1] + arr[1][0];
            dp[1][1] = arr[1][1] + arr[0][0];
            int max = Math.max(dp[0][1], dp[1][1]);
            result = Math.max(result, max);
        }
        for(int i = 2; i < n; i++) {
            dp[0][i] = Math.max(dp[1][i-1] + arr[0][i], dp[1][i-2] + arr[0][i]);
            dp[1][i] = Math.max(dp[0][i-1] + arr[1][i], dp[0][i-2] + arr[1][i]);
            int max = Math.max(dp[0][i], dp[1][i]);
            result = Math.max(result, max);
        }
        System.out.println(result);
    }
}
