package 백준.gold.트리의_높이와_너비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, map);
    }

    static class Node {

        int n;
        Node left;
        Node right;
        Node parent;
        int level = -1;
        int rightSize = 0;
        int leftSize = 0;
        int position = 0;

        public Node(int n) {
            this.n = n;
        }

        public void linkLeft(Node left) {
            this.left = left;
            left.parent = this;
        }

        public void linkRight(Node right) {
            this.right = right;
            right.parent = this;
        }

        public int findSize(int level) {
            this.level = level;
            if (Objects.nonNull(left)) {
                leftSize = left.findSize(level + 1);
            }
            if (Objects.nonNull(right)) {
                rightSize = right.findSize(level + 1);
            }
            return leftSize + rightSize + 1;
        }

        public void findPosition() {
            position = leftSize + 1;
            if(Objects.nonNull(left)) {
                left.findPositionLeft(position);
            }
            if(Objects.nonNull(right)) {
                right.findPositionRight(position);
            }
        }

        public void findPositionLeft(int parentPosition) {
            position = parentPosition - rightSize - 1;
            if(Objects.nonNull(left)) {
                left.findPositionLeft(position);
            }
            if(Objects.nonNull(right)) {
                right.findPositionRight(position);
            }
        }

        public void findPositionRight(int parentPosition) {
            position = parentPosition + leftSize + 1;
            if(Objects.nonNull(left)) {
                left.findPositionLeft(position);
            }
            if(Objects.nonNull(right)) {
                right.findPositionRight(position);
            }
        }
    }

    private void solution(int n, int[][] map) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        for (int[] link : map) {
            int parent = link[0];
            int left = link[1];
            int right = link[2];
            if (left != -1) {
                nodes.get(parent).linkLeft(nodes.get(left));
            }
            if (right != -1) {
                nodes.get(parent).linkRight(nodes.get(right));
            }
        }
        Node root = nodes.stream()
            .filter(node -> Objects.isNull(node.parent) && node.n != 0)
            .findFirst().get();

        root.findSize(1);
        root.findPosition();

        int maxLevel = 0;
        Map<Integer, Integer> minPositionGroupByLevel = new HashMap<>();
        Map<Integer, Integer> maxPositionGroupByLevel = new HashMap<>();
        for (Node node : nodes) {
            Integer minPosition = minPositionGroupByLevel.getOrDefault(node.level, 10000);
            Integer maxPosition = maxPositionGroupByLevel.getOrDefault(node.level, 0);
            minPositionGroupByLevel.put(node.level, Math.min(minPosition, node.position));
            maxPositionGroupByLevel.put(node.level, Math.max(maxPosition, node.position));
            maxLevel = Math.max(maxLevel, node.level);
        }

        int maxWidth = 0;
        int maxWidthLevel = 0;
        for(int level=1; level<=maxLevel; level++) {
            Integer minPosition = minPositionGroupByLevel.get(level);
            Integer maxPosition = maxPositionGroupByLevel.get(level);
            int width = maxPosition - minPosition + 1;
            if(width > maxWidth) {
                maxWidthLevel = level;
                maxWidth = width;
            }
        }

        System.out.println(maxWidthLevel + " " + maxWidth);
    }
}
