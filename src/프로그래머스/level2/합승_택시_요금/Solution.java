package 프로그래머스.level2.합승_택시_요금;

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

    /**
     * @param n     지점의 개수
     * @param s     출발지점
     * @param a     A의 도착지점
     * @param b     B의 도착지점
     * @param fares 지점 사이의 예상 택시 요금
     * @return 최저 예상 택시 요금
     */
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int result = Integer.MAX_VALUE;

        List<List<int[]>> lines = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            lines.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            int begin = fare[0];
            int end = fare[1];
            int fee = fare[2];
            lines.get(begin).add(new int[]{begin, end, fee});
            lines.get(end).add(new int[]{end, begin, fee});
        }

        int[] startToStop = findOrigin(n, s, lines);
        for (int i = 1; i <= n; i++) {
            if (startToStop[i] == Integer.MAX_VALUE) {
                continue;
            }
            int[] stopToEnd = findOrigin(n, i, lines);
            result = Math.min(result,
                startToStop[i] + stopToEnd[a] + stopToEnd[b]);
        }

        return result;
    }

    private int[] findOrigin(int n, int start, List<List<int[]>> lines) {
        int[] fees = new int[n+1];
        Arrays.fill(fees, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.fee));
        q.add(new Node(start, 0));
        fees[start] = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (visited[node.number]) {
                continue;
            }
            visited[node.number] = true;
            for (int[] fare : lines.get(node.number)) {
                int target = fare[1];
                int fee = fare[2];
                if (fees[target] > node.fee + fee) {
                    q.add(new Node(target, node.fee + fee));
                    fees[target] = Math.min(fees[target], node.fee + fee);
                }
            }
        }
        return fees;
    }

    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24},
            {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int result = new Solution().solution(n, s, a, b, fares);
        System.out.println(result);
    }
}
