package 백준.gold.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strA = br.readLine();
        String strB = br.readLine();
        new Main().solution(strA, strB);
    }

    private void solution(String strA, String strB) {
        char[] arrA = strA.toCharArray();
        char[] arrB = strB.toCharArray();

        int n = arrA.length;
        int m = arrB.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (arrA[i - 1] == arrB[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
