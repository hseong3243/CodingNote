package 프로그래머스.level2.뒤에_있는_큰_수_찾기;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
//        int[] arr = {2, 3, 3, 5};
        int[] arr = {9, 1, 5, 3, 6, 2};
        int[] result = new Solution().solution(arr);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }

    public int[] solution(int[] numbers) {
        int n = numbers.length;

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.add(numbers[i]);
        }

        return result;
    }
}
