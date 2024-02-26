package 프로그래머스.level3.공_이동_시뮬레이션;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
//        int n = 2;
//        int m = 2;
//        int x = 0;
//        int y = 0;
//        int[][] queries = {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}};
        int n = 2;
        int m = 5;
        int x = 0;
        int y = 1;
        int[][] queries = {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}};
        long result = new Solution().solution(n, m, x, y, queries);
        System.out.println(result);
    }

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long minR = x;
        long minC = y;
        long maxR = x;
        long maxC = y;
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
            int command = query[0];
            int distance = query[1];
            if (command == 0) {  // 좌
                if (minC != 0) {
                    minC += distance;
                }
                maxC = Math.min(maxC + distance, m - 1);
            } else if (command == 1) {  // 우
                if (maxC != m - 1) {
                    maxC -= distance;
                }
                minC = Math.max(minC - distance, 0);
            } else if (command == 2) {  // 상
                if (minR != 0) {
                    minR += distance;
                }
                maxR = Math.min(maxR + distance, n - 1);
            } else {  // 하
                if (maxR != n - 1) {
                    maxR -= distance;
                }
                minR = Math.max(minR - distance, 0);
            }

            if (minR >= n || maxR < 0 || minC >= m || maxC < 0) {
                return 0;
            }
        }

        return (maxR - minR + 1) * (maxC - minC + 1);
    }
}
