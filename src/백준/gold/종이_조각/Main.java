package 백준.gold.종이_조각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] numbers = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        new Main(n, m, map).solution();
    }

    private final int n;
    private final int m;
    private final int[][] map;

    public Main(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;
    }

    private void solution() {
        int result = find(0, 0, new int[n][m]);
        System.out.println(result);
    }

    private int find(int r, int c, int[][] arr) {
        int result = 0;
        if (r >= n) {
            return Math.max(result, getSum(arr));
        }
        if (c >= m) {
            return Math.max(result, find(r + 1, 0, arr));
        }
        arr[r][c] = 0;  // 가로
        result = Math.max(result, find(r, c + 1, arr));
        arr[r][c] = 1;  // 세로
        result = Math.max(result, find(r, c + 1, arr));
        return result;
    }

    private int getSum(int[][] arr) {
        boolean[][] visited = new boolean[n][m];
        int result = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (arr[i][j] == 0) {  // 가로이면
                    if (visited[i][j]) {
                        continue;
                    }
                    visited[i][j] = true;
                    result += (int) (Math.pow(10, count++) * map[i][j]);
                } else {
                    count = 0;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            int count = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i][j] == 1) {  // 세로이면
                    if (visited[i][j]) {
                        continue;
                    }
                    visited[i][j] = true;
                    result += (int) (Math.pow(10, count++) * map[i][j]);
                } else {
                    count = 0;
                }
            }
        }
        return result;
    }
}
