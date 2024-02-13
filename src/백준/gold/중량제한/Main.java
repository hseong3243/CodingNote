package 백준.gold.중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] arr = new int[m][3];
        for(int i=0; i<m; i++) {
            split = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
            arr[i][2] = Integer.parseInt(split[2]);
        }
        split = br.readLine().split(" ");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        int result = new Main().solution(n, m, arr, a, b);
        System.out.println(result);
    }

    private int solution(int n, int m, int[][] arr, int a, int b) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] line : arr) {
            int start = line[0];
            int end = line[1];
            int weight = line[2];
            List<int[]> startToEnd = map.getOrDefault(start, new ArrayList<>());
            List<int[]> endToStart = map.getOrDefault(end, new ArrayList<>());
            startToEnd.add(new int[]{start, end, weight});
            endToStart.add(new int[]{end, start, weight});
            map.put(start, startToEnd);
            map.put(end, endToStart);
        }
        return findPath(map, a, b, n);
    }

    private int findPath(Map<Integer, List<int[]>> map, int start, int end, int len) {
        int[] visit = new int[len + 1];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        q.add(new int[]{start, Integer.MAX_VALUE});
        while (!q.isEmpty()) {
            int[] node = q.poll();
            if(visit[node[0]] > 0) {
                if(visit[node[0]] >= node[1]) {
                    continue;
                }
            }
            visit[node[0]] = Math.max(node[1], visit[node[0]]);
            if(node[0] == end) {
                break;
            }
            List<int[]> lines = map.get(node[0]);
            for (int[] line : lines) {
                q.add(new int[]{line[1], Math.min(node[1], line[2])});
            }
        }
        return visit[end];
    }
}
