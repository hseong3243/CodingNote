package 프로그래머스.level2.땅따먹기;

public class Solution {

    public static void main(String[] args) {
        int[][] land = new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        int result = new Solution().solution(land);
        System.out.println(result);
    }

    int solution(int[][] land) {
        int row = land.length;
        int col = 4;
        int[][] sum = new int[row][col];
        for (int i = 0; i < 4; i++) {
            sum[0][i] = land[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < col; k++) {
                    if (j != k) {
                        sum[i][j] = Math.max(sum[i][j], sum[i - 1][k] + land[i][j]);
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < col; i++) {
            result = Math.max(result, sum[row - 1][i]);
        }
        return result;
    }
}
