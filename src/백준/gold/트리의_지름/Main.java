package 백준.gold.트리의_지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n - 1][3];
        for (int i = 0; i < n - 1; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }
        new Main().solution(n, arr);
    }

    static class Node {

        int n;
        Set<Line> children = new HashSet<>();
        int max = 0;

        public Node(int n) {
            this.n = n;
        }

        public void add(Line line) {
            children.add(line);
        }

        public int findMaxLength() {
            return findMaxLength(this);
        }

        public int findMaxLength(Node parentNode) {
            Queue<Integer> weights = new PriorityQueue<>((a, b) -> b - a);
            for (Line child : children) {
                if(child.node.equals(parentNode)) {
                    continue;
                }
                weights.add(child.findMaxLength(this));
            }
            if (weights.isEmpty()) {
                return 0;
            } else {
                int maxA = weights.poll();
                int maxB = 0;
                if(!weights.isEmpty()) {
                    maxB = weights.poll();
                }
                max = maxA + maxB;
                return maxA;
            }
        }
    }

    static class Line {

        Node node;
        int weight;

        public Line(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public int findMaxLength(Node parentNode) {
            return node.findMaxLength(parentNode) + weight;
        }
    }

    private void solution(int n, int[][] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        for (int[] ints : arr) {
            int parent = ints[0];
            int child = ints[1];
            int weight = ints[2];
            Node parentNode = nodes.get(parent);
            Node childNode = nodes.get(child);
            parentNode.add(new Line(childNode, weight));
            childNode.add(new Line(parentNode, weight));
        }

        Node rootNode = nodes.get(1);
        rootNode.findMaxLength();
        int result = 0;
        for (Node node : nodes) {
            result = Math.max(result, node.max);
        }
        System.out.println(result);
    }
}
