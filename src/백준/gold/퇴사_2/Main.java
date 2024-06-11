package 백준.gold.퇴사_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, arr);
    }

    private void solution(int n, int[][] arr) {
        int[] dp = new int[n+1];
        for(int i=0; i<n; i++) {
            int time = arr[i][0];
            int money = arr[i][1];
            dp[i+1] = Math.max(dp[i+1], dp[i]);
            if(i+time > n) {
                continue;
            }
            dp[i + time] = Math.max(dp[i + time], dp[i] + money);
        }
        System.out.println(dp[n]);
    }
}
