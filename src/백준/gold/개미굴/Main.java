package 백준.gold.개미굴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    private static class Cave {
        private final String[] foods;

        public Cave(String[] foods) {
            this.foods = foods;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Cave> caves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String[] food = new String[k];
            for (int j = 0; j < k; j++) {
                food[j] = st.nextToken();
            }
            caves.add(new Cave(food));
        }

        new Main().solution(n, caves);
    }

    private static class Node {

        private final String food;
        private final Map<String, Node> children = new TreeMap<>();

        public Node(String food) {
            this.food = food;
        }
    }

    private void solution(int n, List<Cave> caves) {
        Map<String, Node> root = new TreeMap<>();

        for (Cave cave : caves) {
            Node child = null;
            for (String food : cave.foods) {
                if (child == null) {
                    child = root.computeIfAbsent(food, key -> new Node(food));
                } else {
                    child = child.children.computeIfAbsent(food, key -> new Node(food));
                }
            }
        }

        print(root, 0);
    }

    private void print(Map<String, Node> children, int depth) {
        for (Node node : children.values()) {
            printWithDash(node.food, depth);
            print(node.children, depth + 1);
        }
    }

    private void printWithDash(String word, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2 * count; i++) {
            sb.append("-");
        }
        sb.append(word);
        System.out.println(sb);
    }
}
