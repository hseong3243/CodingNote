package 프로그래머스.level2.테이블_해시_함수;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int[][] data = new int[][]{{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}};
        int col = 2;
        int rowBegin = 2;
        int rowEnd = 3;
        int result = new Solution().solution(data, col, rowBegin, rowEnd);
        System.out.println(result);
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        col--;
        row_begin--;
        row_end--;
        int finalCol = col;
        List<int[]> sortedData = Arrays.stream(data).sorted((a, b) -> {
            if (a[finalCol] > b[finalCol]) {
                return 1;
            } else if (a[finalCol] == b[finalCol]) {
                return a[0] < b[0] ? 1 : -1;
            } else {
                return -1;
            }
        }).collect(Collectors.toList());

        List<Integer> sums = new ArrayList<>();
        for(int i=row_begin; i<=row_end; i++) {
            int finalI = i;
            int sum = Arrays.stream(sortedData.get(i))
                .reduce(0, (a, b) -> a + b%(finalI +1));
            sums.add(sum);
        }

        return sums.stream().reduce(0, (a, b) -> a^b);
    }
}
