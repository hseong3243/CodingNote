package 프로그래머스.level3.백10_옮기기;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        String[] s = {"1110", "100111100", "0111111010"};
        String[] result = new Solution().solution(s);
        for (String string : result) {
            System.out.println(string);
        }
    }

    public String[] solution(String[] s) {
        String[] result = new String[s.length];
        int idx = 0;
        for (String string : s) {
            result[idx++] = find(string);
        }
        return result;
    }

    private String find(String string) {
        char[] arr = string.toCharArray();
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (char c : arr) {
            if (c == '0' && stack.size() >= 2) {
                Character b = stack.pop();
                Character a = stack.pop();
                if (a == '1' && b == '1' && c == '0') {
                    count++;
                    continue;
                } else {
                    stack.push(a);
                    stack.push(b);
                }
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        while (count > 0) {
            boolean append = false;
            for (int i = sb.length() - 1; i >= 0; i--) {
                if(sb.charAt(i) == '0') {
                    if(i == sb.length() - 1) {
                        sb.append("110");
                    } else {
                        sb.insert(i+1, "110");
                    }
                    append = true;
                    break;
                }
            }
            if(!append) {
                sb.insert(0, "110");
            }
            count--;
        }
        return sb.toString();
    }
}
