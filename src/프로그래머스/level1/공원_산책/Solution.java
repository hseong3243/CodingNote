package 프로그래머스.level1.공원_산책;

import java.util.Objects;

public class Solution {

    public static void main(String[] args) {
        String[] park = {"SOO", "OOO", "OOO"};
        String[] routes = {"E 2", "S 2", "W 1"};
        int[] result = new Solution().solution(park, routes);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private char[][] map;
    private int n;
    private int m;

    public int[] solution(String[] park, String[] routes) {
        this.n = park.length;
        this.m = park[0].length();
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = park[i].toCharArray();
        }
        Point point = findStartPoint();
        for (String route : routes) {
            String[] split = route.split(" ");
            String direction = split[0];
            int distance = Integer.parseInt(split[1]);
            int d = -1;
            switch (direction) {
                case "N" -> d = 0;
                case "S" -> d = 1;
                case "W" -> d = 2;
                case "E" -> d = 3;
            }
            int nextR = point.r + DR[d] * distance;
            int nextC = point.c + DC[d] * distance;
            if (canMove(nextR, nextC) && noBlock(point.r, point.c, nextR, nextC, d)) {
                point.r = nextR;
                point.c = nextC;
            }
        }
        return new int[]{point.r, point.c};
    }

    private Point findStartPoint() {
        Point startPoint = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    startPoint = new Point(i, j);
                    break;
                }
            }
            if (Objects.nonNull(startPoint)) {
                break;
            }
        }
        return startPoint;
    }

    private boolean canMove(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return false;
        }
        return true;
    }

    private boolean noBlock(int r, int c, int nextR, int nextC, int d) {
        if (map[r][c] == 'X') {
            return false;
        }
        if(r == nextR && c == nextC) {
            return true;
        }
        return noBlock(r + DR[d], c + DC[d], nextR, nextC, d);
    }
}
