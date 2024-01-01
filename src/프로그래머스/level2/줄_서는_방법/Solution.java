package 프로그래머스.level2.줄_서는_방법;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int n = 3;
        long k = 5;
        int[] result = new Solution().solution(n, k);
        for (int i : result) {
            System.out.print(i);
        }
    }

    public int[] solution(int n, long k) {
        long number = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            number *= i;
            list.add(i);
        }

        int[] result = new int[n];
        int count = 0;
        k--;
        for(int i=n; i > 0; i--) {
            number /= i;
            Integer remove = list.remove((int) (k / number));
            result[count++] = remove;
            k %= number;
        }

        return result;
    }
}
