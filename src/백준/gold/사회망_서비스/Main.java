package 백준.gold.사회망_서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n-1][2];
        for(int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, arr);
    }

    private int[][] dp;

    private void solution(int n, int[][] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] link : arr) {
            List<Integer> friendA = map.getOrDefault(link[0], new ArrayList<>());
            List<Integer> friendB = map.getOrDefault(link[1], new ArrayList<>());
            friendA.add(link[1]);
            friendB.add(link[0]);
            map.put(link[0], friendA);
            map.put(link[1], friendB);
        }

        dp = new int[n + 1][2];
        dfs(1, -1, map);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private void dfs(int now, int parent, Map<Integer, List<Integer>> map) {
        List<Integer> node = map.get(now);
        for (Integer child : node) {
            if(child == parent) {
                continue;
            }
            dfs(child, now, map);
            dp[now][0] += Math.min(dp[child][0], dp[child][1]);
            dp[now][1] += dp[child][0];
        }
        dp[now][0] += 1;
    }
}
