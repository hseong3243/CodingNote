package 백준.gold.수확;

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

    private int n;
    private int[] arr;

    private void solution(int n, int[] arr) {
        this.n = n;
        this.arr = arr;

        int[][] dp = new int[n][n];
        int result = find(0, 0, n - 1, dp);
        System.out.println(result);
    }

    private int find(int count, int start, int end, int[][] dp) {
        if(count >= n) {
            return 0;
        }
        if(dp[start][end] != 0) {
            return dp[start][end];
        }
        dp[start][end] = Math.max(
            find(count + 1, start + 1, end, dp) + arr[start] * (1 + count),
            find(count + 1, start, end - 1, dp) + arr[end] * (1 + count)
        );
        return dp[start][end];
    }
}
