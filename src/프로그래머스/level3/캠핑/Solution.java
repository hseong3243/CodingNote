package 프로그래머스.level3.캠핑;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
//        int n = 4;
//        int[][] data = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};
//        int[][] data = {{0, 2}, {1, 2}, {2, 0}, {2, 4}};
        int n = 7;
        int[][] data = {{0, 2}, {0, 3}, {1, 0}, {1, 4}, {2, 1}, {2, 3}, {2, 4}};
        int result = new Solution().solution(n, data);
        System.out.println(result);
    }

    public int solution(int n, int[][] data) {
        Arrays.sort(data, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] a = data[i];
            int nextRow = -1;
            int minCol = 0;
            int maxCol = Integer.MAX_VALUE;
            int prevMinCol = 0;
            int prevMaxCol = Integer.MAX_VALUE;
            for (int j = i + 1; j < n; j++) {
                int[] b = data[j];
                if (a[0] == b[0]) {
                    continue;
                }
                if(b[0] > nextRow) {
                    prevMinCol = minCol;
                    prevMaxCol = maxCol;
                    nextRow = b[0];
                }
                if (nextRow != -1) {
                    if (b[1] < a[1] && b[1] < prevMinCol) {
                        continue;
                    }
                    if (b[1] > a[1] && b[1] > prevMaxCol) {
                        continue;
                    }
                }

                if (b[1] < a[1]) {
                    minCol = Math.max(minCol, b[1]);
                } else if (b[1] == a[1]) {
                    continue;
                } else {
                    maxCol = Math.min(maxCol, b[1]);
                }
                result++;
            }
            System.out.println(a[0] + "," + a[1] + " -> " + result);
        }
        return result;
    }
}
