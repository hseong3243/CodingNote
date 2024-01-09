package 프로그래머스.level2.택배_배달과_수거하기;

public class Solution {

    public static void main(String[] args) {
        int n = 5;
        int cap = 4;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
//        int n = 7;
//        int cap = 2;
//        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
//        int[] pickups = {0, 2, 0, 1, 0, 2, 0};
        long result = new Solution().solution(cap, n, deliveries, pickups);
        System.out.println(result);
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long result = 0;
        int boxLastIdx = n - 1;
        int pickLastIdx = n - 1;
        while (boxLastIdx >= 0 && deliveries[boxLastIdx] == 0) {
            boxLastIdx--;
        }
        while (pickLastIdx >= 0 && pickups[pickLastIdx] == 0) {
            pickLastIdx--;
        }
        while (boxLastIdx >= 0 || pickLastIdx >= 0) {
            result += Math.max(boxLastIdx + 1, pickLastIdx + 1) * 2L;
            int box = cap;
            while (boxLastIdx >= 0) {
                int sum = box - deliveries[boxLastIdx];
                if (sum < 0) {
                    deliveries[boxLastIdx] -= box;
                    box = 0;
                    break;
                } else {
                    box -= deliveries[boxLastIdx];
                    deliveries[boxLastIdx] = 0;
                    boxLastIdx--;
                }
            }
            int pick = 0;
            while (pickLastIdx >= 0) {
                int sum = pick + pickups[pickLastIdx];
                if (sum <= cap) {
                    pick += pickups[pickLastIdx];
                    pickups[pickLastIdx] = 0;
                    pickLastIdx--;
                } else {
                    pickups[pickLastIdx] -= cap - pick;
                    pick = 0;
                    break;
                }
            }
        }

        return result;
    }
}
