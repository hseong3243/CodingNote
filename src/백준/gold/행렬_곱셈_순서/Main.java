package 백준.gold.행렬_곱셈_순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
        }
        new Main().solution(n, arr);
    }

    private int[][] dp;
    private int[][] arr;

    private void solution(int n, int[][] arr) {
        dp = new int[n][n];
        this.arr = arr;
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        int result = find(0, n-1);
        System.out.println(result);
    }

    private int find(int left, int right) {
        if (dp[left][right] != Integer.MAX_VALUE) {
            return dp[left][right];
        }
        if (right - left == 1) {
            dp[left][right] = arr[left][0] * arr[left][1] * arr[right][1];
            return dp[left][right];
        }
        int a = arr[left][0]*arr[left][1]*arr[right][1] + find(left+1, right);
        int b = find(left, right-1) + arr[left][0]*arr[right][0]*arr[right][1];
        dp[left][right] = Math.min(a, b);
        for (int mid = left+1; mid < right-1; mid++) {
            int result = find(left, mid) + find(mid + 1, right)
                    + arr[left][0] * arr[mid][1] * arr[right][1];
            dp[left][right] = Math.min(dp[left][right], result);
        }
        return dp[left][right];
    }
}
