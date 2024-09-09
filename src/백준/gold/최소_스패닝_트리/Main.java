package 백준.gold.최소_스패닝_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static class Line {

        private int nodeA;
        private int nodeB;
        private int weight;

        public Line(int nodeA, int nodeB, int weight) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            split = br.readLine().split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            int weight = Integer.parseInt(split[2]);
            lines.add(new Line(start, end, weight));
        }
        new Main().solution(v, e, lines);
    }

    private int[] parent;

    private void solution(int v, int e, List<Line> lines) {
        lines.sort(Comparator.comparingInt(a -> a.weight));
        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }
        long result = 0;
        for (Line line : lines) {
            int rootA = find(line.nodeA);
            int rootB = find(line.nodeB);
            if (rootA == rootB) {
                continue;
            }
            result += line.weight;

            int min = Math.min(rootA, rootB);
            parent[rootA] = min;
            parent[rootB] = min;
        }

        System.out.println(result);
    }

    private int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }
}
