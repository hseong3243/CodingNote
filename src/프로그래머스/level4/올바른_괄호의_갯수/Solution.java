package 프로그래머스.level4.올바른_괄호의_갯수;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int n = 4;
        int result = new Solution().solution(n);
        System.out.println(result);
    }

    private Set<String> result = new HashSet<>();

    public int solution(int n) {
        dfs(n, new StringBuilder(), 0, 0);
        System.out.println(result);
        return result.size();
    }

    private void dfs(int n, StringBuilder sb, int left, int right) {
        if (left + right == 2 * n) {
            result.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append("(");
            dfs(n, sb, left + 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left && right < n) {
            sb.append(")");
            dfs(n, sb, left, right + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
