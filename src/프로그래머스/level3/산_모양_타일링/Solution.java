package 프로그래머스.level3.산_모양_타일링;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
//        int n = 2;
//        int[] tops = {0, 1};
        int n = 10;
        int[] tops = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int result = new Solution().solution(n, tops);
        System.out.println(result);
    }

    public int solution(int n, int[] tops) {
        int[] a = new int[n];
        int[] b = new int[n];
        if(tops[0] == 1) {
            a[0] = 3;
        } else {
            a[0] = 2;
        }
        b[0] = 1;
        for (int i = 1; i < n; i++) {
            if(tops[i] == 1) {
                a[i] = (3 * a[i - 1] + 2 * b[i - 1]) % 10007;
            } else {
                a[i] = (2 * a[i - 1] + b[i - 1]) % 10007;
            }
            b[i] = (a[i - 1] + b[i - 1]) % 10007;
        }

        return (a[n - 1] + b[n - 1]) % 10007;
    }
}
