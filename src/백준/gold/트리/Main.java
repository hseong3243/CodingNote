package 백준.gold.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] split = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        int remove = Integer.parseInt(br.readLine());
        new Main().solution(n, arr, remove);
    }

    static class Node {
        int n;
        private Node parent;
        Set<Node> children = new HashSet<>();

        public Node(int n) {
            this.n = n;
        }

        public void addChild(Node childeNode) {
            this.children.add(childeNode);
            childeNode.parent = this;
        }

        public void cut() {
            parent.children.remove(this);
        }

        public int count() {
            int result = 0;
            if(children.isEmpty()) {
                return 1;
            }
            for (Node child : children) {
                result += child.count();
            }
            return result;
        }
    }

    private void solution(int n, int[] arr, int remove) {
        List<Node> nodes = new ArrayList<>();
        for(int i=0; i<n; i++) {
            nodes.add(new Node(i));
        }
        Node root = null;
        for(int i=0; i<n; i++) {
            int parent = arr[i];
            if(parent == -1) {
                root = nodes.get(i);
            } else {
                Node parentNode = nodes.get(parent);
                Node childeNode = nodes.get(i);
                parentNode.addChild(childeNode);
            }
        }

        Node removedNode = nodes.get(remove);
        if(remove == root.n) {
            System.out.println(0);
        } else {
            removedNode.cut();
            System.out.println(root.count());
        }
    }
}
