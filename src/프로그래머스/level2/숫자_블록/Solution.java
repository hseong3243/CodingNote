package 프로그래머스.level2.숫자_블록;

import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
//        int begin = 1;
//        int end = 10;
        long begin = 999999990;
        long end = 1000000000;
        int[] result = new Solution().solution(begin, end);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] solution(long begin, long end) {
        System.out.println(find(20000000));
        int[] result = new int[(int) (end - begin + 1)];
        int idx = 0;
        for (long i = begin; i <= end; i++) {
            result[idx++] = find(i);
        }
        return result;
    }

    private int find(long n) {
        long result = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                result = i;
                if(n/i <= 10000000) {
                    return (int) (n / i);  // n/i 10,000,000 이하를 만족하면 바로 반환
                }
            }
        }
        if (n == 1) {
            return 0;
        }
        return (int) result;  // 나누어 떨어지는 것은 있지만 n/i이 10,000,000 이하를 만족하는 것이 없는 경우
    }
}
