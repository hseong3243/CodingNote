package 백준.gold.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);
        int k = Integer.parseInt(br.readLine());
        int[][] arr = new int[e][3];
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(v, e, k, arr);
    }

    private void solution(int v, int e, int k, int[][] arr) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 1; i <= v; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] info : arr) {
            int start = info[0];
            int end = info[1];
            int cost = info[2];
            List<int[]> list = map.get(start);
            list.add(new int[]{end, cost});
            map.put(start, list);
        }

        int[] result = new int[v + 1];
        Arrays.fill(result, Integer.MAX_VALUE);

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{k, 0});
        result[k] = 0;
        while (!pq.isEmpty()) {
            int[] point = pq.poll();
            for (int[] info : map.get(point[0])) {
                int end = info[0];
                int cost = point[1] + info[1];
                if (result[end] < cost) {
                    continue;
                }
                result[end] = cost;
                pq.add(new int[]{end, cost});
            }
        }

        for(int i=1; i<result.length; i++) {
            if(result[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(result[i]);
            }
        }
    }
}
