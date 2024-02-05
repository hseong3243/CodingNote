package 백준.gold.괄호의_값;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        new Main().solution(input);
    }

    private void solution(String input) {
        int result = 0;
        char[] arr = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        int value = 1;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            switch (c) {
                case '(': {
                    stack.add(c);
                    value *= 2;
                    break;
                }
                case '[': {
                    stack.add(c);
                    value *= 3;
                    break;
                }
                case ')': {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        System.out.println(0);
                        return;
                    } else if (arr[i - 1] == '(') {
                        result += value;
                    }
                    stack.pop();
                    value /= 2;
                    break;
                }
                case ']': {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        System.out.println(0);
                        return;
                    } else if (arr[i - 1] == '[') {
                        result += value;
                    }
                    stack.pop();
                    value /= 3;
                    break;
                }
            }
        }
        if(!stack.isEmpty()) {
            System.out.println(0);
            return;
        }
        System.out.println(result);
    }
}
