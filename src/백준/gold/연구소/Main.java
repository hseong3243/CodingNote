package 백준.gold.연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
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
    private List<int[]> virusPoints = new ArrayList<>();
    private int result;

    private void solution(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    virusPoints.add(new int[]{i, j});
                }
            }
        }

        select(new int[]{0, -1}, new Stack<>());
        System.out.println(result);
    }

    private void select(int[] point, Stack<int[]> selectedPoint) {
        if (selectedPoint.size() == 3) {
            check(selectedPoint);
            return;
        }
        int r = point[0];
        int c = point[1];
        while (true) {
            c++;
            if (c >= m) {
                c = 0;
                r++;
            }
            if (r >= n) {
                break;
            }
            if (map[r][c] == 1 || map[r][c] == 2) {
                continue;
            }
            selectedPoint.push(new int[]{r, c});
            select(new int[]{r, c}, selectedPoint);
            selectedPoint.pop();
        }
    }

    private void check(Stack<int[]> selectedPoint) {
        int[][] clonedMap = new int[n][m];
        for(int i=0; i<n; i++) {
            clonedMap[i] = map[i].clone();
        }
        for (int[] point : selectedPoint) {
            int r = point[0];
            int c = point[1];
            clonedMap[r][c] = 1;
        }

        Queue<int[]> q = new LinkedList<>(virusPoints);
        while (!q.isEmpty()) {
            int[] point = q.poll();
            for (int d = 0; d < 4; d++) {
                int nextR = point[0] + DR[d];
                int nextC = point[1] + DC[d];
                if (canMove(nextR, nextC, clonedMap)) {
                    clonedMap[nextR][nextC] = 2;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (clonedMap[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.max(result, count);
    }

    private boolean canMove(int nextR, int nextC, int[][] map) {
        if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
            return false;
        }
        if (map[nextR][nextC] == 1 || map[nextR][nextC] == 2) {
            return false;
        }
        return true;
    }
}
