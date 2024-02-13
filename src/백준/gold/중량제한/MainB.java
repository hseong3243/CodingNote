package 백준.gold.중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MainB {

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
        int result = new MainB().solution(n, m, arr, a, b);
        System.out.println(result);
    }

    private Map<Integer, List<int[]>> map = new HashMap<>();

    private int solution(int n, int m, int[][] arr, int a, int b) {
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
        return binary(a, b, n);
    }

    public int binary(int start, int end, int len) {
        int min = 1;
        int max = 1000000000;
        int result = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (find(start, end, mid, len)) {
                result = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }

    private boolean find(int start, int end, int limit, int len) {
        boolean[] visit = new boolean[len + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        visit[start] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            if(node[0] == end) {
                return true;
            }
            List<int[]> lines = map.get(node[0]);
            for (int[] line : lines) {
                if(!visit[line[1]] && line[2] >= limit) {
                    visit[line[1]] = true;
                    q.add(new int[]{line[1], Math.max(line[2], node[1])});
                }
            }
        }
        return false;
    }
}
