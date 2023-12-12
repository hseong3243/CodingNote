package 프로그래머스.level3.연속_펄스_부분_수열의_합;

public class Solution {

    public static void main(String[] args) {
        int[] sequence = new int[]{2, 3, -6, 1, 3, -1, 2, 4};
        long result = new Solution().solution(sequence);
        System.out.println(result);
    }

    public long solution(int[] sequence) {
        int n = sequence.length;
        int[] pulseA = new int[n];
        int[] pulseB = new int[n];

        int pulse = 1;
        for (int i = 0; i < n; i++) {
            pulseA[i] = sequence[i] * pulse;
            pulse *= -1;
            pulseB[i] = sequence[i] * pulse;
        }

        long max = Long.MIN_VALUE;
        if(n == 1) {
            return Math.max(pulseA[0], pulseB[0]);
        }
        long[] sumA = new long[n];
        long[] sumB = new long[n];
        sumA[0] = pulseA[0];
        sumB[0] = pulseB[0];
        for(int i=1; i<n; i++) {
            sumA[i] = Math.max(pulseA[i], sumA[i - 1] + pulseA[i]);
            sumB[i] = Math.max(pulseB[i], sumB[i - 1] + pulseB[i]);
            max = Math.max(max, sumA[i]);
            max = Math.max(max, sumB[i]);
        }

        return max;
    }
}
