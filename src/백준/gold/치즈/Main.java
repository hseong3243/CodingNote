package 백준.gold.치즈;

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
        int m = Integer.parseInt(split[1]);
        int[][] map = new int[n][m];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n,m,map);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int m;
    private int[][] map;

    private void solution(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;
        int[] start = new int[]{0,0};

        int prevCount = 0;
        int time = 0;
        while (true) {
            boolean[][] mark = mark(start);
            List<int[]> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        points.add(new int[]{i, j});
                    }
                }
            }
            if (points.isEmpty()) {
                break;
            }
            prevCount = points.size();
            List<int[]> needToRemove = new ArrayList<>();
            for (int[] point : points) {
                for (int d = 0; d < 4; d++) {
                    int targetR = point[0] + DR[d];
                    int targetC = point[1] + DC[d];
                    if (mark[targetR][targetC]) {
                        needToRemove.add(point);
                        break;
                    }
                }
            }
            for (int[] point : needToRemove) {
                map[point[0]][point[1]] = 0;
            }
            time++;
        }
        System.out.println(time);
        System.out.println(prevCount);
    }

    private boolean[][] mark(int[] start) {
        boolean[][] visit = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visit[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            for(int d=0; d<4; d++) {
                int nextR = point[0] + DR[d];
                int nextC = point[1] + DC[d];
                if(canMove(nextR, nextC, visit) && map[nextR][nextC] == 0) {
                    q.add(new int[]{nextR, nextC});
                    visit[nextR][nextC] = true;
                }
            }
        }
        return visit;
    }

    private boolean canMove(int nextR, int nextC, boolean[][] visit) {
        if(nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) {
            return false;
        }
        if(visit[nextR][nextC]) {
            return false;
        }
        return true;
    }
}
