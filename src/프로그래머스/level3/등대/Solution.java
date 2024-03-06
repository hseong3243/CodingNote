package 프로그래머스.level3.등대;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
//        int n = 8;
//        int[][] arr = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        int n = 10;
        int[][] arr = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
        int result = new Solution().solution(n, arr);
        System.out.println(result);
    }

    static class Node {
        private final int n;
        private final Set<Node> links = new HashSet<>();

        public Node(int n) {
            this.n = n;
        }

        public void link(Node node) {
            links.add(node);
            node.links.add(this);
        }

        public Result findLeaf() {
            if(links.isEmpty()) {
                return new Result(1, 0);
            }
            int currentChoice = 1;
            int currentNotChoice = 0;
            for (Node child : links) {
                child.links.remove(this);
                Result prevResult = child.findLeaf();
                currentChoice += Math.min(prevResult.choice, prevResult.notChoice);
                currentNotChoice += prevResult.choice;
            }
            return new Result(currentChoice, currentNotChoice);
        }
    }

    static class Result {
        private final int choice;
        private final int notChoice;

        public Result(int choice, int notChoice) {
            this.choice = choice;
            this.notChoice = notChoice;
        }
    }

    public int solution(int n, int[][] lighthouse) {
        List<Node> nodes = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            nodes.add(new Node(i));
        }
        for (int[] ints : lighthouse) {
            Node nodeA = nodes.get(ints[0]);
            Node nodeB = nodes.get(ints[1]);
            nodeA.link(nodeB);
        }
        Node root = nodes.get(1);
        Result result = root.findLeaf();
        return Math.min(result.choice, result.notChoice);
    }
}
