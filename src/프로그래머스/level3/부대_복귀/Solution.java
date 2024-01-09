package 프로그래머스.level3.부대_복귀;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
//        int n = 3;
//        int[][] roads = {{1, 2}, {2, 3}};
//        int[] sources = {2, 3};
//        int destination = 1;
        int n = 5;
        int[][] roads = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] sources = {1, 3, 5};
        int destination = 5;
        int[] result = new Solution().solution(n, roads, sources, destination);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }

    static class Node {

        int number;
        List<Node> links = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }

        public void add(Node node) {
            links.add(node);
            node.links.add(this);
        }
    }


    private int n;
    private int[][] roads;
    private int[] sources;
    private int destination;
    private List<Node> nodes = new ArrayList<>();

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        this.n = n;
        this.roads = roads;
        this.sources = sources;
        this.destination = destination;
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        for (int[] road : roads) {
            Node nodeA = nodes.get(road[0]);
            Node nodeB = nodes.get(road[1]);
            nodeA.add(nodeB);
        }
        int[] map = find();
        int[] result = new int[sources.length];
        for(int i=0; i<sources.length; i++) {
            result[i] = map[sources[i]];
        }
        return result;
    }

    private int[] find() {
        int[] result = new int[n+1];
        boolean[] visit = new boolean[n+1];
        Arrays.fill(result, -1);
        Node root = nodes.get(destination);
        result[destination] = 0;
        visit[destination] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node link : node.links) {
                if (visit[link.number]) {
                    continue;
                }
                result[link.number] = result[node.number] + 1;
                q.add(link);
                visit[link.number] = true;
            }
        }
        return result;
    }
}
