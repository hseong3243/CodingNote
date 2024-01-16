package 프로그래머스.level3.표_편집;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
//        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        String result = new Solution().solution(n, k, cmd);
        System.out.println(result);
    }

    static class Node {

        int number;
        Node prev;
        Node next;
        boolean isDeleted = false;

        public Node(int number) {
            this.number = number;
        }

        public Node remove() {
            isDeleted = true;
            if (Objects.nonNull(prev)) {
                prev.next = next;
            }
            if (Objects.nonNull(next)) {
                next.prev = prev;
                return next;
            }
            return prev;
        }

        public void link() {
            isDeleted = false;
            if (Objects.nonNull(prev)) {
                prev.next = this;
            }
            if (Objects.nonNull(next)) {
                next.prev = this;
            }
        }
    }

    private List<Node> nodes = new ArrayList<>();
    private Node cursor;
    private Stack<Node> memory = new Stack<>();

    public String solution(int n, int k, String[] cmd) {
        Node prev = new Node(0);
        nodes.add(prev);
        for (int i = 1; i < n; i++) {
            Node current = new Node(i);
            prev.next = current;
            current.prev = prev;
            prev = current;
            nodes.add(current);
        }
        cursor = nodes.get(k);
        for (String s : cmd) {
            command(s.split(" "));
        }
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            if (node.isDeleted) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        return sb.toString();
    }

    private void command(String[] split) {
        switch (split[0]) {
            case "U" -> {
                int distance = Integer.parseInt(split[1]);
                for (int i = 0; i < distance; i++) {
                    cursor = cursor.prev;
                }
            }
            case "D" -> {
                int distance = Integer.parseInt(split[1]);
                for (int i = 0; i < distance; i++) {
                    cursor = cursor.next;
                }
            }
            case "C" -> {
                memory.add(cursor);
                cursor = cursor.remove();
            }
            case "Z" -> {
                Node recovery = memory.pop();
                recovery.link();
            }
        }
    }
}
