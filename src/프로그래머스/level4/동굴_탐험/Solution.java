package 프로그래머스.level4.동굴_탐험;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int n = 9;
        int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
        int[][] order = {{8,5},{6,7},{4,1}};
        boolean result = new Solution().solution(n, path, order);
        System.out.println(result);
    }

    static class Node {

        int idx;
        Set<Node> links = new HashSet<>();
        boolean visit = false;
        Node after;
        Node before;
        public Node(int idx) {
            this.idx = idx;
        }

        public void link(Node node) {
            links.add(node);
            node.links.add(this);
        }

        public void setAfter(Node node) {
            after = node;
            node.before = this;
        }

        public void visit() {
            this.visit = true;
        }

        public boolean canMove() {
            if(Objects.isNull(after)) {
                return true;
            }
            return after.visit;
        }
    }

    public boolean solution(int n, int[][] path, int[][] order) {
        List<Node> nodes = new ArrayList<>(n);
        for(int i=0; i<n; i++) {
            nodes.add(new Node(i));
        }
        for (int[] ints : path) {
            Node nodeA = nodes.get(ints[0]);
            Node nodeB = nodes.get(ints[1]);
            nodeA.link(nodeB);
        }
        for (int[] ints : order) {
            Node after = nodes.get(ints[0]);
            Node before = nodes.get(ints[1]);
            before.setAfter(after);
        }
        if(!nodes.get(0).canMove()) {
            return false;
        }
        return bfs(nodes);
    }

    private boolean bfs(List<Node> nodes) {
        Set<Node> canNotMove = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(nodes.get(0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            node.visit();
            canNotMove.remove(node);
            for (Node link : node.links) {
                if (link.visit) {
                    continue;
                }
                if (link.canMove()) {
                    link.links.remove(node);
                    q.add(link);
                } else {
                    canNotMove.add(link);
                }
            }
            if(Objects.nonNull(node.before) && canNotMove.contains(node.before)) {
                q.add(node.before);
            }
        }
        return canNotMove.isEmpty();
    }
}
