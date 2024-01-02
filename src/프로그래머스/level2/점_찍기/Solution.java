package 프로그래머스.level2.점_찍기;

public class Solution {

    public static void main(String[] args) {
        int k = 1;
        int d = 1000000;
        long result = new Solution().solution(k, d);
        System.out.println(result);
    }

    public long solution(int k, int d) {
        long result = 0;
        for (int a = 0; a <= d; a += k) {
            result += find(a, d, k);
        }
        return result;
    }

    private int find(int a, int d, int k) {
        int min = 0;
        int max = d;
        while (min <= max) {
            int b = (min + max) / 2;
            if (Math.pow(a, 2) + Math.pow(b * k, 2) > Math.pow(d, 2)) {
                max = b - 1;
            } else {
                min = b + 1;
            }
        }
        return min;
    }
}
