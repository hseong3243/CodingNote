package 백준.gold.텀_프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        Main main = new Main();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken()) - 1;
            }
            int result = main.solution(n, arr);
            System.out.println(result);
        }
    }

    private int[] dp;
    private boolean[] visit;

    private int solution(int n, int[] students) {
        dp = new int[n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (i == students[i]) {
                dp[i] = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (dp[i] != 0) {
                continue;
            }
            dfs(i, students);
        }
        int result = 0;
        for (int i : dp) {
            if(i == -1) {
                result++;
            }
        }

        return result;
    }

    private int dfs(int idx, int[] students) {
        if (dp[idx] != 0) {
            return -1;
        }
        if (visit[idx]) {
            return idx;
        }
        visit[idx] = true;
        dp[idx] = dfs(students[idx], students);
        visit[idx] = false;
        if (dp[idx] == idx) {
            return -1;
        }
        return dp[idx];
    }
}
