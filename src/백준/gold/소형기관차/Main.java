package 백준.gold.소형기관차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        new Main().solution(n, arr, m);
    }

    private void solution(int n, int[] arr, int m) {
        int[][] dp = new int[n+1][3];
        for(int i=m; i<=n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], arr[i] - arr[i-m]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-m][0] + arr[i] - arr[i-m]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-m][1] + arr[i] - arr[i-m]);
        }
        int result = 0;
        for(int i=1; i<=n; i++) {
            result = Math.max(result, dp[i][2]);
        }
        System.out.println(result);
    }
}
