package 프로그래머스.level2.카카오_프렌즈_컬러링북;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class Solution {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private boolean[][] visit;
    Map<Integer, Integer> areaMaxValue = new HashMap<>();

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = new int[][]{
            {1, 1, 1, 0},
            {1, 2, 2, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 3},
            {0, 0, 0, 3}};
        int[][] picture2 = new int[][]{
            {1, 1, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}};
        int[] result = new Solution().solution(m, n, picture);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visit = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(!visit[i][j] && picture[i][j] != 0) {
                    visit[i][j] = true;
                    int area = bfs(i, j, picture, picture[i][j]);
                    areaMaxValue.put(picture[i][j],
                        Math.max(areaMaxValue.getOrDefault(picture[i][j], 0), area));
                    numberOfArea++;
                }
            }
        }

        for (Entry<Integer, Integer> entry : areaMaxValue.entrySet()) {
            if(entry.getKey() != 0) {
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, entry.getValue());
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int row, int col, int[][] picture, int color) {
        int count = 1;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(row, col));
        while(!q.isEmpty()) {
            Point point = q.poll();
            for(int d=0; d<4; d++) {
                int nextRow = point.r + DR[d];
                int nextCol = point.c + DC[d];
                if(canMove(nextRow, nextCol, picture, color)) {
                    visit[nextRow][nextCol] = true;
                    q.add(new Point(nextRow, nextCol));
                    count++;
                }
            }
        }
        return count;
    }

    private boolean canMove(int row, int col, int[][] picture, int color) {
        int maxRow = picture.length;
        int maxCol = picture[0].length;
        if (row < 0 || row >= maxRow) {
            return false;
        }
        if (col < 0 || col >= maxCol) {
            return false;
        }
        if(visit[row][col]) {
            return false;
        }
        if(picture[row][col] != color) {
            return false;
        }
        return true;
    }
}
