package 프로그래머스.level3.스타_수열;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
//        int[] arr = {5, 2, 3, 3, 5, 3};
        int[] arr = {5, 2, 3, 2, 5, 5, 3, 4};
        int result = new Solution().solution(arr);
        System.out.println(result);
    }

    private int[] arr;

    public int solution(int[] a) {
        arr = a;
        return find() * 2;
    }

    private int find() {
        int[] count = new int[arr.length];
        for (int i : arr) {
            count[i]++;
        }
        int result = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if(count[i] <= result) {  // 현재까지의 스타 수열 개수보다 수의 개수가 적으면 시도하지 않는다.
                continue;
            }
            int len = 0;
            for(int j=0; j< arr.length-1; j++) {
                if(len == count[i]) {
                    break;
                }
                if(arr[j] == arr[j+1]) {  // 같으면 스타 수열일 수 없다.
                    continue;
                }
                if(arr[j] != i && arr[j+1] != i) {  // 타겟이 포함되어 있지 않으면 넘긴다.
                    continue;
                }
                len++;
                j++;
            }
            result = Math.max(result, len);
        }
        return result;
    }
}
