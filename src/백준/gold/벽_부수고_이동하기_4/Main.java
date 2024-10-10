package 백준.gold.벽_부수고_이동하기_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j] - '0';
            }
        }
        new Main().solution(n, m, map);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static class Point {

        private int r;
        private int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private int n;
    private int m;
    private int[][] map;
    private Point[][] parent;

    private void solution(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;
        parent = new Point[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parent[i][j] = new Point(i, j);
            }
        }
        boolean[][] visit = new boolean[n][m];
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    result[i][j] = dfs(i, j, i, j, visit);
                }
            }
        }
        boolean[][] useCheck = new boolean[n][m];
        Set<Point> use = new HashSet<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 1) {
                    int value = 1;
                    for(int d=0; d<4; d++) {
                        int nextR = i + DR[d];
                        int nextC = j + DC[d];
                        if (isOutOfRange(nextR, nextC) || map[nextR][nextC] == 1) {
                            continue;
                        }
                        Point point = parent[nextR][nextC];
                        use.add(point);
                    }

                    for (Point point : use) {
                        if(useCheck[point.r][point.c]) {
                            continue;
                        }
                        useCheck[point.r][point.c] = true;
                        value += result[point.r][point.c];
                    }
                    for (Point point : use) {
                        useCheck[point.r][point.c] = false;
                    }
                    use.clear();
                    result[i][j] = value % 10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 1) {
                    sb.append(result[i][j]);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private int dfs(int parentR, int parentC, int r, int c, boolean[][] visit) {
        visit[r][c] = true;
        int result = 1;
        parent[r][c] = parent[parentR][parentC];
        for (int d = 0; d < 4; d++) {
            int nextR = r + DR[d];
            int nextC = c + DC[d];
            if (canMove(nextR, nextC, visit)) {
                result += dfs(parentR, parentC, nextR, nextC, visit);
            }
        }
        return result;
    }

    private boolean canMove(int r, int c, boolean[][] visit) {
        if (isOutOfRange(r, c)) {
            return false;
        }
        if (visit[r][c]) {
            return false;
        }
        if (map[r][c] == 1) {
            return false;
        }
        return true;
    }

    private boolean isOutOfRange(int r, int c) {
        if (r < 0 || r >= n) {
            return true;
        }
        if (c < 0 || c >= m) {
            return true;
        }
        return false;
    }
}
