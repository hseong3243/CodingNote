package 백준.gold.적록색약;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        new Main().solution(n, arr);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static final Set<Character> ABNORMAL_COLOR = Set.of('R', 'G');

    private int n;
    private char[][] arr;

    private void solution(int n, char[][] arr) {
        this.n = n;
        this.arr = arr;
        int normal = normal();
        int abnormal = abnormal();
        System.out.println(normal + " " + abnormal);
    }

    private int normal() {
        int count = 0;
        boolean[][] visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) {
                    continue;
                }
                find(visit, i, j);
                count++;
            }
        }
        return count;
    }

    private void find(boolean[][] visit, int row, int col) {
        visit[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int newRow = row + DR[i];
            int newCol = col + DC[i];
            if (canNotVisitNormal(newRow, newCol, visit, arr[row][col])) {
                continue;
            }
            find(visit, newRow, newCol);
        }
    }

    private boolean canNotVisitNormal(int row, int col, boolean[][] visit, char color) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return true;
        }
        if (visit[row][col]) {
            return true;
        }
        return color != arr[row][col];
    }

    private int abnormal() {
        int count = 0;
        boolean[][] visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) {
                    continue;
                }
                findAbnormal(visit, i, j);
                count++;
            }
        }
        return count;
    }

    private void findAbnormal(boolean[][] visit, int row, int col) {
        visit[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int newRow = row + DR[i];
            int newCol = col + DC[i];
            if (canNotVisitAbnormal(newRow, newCol, visit, arr[row][col])) {
                continue;
            }
            findAbnormal(visit, newRow, newCol);
        }
    }

    private boolean canNotVisitAbnormal(int row, int col, boolean[][] visit, char color) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return true;
        }
        if (visit[row][col]) {
            return true;
        }
        if (ABNORMAL_COLOR.contains(color) && ABNORMAL_COLOR.contains(arr[row][col])) {
            return false;
        }
        return color != arr[row][col];
    }
}
