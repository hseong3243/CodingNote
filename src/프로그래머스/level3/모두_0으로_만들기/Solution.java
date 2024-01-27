package 프로그래머스.level3.모두_0으로_만들기;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};
        long solution = new Solution().solution(a, edges);
        System.out.println(solution);
    }

    static class Node {
        int number;
        long weight;
        Set<Node> links = new HashSet<>();
        boolean visit = false;

        public Node(int number, long weight) {
            this.number = number;
            this.weight = weight;
        }

        public void link(Node node) {
            links.add(node);
            node.links.add(this);
        }

        public void visit() {
            visit = true;
        }

        public void unlink(Node node) {
            links.remove(node);
            node.links.remove(this);
        }
    }

    public long solution(int[] a, int[][] edges) {
        List<Node> nodes = new ArrayList<>();
        int idx = 0;
        long sum = 0;
        for (int weight : a) {
            nodes.add(new Node(idx++, weight));
            sum += weight;
        }
        if(sum != 0) {
            return -1;
        }
        if(a.length == 2) {
            return Math.abs(a[0]);
        }
        for (int[] edge : edges) {
            Node nodeA = nodes.get(edge[0]);
            Node nodeB = nodes.get(edge[1]);
            nodeA.link(nodeB);
        }
        return find(nodes);
    }

    private long find(List<Node> nodes) {
        List<Node> leafNodes = nodes.stream()
            .filter(node -> node.links.size() == 1)
            .collect(Collectors.toList());
        Queue<Node> q = new LinkedList<>(leafNodes);
        long result = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            node.visit();
            for (Node link : node.links) {
                if (!link.visit) {
                    link.weight += node.weight;
                    link.unlink(node);
                    result += Math.abs(node.weight);
                    if(link.links.size() == 1) {
                        q.add(link);
                    }
                    break;
                }
            }
        }
        return result;
    }
}
