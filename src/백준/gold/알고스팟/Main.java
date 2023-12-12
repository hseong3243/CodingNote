package 백준.gold.알고스팟;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {

        int r;
        int c;
        int power;

        public Point(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        char[][] map = new char[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }
        solution(row, col, map);
        System.out.println(result);
    }

    /**
     * 1*1 크기의 방으로 이루어진 n*m 크기의 미로 빈 방 또는 벽. 벽은 부숴야 함 0은 빈 방, 1은 벽
     */
    public static void solution(int n, int m, char[][] map) {
        int[][] sum = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i][j] = Integer.MAX_VALUE;
            }
        }
        n--;
        m--;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0));
        while (!q.isEmpty()) {
            Point point = q.poll();
            if (point.r == n && point.c == m) {
                result = Math.min(result, point.power);
            }
            for (int d = 0; d < 4; d++) {
                int nextRow = point.r + DR[d];
                int nextCol = point.c + DC[d];
                if (canMove(nextRow, nextCol, map, sum, point.power)) {
                    int power = point.power;
                    sum[nextRow][nextCol] = power;
                    if (map[nextRow][nextCol] == '1') {
                        power++;
                    }
                    q.add(new Point(nextRow, nextCol, power));
                }
            }
        }
    }

    private static boolean canMove(int r, int c, char[][] map, int[][] sum, int power) {
        int rowLength = map.length;
        int colLength = map[0].length;
        if (r < 0 || r >= rowLength) {
            return false;
        }
        if (c < 0 || c >= colLength) {
            return false;
        }
        if (sum[r][c] <= power) {
            return false;
        }
        return true;
    }
}
