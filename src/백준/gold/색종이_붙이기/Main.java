package 백준.gold.색종이_붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(map);

    }

    private static final int N = 10;
    private int[][] map;
    private int result = 26;

    private void solution(int[][] map) {
        this.map = map;
        int[] paper = {0, 0, 0, 0, 0, 0};
        dfs(0, 0, 0, paper);
        if (result > 25) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private void dfs(int r, int c, int count, int[] paper) {
        if (r >= 9 && c >= 10) {
            result = Math.min(result, count);
            return;
        }
        if (count >= result) {
            return;
        }
        if (c >= 10) {
            c = 0;
            r++;
        }
        if (map[r][c] == 1) {
            for (int len = 1; len <= 5; len++) {
                if (!canTaping(r, c, len) || paper[len] == 5) {
                    continue;
                }
                taping(r, c, len, 0);
                paper[len]++;
                dfs(r, c + 1, count + 1, paper);
                paper[len]--;
                taping(r, c, len, 1);
            }
        } else {
            dfs(r, c + 1, count, paper);
        }
    }

    private boolean canTaping(int r, int c, int len) {
        for (int i = r; i < r+len; i++) {
            for (int j = c; j < c+len; j++) {
                if(i >= 10 || j >= 10 || i < 0 || j < 0) {
                    return false;
                }
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void taping(int r, int c, int len, int paper) {
        for (int i = r; i < r+len; i++) {
            for (int j = c; j < c+len; j++) {
                map[i][j] = paper;
            }
        }
    }
}
