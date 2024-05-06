package 백준.gold.파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int x = Integer.parseInt(split[2]);
        int[][] arr = new int[m][3];
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }
        new Main().solution(n, m, x, arr);
    }

    private int n;

    private void solution(int n, int m, int x, int[][] arr) {
        this.n = n;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] ints : arr) {
            int start = ints[0];
            int end = ints[1];
            int cost = ints[2];
            List<int[]> roads = map.get(start);
            roads.add(new int[]{end, cost});
        }

        int answer = 0;
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = find(i, x, map) + find(x, i, map);
            answer = Math.max(answer, result[i]);
        }
        System.out.println(answer);
    }

    private int find(int start, int target, Map<Integer, List<int[]>> map) {
        boolean[] visit = new boolean[n + 1];
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{start, 0});
        visit[start] = true;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            if (point[0] == target) {
                return point[1];
            }
            visit[point[0]] = true;
            List<int[]> lines = map.get(point[0]);
            for (int[] line : lines) {
                if (visit[line[0]]) {
                    continue;
                }
                q.add(new int[]{line[0], point[1] + line[1]});
            }
        }
        return -1;
    }
}
