package 프로그래머스.level3.순위;

public class Solution {

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int result = new Solution().solution(n, results);
        System.out.println(result);
    }

    public int solution(int n, int[][] results) {
        int[][] map = new int[n + 1][n + 1];
        for (int[] result : results) {
            int winner = result[0];
            int looser = result[1];
            map[winner][looser] = 1;
            map[looser][winner] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                        map[j][i] = -1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int winAndDefeat = 0;
            for (int j = 1; j <= n; j++) {
                if(map[i][j] != 0) {
                    winAndDefeat++;
                }
            }
            if(winAndDefeat == n-1) {
                result++;
            }
        }

        return result;
    }
}
