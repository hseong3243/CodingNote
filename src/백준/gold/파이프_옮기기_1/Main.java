package 백준.gold.파이프_옮기기_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, map);
    }

    private void solution(int n, int[][] map) {
        int[][][] dp = new int[n][n][3];
        dp[0][1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1) {
                    continue;
                }
                if (j - 1 >= 0) {
                    dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][1];
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if(map[i][j-1] == 0 && map[i-1][j] == 0) {
                        dp[i][j][1] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
                if (i - 1 >= 0) {
                    dp[i][j][2] += dp[i - 1][j][1] + dp[i - 1][j][2];
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += dp[n - 1][n - 1][i];
        }

        System.out.println(result);
    }
}
