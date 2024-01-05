package 프로그래머스.level2.두_원_사이의_정수_쌍;

public class Solution {

    public static void main(String[] args) {
        int r1 = 2;
        int r2 = 3;
        long result = new Solution().solution(r1, r2);
        System.out.println(result);
    }

    public long solution(int r1, int r2) {
        long result = 0;
        for (long i = 1; i <= r2; i++) {
            long maxPoint = (long) Math.sqrt((long) r2 * r2 - i * i);
            result += maxPoint + 1;
        }
        long minus = 0;
        for (long i = 1; i <= r1; i++) {
            long maxPoint = (long) Math.sqrt((long) r1 * r1 - i * i);
            if (maxPoint * maxPoint + i * i == (long) r1 * r1) {
                maxPoint -= 1;
            }
            minus += maxPoint + 1;
        }

        return 4 * (result - minus);
    }
}
