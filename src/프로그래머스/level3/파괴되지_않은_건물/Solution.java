package 프로그래머스.level3.파괴되지_않은_건물;

public class Solution {

    public static void main(String[] args) {
//        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
//        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
        int result = new Solution().solution(board, skill);
        System.out.println(result);
    }

    private int n;
    private int m;

    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        int[][] map = new int[n + 1][m + 1];

        for (int[] arr : skill) {
            int type = arr[0];
            int r1 = arr[1];
            int c1 = arr[2];
            int r2 = arr[3];
            int c2 = arr[4];
            int degree = arr[5];
            if (type == 1) {
                map[r1][c1] += -1 * degree;
                map[r2 + 1][c2 + 1] += -1 * degree;
                map[r2 + 1][c1] += degree;
                map[r1][c2 + 1] += degree;
            } else {
                map[r1][c1] += degree;
                map[r2 + 1][c2 + 1] += degree;
                map[r2 + 1][c1] += -1 * degree;
                map[r1][c2 + 1] += -1 * degree;
            }
        }
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=0; j<m; j++) {
                map[i][j] += sum;
                sum = map[i][j];
                if(i != 0) {
                    map[i][j] += map[i-1][j];
                }
            }
        }
        int result = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                board[i][j] += map[i][j];
                if(board[i][j]> 0) {
                    result++;
                }
            }
        }

        return result;
    }
}
