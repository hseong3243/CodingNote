package 알고리즘.다익스트라;

import java.util.Arrays;
import java.util.Comparator;
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
        int start = 1;
        int target = 3;
        int n = 5;
        int[][] map = new int[][]{{1, 2, 10}, {1, 4, 31}, {1, 5, 17}, {2, 3, 2}, {2, 4, 5},
            {4, 2, 8}, {5, 4, 12}, {4, 3, 23}};
        new Solution().solution(start, n, map);
    }

    public void solution(int start, int n, int[][] map) {
        boolean[] visited = new boolean[n + 1];
        int[] fees = new int[n + 1];
        Arrays.fill(fees, Integer.MAX_VALUE);
        fees[start] = 0;
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(nodeA -> nodeA.fee));
        q.add(new Node(1, 0));
        while(!q.isEmpty()) {
            Node node = q.poll();
            if(visited[node.number]) {
                continue;
            }
            visited[node.number] = true;
            for (int[] arr : map) {
                int begin = arr[0];
                int end = arr[1];
                int fee = arr[2];
                if(begin == node.number && !visited[end]) {
                    q.add(new Node(end, node.fee + fee));
                    fees[end] = node.fee + fee;
                }
            }
        }
        for (int fee : fees) {
            System.out.println(fee);
        }
    }
}
