package 백준.silver.데스_나이트;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static final int[] DR = {-2, -2, 0, 0, 2, 2};
    private static final int[] DC = {-1, 1, -2, 2, -1, 1};

    static class Point {

        int r;
        int c;
        int cost;

        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();
        new Main().solution(n, r1, c1, r2, c2);
    }

    public void solution(int n, int r1, int c1, int r2, int c2) {
        int[][] map = new int[n][n];
        for (int[] ints : map) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r1, c1, 0));
        while (!q.isEmpty()) {
            Point point = q.poll();
            if (point.r == r2 && point.c == c2) {
                System.out.println(point.cost);
                return;
            }
            for (int d = 0; d < 6; d++) {
                int nextRow = point.r + DR[d];
                int nextCol = point.c + DC[d];
                int nextCost = point.cost + 1;
                if (canMove(nextRow, nextCol, nextCost, map)) {
                    map[nextRow][nextCol] = nextCost;
                    q.add(new Point(nextRow, nextCol, nextCost));
                }
            }
        }
        System.out.println(-1);
    }

    private boolean canMove(int nextRow, int nextCol, int cost, int[][] map) {
        int n = map.length;
        if (nextRow < 0 || nextRow >= n) {
            return false;
        }
        if (nextCol < 0 || nextCol >= n) {
            return false;
        }
        if (map[nextRow][nextCol] <= cost) {
            return false;
        }
        return true;
    }
}
