package 백준.gold.RGB거리_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(arr, n);
    }

    public void solution(int[][] arr, int n) {
        int result = 1000*1000;
        int[][] dp = new int[n][3];
        for(int start=0; start<3; start++) {
            for(int i=0; i<3; i++) {
                if(start == i) {
                    continue;
                }
                dp[0][i] = 1000*1000;
            }
            dp[0][start] = arr[0][start];
            for(int i=1; i<n; i++) {
                dp[i][0] = Math.min(dp[i-1][1] + arr[i][0], dp[i-1][2] + arr[i][0]);
                dp[i][1] = Math.min(dp[i-1][0] + arr[i][1], dp[i-1][2] + arr[i][1]);
                dp[i][2] = Math.min(dp[i-1][0] + arr[i][2], dp[i-1][1] + arr[i][2]);
            }
            for(int i=0; i<3; i++) {
                if(start == i) {
                    continue;
                }
                result = Math.min(result, dp[n-1][i]);
            }
        }
        System.out.println(result);
    }
}
