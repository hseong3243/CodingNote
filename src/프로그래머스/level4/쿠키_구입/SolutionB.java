package 프로그래머스.level4.쿠키_구입;

public class SolutionB {

    public static void main(String[] args) {
        int[] cookie = {1, 1, 2, 3};
        int result = new SolutionB().solution(cookie);
        System.out.println(result);
    }

    public int solution(int[] cookie) {
        int result = 0;
        for (int i = 1; i < cookie.length; i++) {
            int left = i - 1;
            int right = i;

            int leftSum = cookie[left];
            int rightSum = cookie[right];
            while (left >= 0 && right < cookie.length) {
                if (leftSum == rightSum) {
                    result = Math.max(leftSum, result);
                    left--;
                    if (left >= 0) {
                        leftSum += cookie[left];
                    }
                } else if (leftSum < rightSum) {
                    left--;
                    if (left >= 0) {
                        leftSum += cookie[left];
                    }
                } else {
                    right++;
                    if (right < cookie.length) {
                        rightSum += cookie[right];
                    }
                }
            }
        }
        return result;
    }
}
