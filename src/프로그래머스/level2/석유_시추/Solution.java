package 프로그래머스.level2.석유_시추;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int result = new Solution().solution(land);
        System.out.println(result);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int m;
    private int[][] land;
    private int[] oils;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        this.land = land;
        oils = new int[m];
        boolean[][] visit = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (canMove(i, j, visit)) {
                    visit[i][j] = true;
                    bfs(i, j, visit);
                }
            }
        }
        int result = 0;
        for (int oil : oils) {
            result = Math.max(result, oil);
        }
        return result;
    }

    private void bfs(int r, int c, boolean[][] visit) {
        Set<Integer> cols = new HashSet<>();
        int oil = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] point = q.poll();
            oil++;
            cols.add(point[1]);
            for(int d=0; d<4; d++) {
                int nextR = point[0] + DR[d];
                int nextC = point[1] + DC[d];
                if(canMove(nextR, nextC, visit)) {
                    visit[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }
        for (Integer col : cols) {
            oils[col] += oil;
        }
    }

    private boolean canMove(int r, int c, boolean[][] visit) {
        if(r < 0 || c < 0 || r >= n || c >= m) {
            return false;
        }
        if(land[r][c] == 0) {
            return false;
        }
        if(visit[r][c]) {
            return false;
        }
        return true;
    }
}
