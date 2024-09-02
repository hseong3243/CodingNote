package 백준.silver.계단_오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        new Main().solution(n, arr);
    }

    private void solution(int n, int[] arr) {
        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        if (n > 1) {
            dp[1][0] = arr[1];
            dp[1][1] = arr[1] + arr[0];
        }
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 2][0] + arr[i], dp[i - 2][1] + arr[i]);
            dp[i][1] = dp[i - 1][0] + arr[i];
        }
        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
    }
}
