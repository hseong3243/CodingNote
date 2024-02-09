package 프로그래머스.level3.블록_이동하기;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0}};
        int result = new Solution().solution(board);
        System.out.println(result);
    }

    static class Robot {

        int r;
        int c;
        int d;
        int cost;

        public Robot(int r, int c, int d, int cost) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cost = cost;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static final int[][] DIRECTION = {{0, 1}, {1, 0}};
    private static final int[][] ROTATE = {{0, 0}, {0, 1}, {-1, 0}, {-1, 1}, {0, 0}, {1, 0},
        {0, -1}, {1, -1}};
    private static final int[][] RANGE = {{0, 0}, {1, 0}, {1, 1}, {0, 1}};
    private static final int[][] RANGE_CHECK = {{0, 0}, {-1, 0}, {0, 0}, {0, -1}};

    private int n;
    private int[][] board;
    private boolean[][][] visit;

    public int solution(int[][] board) {
        n = board.length;
        this.board = board;
        visit = new boolean[n][n][2];
        PriorityQueue<Robot> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        q.add(new Robot(0, 0, 0, 0));
        while (!q.isEmpty()) {
            Robot robot = q.poll();
            int dir = robot.d;
            if ((robot.r == n - 1 && robot.c == n - 1)
                || (robot.r + DIRECTION[dir][0] == n - 1)
                && (robot.c + DIRECTION[dir][1] == n - 1)) {
                return robot.cost;
            }
            int nextCost = robot.cost + 1;
            for (int d = 0; d < 4; d++) {
                int nextR = robot.r + DR[d];
                int nextC = robot.c + DC[d];
                if (canMove(nextR, nextC, dir)) {
                    visit[nextR][nextC][dir] = true;
                    q.add(new Robot(nextR, nextC, dir, nextCost));
                }
            }
            if (dir == 0) {  //가로
                for (int r = 0; r < 4; r++) {
                    int rotateR = robot.r + ROTATE[r][0];
                    int rotateC = robot.c + ROTATE[r][1];
                    if (r < 2 && canRotate(robot, RANGE_CHECK[0]) && !visit[rotateR][rotateC][1]) {
                        visit[rotateR][rotateC][1] = true;
                        q.add(new Robot(rotateR, rotateC, 1, nextCost));
                    } else if (r >= 2 && canRotate(robot,
                        RANGE_CHECK[1]) && !visit[rotateR][rotateC][1]) {
                        visit[rotateR][rotateC][1] = true;
                        q.add(new Robot(rotateR, rotateC, 1, nextCost));
                    }
                }
            } else {
                for (int r = 4; r < 8; r++) {
                    int rotateR = robot.r + ROTATE[r][0];
                    int rotateC = robot.c + ROTATE[r][1];
                    if (r < 6 && canRotate(robot, RANGE_CHECK[2]) && !visit[rotateR][rotateC][0]) {
                        visit[rotateR][rotateC][0] = true;
                        q.add(new Robot(rotateR, rotateC, 0, nextCost));
                    } else if (r >= 6 && canRotate(robot,
                        RANGE_CHECK[3]) && !visit[rotateR][rotateC][0]) {
                        visit[rotateR][rotateC][0] = true;
                        q.add(new Robot(rotateR, rotateC, 0, nextCost));
                    }
                }
            }

        }
        return 0;
    }

    private boolean canMove(int r, int c, int d) {
        int rB = r + DIRECTION[d][0];
        int cB = c + DIRECTION[d][1];
        if (r < 0 || r >= n || c < 0 || c >= n || rB < 0 || rB >= n || cB < 0 || cB >= n) {
            return false;
        }
        if (board[r][c] == 1 || board[rB][cB] == 1) {
            return false;
        }
        if (visit[r][c][d]) {
            return false;
        }
        return true;
    }

    private boolean canRotate(Robot robot, int[] check) {
        for (int r = 0; r < 4; r++) {
            int checkR = robot.r + RANGE[r][0] + check[0];
            int checkC = robot.c + RANGE[r][1] +  check[1];
            if (checkR < 0 || checkR >= n || checkC < 0 || checkC >= n) {
                return false;
            }
            if (board[checkR][checkC] == 1) {
                return false;
            }
        }
        return true;
    }
}
