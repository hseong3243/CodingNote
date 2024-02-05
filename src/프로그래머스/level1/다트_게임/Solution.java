package 프로그래머스.level1.다트_게임;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
//        String dart = "1S2D*3T";
        String dart = "1D2S#10S";
        int result = new Solution().solution(dart);
        System.out.println(result);
    }

    public int solution(String dartResult) {
        int n = 0;
        int idx = 0;
        int[] arr = new int[3];
        for (char c : dartResult.toCharArray()) {
            if (c - '0' >= 0 && c - '0' <= 9) {
                n = n * 10 + (c - '0');
            } else if (c == 'S' || c == 'D' || c == 'T') {
                switch (c) {
                    case 'S' -> arr[idx] = n;
                    case 'D' -> arr[idx] = n * n;
                    case 'T' -> arr[idx] = n * n * n;
                }
                n = 0;
                idx++;
            } else {
                if (c == '*') {
                    arr[idx-1] *= 2;
                    if(idx-2 >= 0) {
                        arr[idx-2] *= 2;
                    }
                } else if (c == '#') {
                    arr[idx - 1] *= -1;
                }
            }
        }
        return Arrays.stream(arr).sum();
    }
}
