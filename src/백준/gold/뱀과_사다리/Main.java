package 백준.gold.뱀과_사다리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[] map = new int[101];

        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            map[x] = y;
        }
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int u = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);
            map[u] = v;
        }
        new Main().solution(n, m, map);
    }

    private void solution(int n, int m, int[] map) {
        int[] memo = new int[101];
        Arrays.fill(memo, Integer.MAX_VALUE);
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        q.add(new int[]{1, 0});
        int result = 0;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            for (int i = 1; i <= 6; i++) {
                int target = point[0] + i;
                int count = point[1] + 1;
                if(target >= 100) {
                    result = count;
                    break;
                }
                if(map[target] != 0) {
                    target = map[target];
                }
                if(memo[target] <= count) {
                    continue;
                }
                memo[target] = count;
                q.add(new int[]{target, count});
            }
            if(result != 0) {
                break;
            }
        }
        System.out.println(result);
    }
}
