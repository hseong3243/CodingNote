package 프로그래머스.level2.가장_큰_정사각형_찾기;

public class Solution {

    public static void main(String[] args) {
        int[][] map = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        int result = new Solution().solution(map);
        System.out.println(result);
    }

    public int solution(int[][] board) {
        int result = 0;
        int n = board.length;
        int m = board[0].length;
        if(n <= 2 || m <= 2) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    result = Math.max(result, board[i][j]);
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == 1) {
                    board[i][j] =
                        Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1])) + 1;
                    result = Math.max(result, board[i][j]);
                }
            }
        }

        return (int) Math.pow(result, 2);
    }
}

