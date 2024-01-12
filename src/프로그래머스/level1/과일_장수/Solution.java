package 프로그래머스.level1.과일_장수;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int k = 3;
        int m = 4;
        int[] score = {1, 2, 3, 1, 2, 3, 1};
        int result = new Solution().solution(k, m, score);
        System.out.println(result);
    }

    public int solution(int k, int m, int[] score) {
        List<Integer> sorted = Arrays.stream(score)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        int result = 0;
        for (int i = m - 1; i < sorted.size(); i += m) {
            result += sorted.get(i) * m;
        }

        return result;
    }
}
