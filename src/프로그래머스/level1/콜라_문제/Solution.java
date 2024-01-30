package 프로그래머스.level1.콜라_문제;

public class Solution {

    public static void main(String[] args) {
        int a = 3;
        int b = 1;
        int n = 20;
        int result = new Solution().solution(a, b, n);
        System.out.println(result);
    }

    public int solution(int a, int b, int n) {
        int result = 0;
        int bottle = n;
        while (bottle / a > 0) {
            int cola = bottle / a * b;
            bottle = bottle % a + cola;
            result += cola;
        }
        return result;
    }
}
