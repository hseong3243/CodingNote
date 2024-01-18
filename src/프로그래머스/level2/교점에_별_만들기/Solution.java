package 프로그래머스.level2.교점에_별_만들기;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
//        int[][] line = {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
        String[] result = new Solution().solution(line);
        for (String s : result) {
            System.out.println(s);
        }
    }

    static class Point {

        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private List<Point> points = new ArrayList<>();
    private long minY = Long.MAX_VALUE;
    private long minX = Long.MAX_VALUE;
    private long maxX = Long.MIN_VALUE;
    private long maxY = Long.MIN_VALUE;

    public String[] solution(int[][] line) {
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                findPoint(line[i], line[j]);
            }
        }

        int resultRow = (int) (maxY - minY + 1);
        int resultCol = (int) (maxX - minX + 1);
        StringBuilder[] sbArr = new StringBuilder[resultRow];
        for (int i = 0; i < resultRow; i++) {
            sbArr[i] = new StringBuilder(".".repeat(resultCol));
        }
        for (Point point : points) {
            sbArr[(int) (point.y - minY)].setCharAt((int) (point.x - minX), '*');
        }
        String[] result = new String[resultRow];
        for (int i = 0; i < resultRow; i++) {
            result[i] = sbArr[resultRow - i - 1].toString();
        }

        return result;
    }

    private void findPoint(int[] lineA, int[] lineB) {
        long a = lineA[0];
        long b = lineA[1];
        long e = lineA[2];
        long c = lineB[0];
        long d = lineB[1];
        long f = lineB[2];
        if (a * d - b * c == 0) {
            return;
        }
        if ((b * f - e * d) % (a * d - b * c) != 0) {
            return;
        }
        if ((e * c - a * f) % (a * d - b * c) != 0) {
            return;
        }
        long x = (b * f - e * d) / (a * d - b * c);
        long y = (e * c - a * f) / (a * d - b * c);
        points.add(new Point(x, y));
        minY = Math.min(minY, y);
        minX = Math.min(minX, x);
        maxY = Math.max(maxY, y);
        maxX = Math.max(maxX, x);
    }
}
