package 프로그래머스.level2.프렌즈4블록;

public class Solution {

    private static final int[] DR = {0, 0, 1, 1};
    private static final int[] DC = {0, 1, 0, 1};
    private static final char BLANK = '0';

    public static void main(String[] args) {
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        int m = 6;
        int n = 6;
        int result = new Solution().solution(m, n, board);
        System.out.println(result);
    }

    private char[][] map;

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        boolean tryAgain = true;
        while (tryAgain) {
            tryAgain = false;
            boolean[][] boom = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isBoom(i, j, m, n)) {
                        answer += boom(i, j, boom);
                        tryAgain = true;
                    }
                }
            }

            sortBlock(boom, m, n);
        }

        return answer;
    }

    private boolean isBoom(int r, int c, int m, int n) {
        char block = map[r][c];
        for (int d = 1; d < 4; d++) {
            int targetRow = r + DR[d];
            int targetCol = c + DC[d];
            if (isNotTarget(targetRow, targetCol, m, n, block)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotTarget(int r, int c, int m, int n, char block) {
        return !isTarget(r, c, m, n, block);
    }

    private boolean isTarget(int r, int c, int m, int n, char block) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return false;
        }
        if (map[r][c] == BLANK) {
            return false;
        }
        return map[r][c] == block;
    }

    private int boom(int r, int c, boolean[][] boom) {
        int boomCount = 0;
        for (int d = 0; d < 4; d++) {
            int targetRow = r + DR[d];
            int targetCol = c + DC[d];
            if(!boom[targetRow][targetCol]) {
                boomCount++;
            }
            boom[targetRow][targetCol] = true;
        }
        return boomCount;
    }

    private void sortBlock(boolean[][] boom, int m, int n) {
        boolean[][] isSorted = new boolean[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                map[i][j] = input(isSorted, boom, i, j);
            }
        }
    }

    private char input(boolean[][] isSorted, boolean[][] boom, int r, int c) {
        for (int i=r; i >= 0; i--){
            if(map[i][c] == BLANK) {
                break;
            }
            if(!isSorted[i][c] && !boom[i][c]) {
                isSorted[i][c] = true;
                return map[i][c];
            }
        }
        return BLANK;
    }
}
