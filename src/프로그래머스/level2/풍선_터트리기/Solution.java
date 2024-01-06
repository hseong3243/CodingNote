package 프로그래머스.level2.풍선_터트리기;

public class Solution {

    public static void main(String[] args) {
        int[] arr = {-16,27,65,-2,58,-92,-71,-68,-61,-33};
        int result = new Solution().solution(arr);
        System.out.println(result);
    }

    public static final int MAX = 1000000000;

    public int solution(int[] a) {
        int min = MAX;
        int minIdx = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
                minIdx = i;
            }
        }
        int minLeft = MAX;
        int result = 0;
        for (int i = 0; i < minIdx; i++) {
            if (a[i] < minLeft) {
                minLeft = a[i];
                result++;
            }
        }
        int minRight = MAX;
        for (int i = a.length - 1; i > minIdx; i--) {
            if (a[i] < minRight) {
                minRight = a[i];
                result++;
            }
        }

        return result + 1;
    }
}
