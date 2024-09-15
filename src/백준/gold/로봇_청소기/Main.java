package 백준.gold.로봇_청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Robot {

        private int r;
        private int c;
        private int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void turn() {
            d--;
            if(d < 0) {
                d = 3;
            }
        }

        public void move(int nextRow, int nextCol) {
            this.r = nextRow;
            this.c = nextCol;
        }

        public int back() {
            int backDir = d;
            backDir -= 2;
            if(backDir < 0) {
                backDir += 4;
            }
            return backDir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        split = br.readLine().split(" ");
        Robot robot = new Robot(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
            Integer.parseInt(split[2]));
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, m, robot, map);
    }

    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    private int n;
    private int m;
    private Robot robot;
    private int[][] map;

    private void solution(int n, int m, Robot robot, int[][] map) {
        this.n = n;
        this.m = m;
        this.robot = robot;
        this.map = map;

        int result = 0;
        boolean[][] visit = new boolean[n][m];
        while (true) {
            int row = robot.r;
            int col = robot.c;
            int dir = robot.d;
            if(!visit[row][col]) {
                visit[row][col] = true;
                result++;
            }

            if(existsClean(visit)) {
                robot.turn();
                int nextRow = robot.r + DR[robot.d];
                int nextCol = robot.c + DC[robot.d];
                if(canClean(nextRow, nextCol, visit)) {
                    robot.move(nextRow, nextCol);
                }
            } else {
                int backDir = robot.back();
                int nextRow = robot.r + DR[backDir];
                int nextCol = robot.c + DC[backDir];
                if(canMove(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
                    robot.move(nextRow, nextCol);
                } else {
                    break;
                }
            }
        }
        System.out.println(result);
    }

    private boolean existsClean(boolean[][] visit) {
        for(int d=0; d<4; d++) {
            int nextRow = robot.r + DR[d];
            int nextCol = robot.c + DC[d];
            if(canClean(nextRow, nextCol, visit)) {
                return true;
            }
        }
        return false;
    }

    private boolean canClean(int nextRow, int nextCol, boolean[][] visit) {
        if(!canMove(nextRow, nextCol)) {
            return false;
        }
        if(map[nextRow][nextCol] == 1) {
            return false;
        }
        if(visit[nextRow][nextCol]) {
            return false;
        }
        return true;
    }

    private boolean canMove(int row, int col) {
        if(row < 0 || row >= n) {
            return false;
        }
        if(col < 0 || col >= m) {
            return false;
        }
        return true;
    }
}
