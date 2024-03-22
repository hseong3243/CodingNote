package 백준.gold.빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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
        new Main().solution(n, m, map);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int m;
    private int[][] map;

    private void solution(int n, int m, int[][] arr) {
        this.n = n;
        this.m = m;
        this.map = arr;

        int time = 0;
        while (true) {
            int count = 0;
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0 || visited[i][j]) {
                        continue;
                    }
                    count++;
                    visit(i, j, visited);
                }
            }
            if(count >= 2) {
                break;
            }
            if(count == 0) {
                time = 0;
                break;
            }
            time++;

            int[][] temp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }
                    int melt = 0;
                    for (int d = 0; d < 4; d++) {
                        int tileR = i + DR[d];
                        int tileC = j + DC[d];
                        if (map[tileR][tileC] == 0) {
                            melt++;
                        }
                    }
                    temp[i][j] = Math.max(map[i][j] - melt, 0);
                }
            }
            map = temp;
        }

        System.out.println(time);
    }

    private void visit(int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] point = q.poll();
            for(int d=0; d<4; d++) {
                int nextR = point[0] + DR[d];
                int nextC = point[1] + DC[d];
                if(canMove(nextR, nextC, visited)) {
                    visited[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }
    }

    private boolean canMove(int r, int c, boolean[][] visited) {
        if(r < 0 || c < 0 || r >= n || c >= m) {
            return false;
        }
        if(map[r][c] == 0) {
            return false;
        }
        if(visited[r][c]) {
            return false;
        }
        return true;
    }
}
