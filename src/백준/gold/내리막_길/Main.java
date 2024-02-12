package 백준.gold.내리막_길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main(n, m, map).solution();
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int m;
    private int[][] map;

    public Main(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;
    }

    private void solution() {
        int[][] dp = new int[n][m];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        dp[0][0] = dfs(0, 0, dp);
        System.out.println(dp[0][0]);
    }

    private int dfs(int r, int c, int[][] dp) {
        if(dp[r][c] != -1) {
            return dp[r][c];
        }
        if(r == n - 1 && c == m - 1) {
            return 1;
        }
        dp[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nextR = r + DR[d];
            int nextC = c + DC[d];
            if (canMove(nextR, nextC, map[r][c])) {
                dp[r][c] += dfs(nextR, nextC, dp);
            }
        }
        return dp[r][c];
    }

    private boolean canMove(int r, int c, int prev) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return false;
        }
        if (map[r][c] >= prev) {
            return false;
        }
        return true;
    }
}
