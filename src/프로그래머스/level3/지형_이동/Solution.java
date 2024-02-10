package 프로그래머스.level3.지형_이동;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
        int height = 3;
//        int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
//        int height = 1;
        int result = new Solution().solution(land, height);
        System.out.println(result);
    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static class Candidate {
        Point point;
        int diff;

        public Candidate(Point point, int diff) {
            this.point = point;
            this.diff = diff;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;

    public int solution(int[][] land, int height) {
        n = land.length;
        int min = 10000;
        Point minPoint = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] < min) {
                    min = land[i][j];
                    minPoint = new Point(i, j);
                }
            }
        }
        return bfs(land, height, minPoint);
    }

    private int bfs(int[][] land, int height, Point minPoint) {
        int result = 0;
        boolean[][] visit = new boolean[n][n];
        PriorityQueue<Candidate> waiting = new PriorityQueue<>(Comparator.comparingInt(a -> a.diff));
        Queue<Point> q = new LinkedList<>();
        q.add(minPoint);
        visit[minPoint.r][minPoint.c] = true;
        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int d = 0; d < 4; d++) {
                int nextR = point.r + DR[d];
                int nextC = point.c + DC[d];
                if (canMove(nextR, nextC, visit)) {
                    Point nextPoint = new Point(nextR, nextC);
                    int diff = Math.abs(land[point.r][point.c] - land[nextR][nextC]);
                    if(diff <= height) {
                        visit[nextR][nextC] = true;
                        q.add(nextPoint);
                    } else {
                        waiting.add(new Candidate(nextPoint, diff));
                    }
                }
            }

            if(q.isEmpty()) {
                while (!waiting.isEmpty()) {
                    Candidate candidate = waiting.poll();
                    Point next = candidate.point;
                    if(visit[next.r][next.c]) {
                        continue;
                    }
                    q.add(next);
                    visit[next.r][next.c] = true;
                    result += candidate.diff;
                    break;
                }
            }
        }
        return result;
    }

    private boolean canMove(int r, int c, boolean[][] visit) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return false;
        }
        if (visit[r][c]) {
            return false;
        }
        return true;
    }
}
