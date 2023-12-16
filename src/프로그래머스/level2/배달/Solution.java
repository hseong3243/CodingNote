package 프로그래머스.level2.배달;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static class Node {
        int number;
        int fee;

        public Node(int number, int fee) {
            this.number = number;
            this.fee = fee;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] road = new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1},
            {5, 4, 2}};
        int k = 3;
        int result = new Solution().solution(n, road, k);
        System.out.println(result);
    }

    private int[][] map;
    private List<List<Integer>> roads = new ArrayList<>();

    public int solution(int N, int[][] road, int K) {
        for(int i=0; i<=N; i++) {
            roads.add(new ArrayList<>());
        }
        map = new int[N+1][N+1];
        for(int[] arr : map) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int[] arr : road) {
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];
            map[a][b] = Math.min(map[a][b], c);
            map[b][a] = Math.min(map[b][a], c);
            roads.get(a).add(b);
            roads.get(b).add(a);
        }
        int[] costs = findCosts();
        int result = 0;
        for (int cost : costs) {
            if(cost <= K) {
                result++;
            }
        }

        return result;
    }

    private int[] findCosts() {
        int[] minCosts = new int[roads.size()];
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        minCosts[1] = 0;
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.fee));
        q.add(new Node(1, 0));
        while(!q.isEmpty()) {
            Node begin = q.poll();
            for (int end : roads.get(begin.number)) {
                int nextFee = begin.fee + map[begin.number][end];
                if(nextFee < minCosts[end]) {
                    minCosts[end] = nextFee;
                    q.add(new Node(end, nextFee));
                }
            }
        }

        return minCosts;
    }
}
