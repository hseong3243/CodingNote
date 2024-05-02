package 백준.gold.아기_상어;

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

    static class Shark {

        int count = 0;
        int level = 2;

        public void increase() {
            count++;
            if (level == count) {
                level++;
                count = 0;
            }
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int[][] map;
    private Shark shark;

    private void solution(int n, int[][] map) {
        this.n = n;
        this.map = map;
        this.shark = new Shark();
        int[] origin = findOrigin(n, map);
        map[origin[0]][origin[1]] = 0;

        int time = 0;
        while (true) {
            int[] point = findNextPoint(map, origin);
            if (point[0] == -1) {
                break;
            }
            map[point[0]][point[1]] = 0;
            origin = point;
            shark.increase();
            time += point[2];
        }
        System.out.println(time);
    }

    private int[] findOrigin(int n, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 9) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    private int[] findNextPoint(int[][] map, int[] origin) {
        boolean[][] visit = new boolean[n][n];
        List<int[]> find = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{origin[0], origin[1], 0});
        visit[origin[0]][origin[1]] = true;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            if (!find.isEmpty()) {
                int[] last = find.get(find.size() - 1);
                if (point[2] > last[2]) {
                    break;
                }
            }
            if (map[point[0]][point[1]] != 0 && map[point[0]][point[1]] < shark.level) {
                find.add(point);
            }
            for (int d = 0; d < 4; d++) {
                int nextR = point[0] + DR[d];
                int nextC = point[1] + DC[d];
                if (canMove(nextR, nextC, visit)) {
                    visit[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC, point[2] + 1});
                }
            }
        }
        if (find.isEmpty()) {
            return new int[]{-1};
        }
        int[] result = null;
        for (int[] point : find) {
            if (result == null) {
                result = point;
            } else {
                if (point[0] < result[0]) {
                    result = point;
                } else if (point[0] == result[0] && (point[1] < result[1])) {
                    result = point;
                }
            }
        }
        return result;
    }

    private boolean canMove(int nextR, int nextC, boolean[][] visit) {
        if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) {
            return false;
        }
        if (map[nextR][nextC] > shark.level) {
            return false;
        }
        if (visit[nextR][nextC]) {
            return false;
        }
        return true;
    }
}
