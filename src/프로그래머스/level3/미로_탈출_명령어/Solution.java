package 프로그래머스.level3.미로_탈출_명령어;

public class Solution {

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int x = 2;
        int y = 3;
        int r = 3;
        int c = 1;
        int k = 5;
        String result = new Solution().solution(n, m, x, y, r, c, k);
        System.out.println(result);
    }

    private static final int[] DR = {1, 0, 0, -1};
    private static final int[] DC = {0, -1, 1, 0};
    private static final char[] D = {'d', 'l', 'r', 'u'};  //사전순: d, l, r, u

    private int n;
    private int m;
    private int x;
    private int y;
    private int r;
    private int c;
    private int k;
    private String result = "";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;
        this.k = k;
        if ((k - (Math.abs(r - x) + Math.abs(c - y))) % 2 == 0) {
            dfs(x, y, new StringBuilder());
        }
        if (result.isBlank()) {
            return "impossible";
        }
        return result;
    }

    private void dfs(int row, int col, StringBuilder line) {
        if (!result.isBlank()) {
            return;
        }
        if (Math.abs(row - r) + Math.abs(col - c) > k - line.length()) {
            return;
        }
        if (line.length() >= k) {
            result = line.toString();
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextR = row + DR[i];
            int nextC = col + DC[i];
            if (canMove(nextR, nextC)) {
                line.append(D[i]);
                dfs(nextR, nextC, line);
                line.deleteCharAt(line.length() - 1);
            }
        }
    }

    private boolean canMove(int nextR, int nextC) {
        if (nextR <= 0 || nextC <= 0) {
            return false;
        }
        if (nextR > n || nextC > m) {
            return false;
        }
        return true;
    }
}
