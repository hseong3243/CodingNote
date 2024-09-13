package 백준.gold.인구_이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int l = Integer.parseInt(split[1]);
        int r = Integer.parseInt(split[2]);
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, l, r, map);
    }

    private static class Ally {

        private List<int[]> kingdom = new ArrayList<>();
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int l;
    private int r;
    private int[][] map;

    private void solution(int n, int l, int r, int[][] map) {
        this.n = n;
        this.l = l;
        this.r = r;
        this.map = map;

        int result = 0;
        boolean keepContinue = true;
        while (true) {
            List<Ally> allies = new ArrayList<>();
            boolean[][] visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j]) {
                        continue;
                    }
                    allies.add(bfs(i, j, visit));
                }
            }
            keepContinue = false;
            for (Ally ally : allies) {
                int people = 0;
                for (int[] ints : ally.kingdom) {
                    people += map[ints[0]][ints[1]];
                }
                people /= ally.kingdom.size();
                for (int[] ints : ally.kingdom) {
                    map[ints[0]][ints[1]] = people;
                }

                if (ally.kingdom.size() > 1) {
                    keepContinue = true;
                }
            }
            if(keepContinue) {
                result++;
            } else {
                break;
            }
        }
        System.out.println(result);
    }

    private Ally bfs(int row, int col, boolean[][] visit) {
        Ally ally = new Ally();
        Queue<int[]> q = new LinkedList<>();
        int[] startPoint = {row, col};
        visit[row][col] = true;
        ally.kingdom.add(startPoint);
        q.add(startPoint);
        while (!q.isEmpty()) {
            int[] point = q.poll();
            for (int d = 0; d < 4; d++) {
                int nextRow = point[0] + DR[d];
                int nextCol = point[1] + DC[d];
                if (!canMove(nextRow, nextCol, visit)) {
                    continue;
                }
                if (!canOpenBorder(point[0], point[1], nextRow, nextCol)) {
                    continue;
                }
                visit[nextRow][nextCol] = true;
                int[] nextPoint = {nextRow, nextCol};
                ally.kingdom.add(nextPoint);
                q.add(nextPoint);
            }
        }
        return ally;
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

    private boolean canOpenBorder(int row, int col, int nextRow, int nextCol) {
        int people = map[row][col];
        int nextPeople = map[nextRow][nextCol];
        int movePeople = Math.abs(people - nextPeople);
        if (movePeople >= l && movePeople <= r) {
            return true;
        }
        return false;
    }
}
