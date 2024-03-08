package 프로그래머스.level3.지형_편집;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int[][] land = {{1, 2}, {2, 3}};
        int p = 3;
        int q = 2;
//        int[][] land = {{4, 4, 3}, {3, 2, 2}, {2, 1, 0}};
//        int p = 5;
//        int q = 3;
        long result = new Solution().solution(land, p, q);
        System.out.println(result);
    }

    public long solution(int[][] land, int P, int Q) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int[] line : land) {
            for (int point : line) {
                min = Math.min(min, point);
                max = Math.max(max, point);
            }
        }

        long result = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            long sum = sum(mid, land, P, Q);
            long sumA = sum(mid + 1, land, P, Q);
            long sumB = sum(mid - 1, land, P, Q);
            if (sumA < sum) {
                min = mid + 1;
            } else if (sumB < sum) {
                max = mid - 1;
            } else {
                result = sum;
                break;
            }
        }
        return result;
    }

    private long sum(int mid, int[][] land, int p, int q) {
        long result = 0;
        for (int[] line : land) {
            for (int point : line) {
                if (point > mid) {
                    result += (long) (point - mid) * q;
                }
                if (point < mid) {
                    result += (long) (mid - point) * p;
                }
            }
        }
        return result;
    }
}
