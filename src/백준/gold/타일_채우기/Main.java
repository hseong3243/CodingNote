package 백준.gold.타일_채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        new Main().solution(n);
    }

    private void solution(int n) {
        long[] dp = new long[n + 1];
        int[] arr = new int[n + 1];
        if(n == 1) {
            System.out.println(0);
            return;
        }
        arr[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            arr[i] = 2;
        }

        dp[2] = arr[2];
        for (int i = 4; i <= n; i += 2) {
            for (int j = 2; j < i; j += 2) {
                dp[i] += dp[i - j] * arr[j];
            }
            dp[i] += arr[i];
        }
        System.out.println(dp[n]);
    }
}
