package 프로그래머스.level2.삼_x_n_타일링;

public class Solution {

    public static void main(String[] args) {
        int n = 8;
        int result = new Solution().solution(n);
        System.out.println(result);
    }

    public int solution(int n) {
        long[] arr = new long[n + 1];
        arr[2] = 3;
        arr[4] = 11;
        for(int i=6; i<=n; i += 2) {
            arr[i] = 3*arr[i-2] + 2;
            for(int j=2; j<=i-4; j += 2) {
                arr[i] += 2 * arr[j];
            }
            arr[i] %= 1000000007;
        }
        return (int) arr[n];
    }
}
