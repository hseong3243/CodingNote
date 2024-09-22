package 백준.gold.다리_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int[][] map;

    private void solution(int n, int[][] map) {
        this.n = n;
        this.map = map;
        int[][] islands = new int[n][n];
        int island = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (islands[i][j] != 0) {
                    continue;
                }
                find(islands, island, i, j);
                island++;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < island - 1; i++) {
            result = Math.min(result, findShortest(islands, i));
        }

        System.out.println(result);
    }

    private void find(int[][] islands, int island, int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        islands[row][col] = island;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];
            for (int d = 0; d < 4; d++) {
                int nextR = r + DR[d];
                int nextC = c + DC[d];
                if (canMove(nextR, nextC, islands)) {
                    islands[nextR][nextC] = island;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }
    }

    private boolean canMove(int row, int col, int[][] islands) {
        if (row >= n || row < 0) {
            return false;
        }
        if (col >= n || col < 0) {
            return false;
        }
        if (map[row][col] == 0) {
            return false;
        }
        if (islands[row][col] != 0) {
            return false;
        }
        return true;
    }

    private int findShortest(int[][] islands, int island) {
        boolean[][] visit = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (islands[i][j] != island) {
                    continue;
                }
                q.add(new int[]{i, j, 0});
                visit[i][j] = true;
            }
        }

        while (!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];
            if (islands[r][c] != 0 && islands[r][c] != island) {
                return point[2] - 1;
            }
            for (int d = 0; d < 4; d++) {
                int nextRow = r + DR[d];
                int nextCol = c + DC[d];
                if (canMove(nextRow, nextCol, visit)) {
                    q.add(new int[]{nextRow, nextCol, point[2] + 1});
                    visit[nextRow][nextCol] = true;
                }
            }
        }
        return -1;
    }

    private boolean canMove(int row, int col, boolean[][] visit) {
        if (row >= n || row < 0) {
            return false;
        }
        if (col >= n || col < 0) {
            return false;
        }
        if (visit[row][col]) {
            return false;
        }
        return true;
    }
}
