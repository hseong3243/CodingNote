package 프로그래머스.level2.멀쩡한_사각형;

import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        int w = 8;
        int h = 12;
        long result = new Solution().solution(w, h);
        System.out.println(result);
    }

    public long solution(int w, int h) {
        int euclid = euclid(w, h);
        int remove = w / euclid + h / euclid - 1;
        return (long) w * h - (long) remove * euclid;
    }

    private int euclid(int a, int b) {
        if(b == 0) {
            return a;
        }
        return euclid(b, a % b);
    }
}
