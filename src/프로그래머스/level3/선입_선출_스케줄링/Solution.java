package 프로그래머스.level3.선입_선출_스케줄링;

public class Solution {

    public static void main(String[] args) {
        int n = 6;
        int[] cores = {1, 2, 3};
//        int[] cores = {4, 5, 8};
        int result = new Solution().solution(n, cores);
        System.out.println(result);
    }

    public int solution(int n, int[] cores) {
        int time = getMinTime(n, cores);

        int prev = 0;
        for (int core : cores) {
            prev += (time - 1) / core + 1;
        }
        int result = 0;
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                prev++;
            }
            if (prev == n) {
                result = i + 1;
                break;
            }
        }

        return result;
    }

    private int getMinTime(int n, int[] cores) {
        long min = 1;
        long max = 2000000000;
        while (min <= max) {
            long time = (min + max) / 2;
            long powerSum = 0;
            for (int core : cores) {
                powerSum += time / core + 1;
            }
            if (powerSum >= n) {
                max = time - 1;
            } else {
                min = time + 1;
            }
        }
        return (int) min;
    }
}
