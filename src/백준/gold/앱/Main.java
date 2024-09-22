package 백준.gold.앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] memories = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }
        int[] costs = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, m, memories, costs);
    }

    private void solution(int n, int m, int[] memories, int[] costs) {
        int[][] dp = new int[n+1][10001];

        for(int i=1; i<=n; i++) {
            for(int c=0; c<=10000; c++) {
                if(costs[i] <= c) {
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - costs[i]] + memories[i]);
                } else {
                    dp[i][c] = dp[i-1][c];
                }
            }
        }

        for(int i=1; i<=n; i++) {
            for(int c=0; c<=15; c++) {
                System.out.print(dp[i][c] + " ");
            }
            System.out.println();
        }

        int result = 0;
        for(int c=0; c<=10000; c++) {
            if(dp[n][c] >= m) {
                result = c;
                break;
            }
        }
        System.out.println(result);
    }
}
