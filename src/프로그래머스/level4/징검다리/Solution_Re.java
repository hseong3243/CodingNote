package 프로그래머스.level4.징검다리;

import java.util.Arrays;

public class Solution_Re {

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2,14,11,21,17};
        int n = 2;
        int result = new Solution_Re().solution(distance, rocks, n);
        System.out.println(result);
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int min = 1;
        int max = distance;
        while (min <= max) {
            int mid = (min + max) / 2;
            int move = 0;
            int count = n;
            for (int rock : rocks) {
                if(rock - move >= mid) {
                    move = rock;
                } else {
                    count--;
                }
            }
            if(distance - move < mid) {
                count--;
            }
            if(count < 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min - 1;
    }
}
