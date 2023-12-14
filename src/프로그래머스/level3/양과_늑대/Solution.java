package 프로그래머스.level3.양과_늑대;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static class Node {
        int number;
        int animal;
        List<Node> children = new ArrayList<>();

        public Node(int number, int animal) {
            this.number = number;
            this.animal = animal;
        }

        public void add(Node node) {
            children.add(node);
        }
    }

    private int result = 0;

    public static void main(String[] args) {
        int[] info = new int[]{0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11},
            {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        int result = new Solution().solution(info, edges);
        System.out.println(result);
    }

    public int solution(int[] info, int[][] edges) {
        List<Node> nodes = new ArrayList<>();
        for (int i=0; i<info.length; i++) {
            nodes.add(new Node(i, info[i]));
        }
        for (int[] edge : edges) {
            Node parent = nodes.get(edge[0]);
            Node child = nodes.get(edge[1]);
            parent.add(child);
        }
        Node root = nodes.get(0);
        List<Node> next = new ArrayList<>();
        next.add(root);
        dfs(next, root, 0, 0);

        return result;
    }

    private void dfs(List<Node> next, Node node, int sheep, int wolf) {
        if(node.animal == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if(wolf >= sheep) {
            return;
        }
        result = Math.max(result, sheep);

        List<Node> nextDfs = new ArrayList<>(next);
        nextDfs.remove(node);
        nextDfs.addAll(node.children);
        for (Node nextNode : nextDfs) {
            dfs(nextDfs, nextNode, sheep, wolf);
        }
    }
}
