package 백준.gold.욕심쟁이_판다;

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

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int[][] map;
    private int[][] memory;

    private void solution(int n, int[][] map) {
        this.n = n;
        this.map = map;
        this.memory = new int[n][n];

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, find(i, j));
            }
        }
        System.out.println(result);
    }

    private int find(int row, int col) {
        if(memory[row][col] != 0) {
            return memory[row][col];
        }
        int result = 1;
        for (int d = 0; d < 4; d++) {
            int nextR = row + DR[d];
            int nextC = col + DC[d];
            if (canMove(nextR, nextC, row, col)) {
                result = Math.max(result, find(nextR, nextC) + 1);
            }
        }
        memory[row][col] = result;
        return memory[row][col];
    }

    private boolean canMove(int nextR, int nextC, int r, int c) {
        if (nextC < 0 || nextR < 0 || nextC >= n || nextR >= n) {
            return false;
        }
        if (map[nextR][nextC] <= map[r][c]) {
            return false;
        }
        return true;
    }
}
