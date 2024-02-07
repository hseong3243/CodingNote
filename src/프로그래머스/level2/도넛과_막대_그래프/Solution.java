package 프로그래머스.level2.도넛과_막대_그래프;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
//        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6},
//            {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[] result = new Solution().solution(edges);
        for (int i : result) {
            System.out.println(i);
        }
    }

    static class Node {

        int n;
        Set<Node> children = new HashSet<>();
        Set<Node> parents = new HashSet<>();

        public Node(int n) {
            this.n = n;
        }

        public void addChild(Node childNode) {
            children.add(childNode);
            childNode.parents.add(this);
        }

        public boolean isBar(boolean[] visit) {
            Queue<Node> q = new LinkedList<>();
            q.add(this);
            visit[n] = true;
            while (!q.isEmpty()) {
                Node node = q.poll();
                if(node.children.isEmpty()) {
                    return true;
                }
                for (Node child : node.children) {
                    if(visit[child.n]) {
                        return false;
                    }
                    visit[child.n] = true;
                    q.add(child);
                }
            }
            return true;
        }

        public boolean isEight(boolean[] visit) {
            Queue<Node> q = new LinkedList<>();
            q.add(this);
            visit[n] = true;
            while (!q.isEmpty()) {
                Node node = q.poll();
                if (node.parents.size() == 2 && node.children.size() == 2) {
                    return true;
                }
                for (Node child : node.children) {
                    if (!visit[child.n]) {
                        visit[child.n] = true;
                        q.add(child);
                    }
                }
            }
            return false;
        }
    }

    public int[] solution(int[][] edges) {
        Map<Integer, Node> nodes = new HashMap<>();
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            Node parentNode = nodes.getOrDefault(parent, new Node(parent));
            Node childNode;
            if (parent == child) {
                childNode = parentNode;
            } else {
                childNode = nodes.getOrDefault(child, new Node(child));
            }
            parentNode.addChild(childNode);
            nodes.put(parent, parentNode);
            nodes.put(child, childNode);
        }

        boolean[] barVisit = new boolean[1000001];
        boolean[] eightVisit = new boolean[1000001];
        Node root = findRoot(nodes);
        int doughnut = 0;
        int bar = 0;
        int eight = 0;
        for (Node child : root.children) {
            child.parents.remove(root);
            if (child.isBar(barVisit)) {
                bar++;
                continue;
            }
            if (child.isEight(eightVisit)) {
                eight++;
            } else {
                doughnut++;
            }
        }

        return new int[]{root.n, doughnut, bar, eight};
    }

    private Node findRoot(Map<Integer, Node> nodes) {
        for (Node node : nodes.values()) {
            if (node.parents.isEmpty() && node.children.size() > 1) {
                return node;
            }
        }
        throw new RuntimeException();
    }
}
