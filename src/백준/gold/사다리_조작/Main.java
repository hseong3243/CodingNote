package 백준.gold.사다리_조작;

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
        int h = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[n + 1][h + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[b][a] = true;
        }
        new Main().solution(n, m, h, map);
    }

    private int n;
    private int m;
    private int h;
    private boolean[][] map;
    private int result = 4;

    private void solution(int n, int m, int h, boolean[][] map) {
        this.n = n;
        this.m = m;
        this.h = h;
        this.map = map;
        dfs(0);
        if (result >= 4) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private void dfs(int count) {
        if (end()) {
            result = Math.min(result, count);
            return;
        }
        if (count >= 3) {
            return;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= h; j++) {
                if (map[i][j]) {
                    continue;
                }
                if (isTarget(i, j)) {
                    map[i][j] = true;
                    dfs(count + 1);
                    map[i][j] = false;
                }
            }
        }
    }

    private boolean end() {
        for (int i = 1; i <= n; i++) {
            int now = i;
            for (int j = 1; j <= h; j++) {
                if (map[now][j]) {
                    now++;
                } else if (now - 1 >= 1 && map[now - 1][j]) {
                    now--;
                }

                if (Math.abs(now - i) > h - j) {
                    return false;
                }
            }
            if (i != now) {
                return false;
            }
        }
        return true;
    }

    private boolean isTarget(int i, int j) {
        if (i - 1 >= 1 && map[i - 1][j]) {
            return false;
        }
        if (i + 1 <= n && map[i + 1][j]) {
            return false;
        }
        return true;
    }
}
