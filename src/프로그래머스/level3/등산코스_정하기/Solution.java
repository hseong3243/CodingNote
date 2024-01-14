package 프로그래머스.level3.등산코스_정하기;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
//        int n = 6;
//        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3},
//            {4, 6, 1}, {5, 6, 1}};
//        int[] gates = {1, 3};
//        int[] summits = {5};
//        int n = 7;
//        int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
//        int[] gates = {1};
//        int[] summits = {2, 3, 4};
        int n = 7;
        int[][] paths = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1},
            {6, 7, 1}};
        int[] gates = {3, 7};
        int[] summits = {1, 5};
//        int n = 7;
//        int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
//        int[] gates = {1};
//        int[] summits = {2, 3, 4};
        int[] result = new Solution().solution(n, paths, gates, summits);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    static class Path {
        int begin;
        int end;
        int cost;

        public Path(int begin, int end, int cost) {
            this.begin = begin;
            this.end = end;
            this.cost = cost;
        }
    }

    private boolean[] isSummit;
    private int minIntensity = Integer.MAX_VALUE;
    private int targetSummit = 0;
    private Map<Integer, List<Path>> map = new HashMap<>();
    private int n;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int w = path[2];
            List<Path> childrenA = map.getOrDefault(a, new ArrayList<>());
            childrenA.add(new Path(a, b, w));
            map.put(a, childrenA);
            List<Path> childrenB = map.getOrDefault(b, new ArrayList<>());
            childrenB.add(new Path(b, a, w));
            map.put(b, childrenB);
        }
        isSummit = new boolean[n + 1];
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        find(gates);

        return new int[]{targetSummit, minIntensity};
    }

    private void find(int[] gates) {
        boolean[] visit = new boolean[n + 1];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int gate : gates) {
            q.add(new int[]{gate, 0});
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int start = point[0];
            int intensity = point[1];
            visit[start] = true;
            if(intensity > minIntensity) {
                continue;
            }
            if (isSummit[start]) {
                System.out.println(start + " " + intensity);
                if (intensity < minIntensity || (intensity == minIntensity
                    && start < targetSummit)) {
                    minIntensity = intensity;
                    targetSummit = start;
                }
                continue;
            }
            for (Path path : map.get(start)) {
                if (!visit[path.end]) {
                    q.add(new int[]{path.end, Math.max(intensity, path.cost)});
                }
            }
        }
    }
}
