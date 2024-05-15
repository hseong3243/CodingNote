package 백준.gold.포도주_시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        new Main().solution(n, arr);
    }

    private int n;
    private int[] arr;

    private void solution(int n, int[] arr) {
        this.n = n;
        this.arr = arr;

        int[][] dp = new int[n][3];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        dp[0][1] = arr[0];
        dp[0][0] = 0;
        dp[0][2] = 0;
        drink(dp,n-1, 0);
        drink(dp,n-1, 1);
        drink(dp,n-1, 2);

        int result = 0;
        for(int i=0; i<3; i++) {
            result = Math.max(result, dp[n - 1][i]);
        }
        System.out.println(result);
    }

    private int drink(int[][] dp, int idx, int count) {
        if(idx == 0) {
            return dp[idx][count];
        }
        if(dp[idx][count] != -1) {
            return dp[idx][count];
        }

        if(count == 0) {
            dp[idx][0] = Math.max(dp[idx][0], drink(dp, idx-1, 0));
            dp[idx][0] = Math.max(dp[idx][0], drink(dp, idx-1, 1));
            dp[idx][0] = Math.max(dp[idx][0], drink(dp, idx-1, 2));
        } else if(count == 1) {
            dp[idx][1] = drink(dp, idx - 1, 0) + arr[idx];
        } else if(count == 2) {
            dp[idx][2] = drink(dp, idx - 1, 1) + arr[idx];
        }
        return dp[idx][count];
    }
}
