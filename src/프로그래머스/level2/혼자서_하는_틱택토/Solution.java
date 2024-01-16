package 프로그래머스.level2.혼자서_하는_틱택토;

public class Solution {

    public static void main(String[] args) {
        String[] board = {"O.X", ".O.", "..X"};
        int result = new Solution().solution(board);
        System.out.println(result);
    }

    private char[][] map;

    public int solution(String[] board) {
        map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            map[i] = board[i].toCharArray();
        }
        int checkWin = countWin('X');
        int blankWin = countWin('O');
        int blankCount = 0;
        int checkCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'O') {
                    blankCount++;
                } else if (map[i][j] == 'X') {
                    checkCount++;
                }
            }
        }
        if (!((blankWin == 0 && checkWin == 0)
            || (blankWin == 1 && checkWin == 0)
            || (blankWin == 0 && checkWin == 1)
            || (blankWin == 2 && checkWin == 0))) {
            return 0;
        }
        int diff = blankCount - checkCount;
        if (!(diff == 0 || diff == 1)) {
            return 0;
        }
        if(blankWin == 2 && diff == 0) {
            return 0;
        }
        if(blankWin == 1 && diff == 0) {
            return 0;
        }
        if(checkWin == 1 && diff == 1) {
            return 0;
        }
        return 1;
    }

    private int countWin(char target) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count += right(i, 0, target);
            count += down(0, i, target);
        }
        count += rightDown(0, 0, target);
        count += leftDown(0, 2, target);
        return count;
    }

    private int right(int r, int c, char target) {
        if (c == 3) {
            return 1;
        }
        if (map[r][c] == target) {
            return right(r, c + 1, target);
        }
        return 0;
    }

    private int down(int r, int c, char target) {
        if (r == 3) {
            return 1;
        }
        if (map[r][c] == target) {
            return down(r + 1, c, target);
        }
        return 0;
    }

    private int rightDown(int r, int c, char target) {
        if (r == 3) {
            return 1;
        }
        if (map[r][c] == target) {
            return rightDown(r + 1, c + 1, target);
        }
        return 0;
    }

    private int leftDown(int r, int c, char target) {
        if (r == 3) {
            return 1;
        }
        if (map[r][c] == target) {
            return leftDown(r + 1, c - 1, target);
        }
        return 0;
    }
}
