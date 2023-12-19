package 프로그래머스.level3.징검다리_건너기;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
//        int[] stones = {3, 4, 5, 4, 5, 3};
        int k = 3;
        int result = new Solution().solution(stones, k);
        System.out.println(result);
    }

    public int solution(int[] stones, int k) {
        int answer = 0;

        int max = 2000000000;
        int min = 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (isCross(stones, k, mid)) {
                min = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    private boolean isCross(int[] stones, int k, int mid) {
        int distance = 0;
        for (int stone : stones) {
            if (stone < mid) {
                distance++;
            } else {
                distance = 0;
            }
            if (distance == k) {
                return false;
            }
        }
        return true;
    }
}
