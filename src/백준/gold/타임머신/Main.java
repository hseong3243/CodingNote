package 백준.gold.타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static class Line {

        private int source;
        private int target;
        private int weight;

        public Line(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            lines.add(new Line(a, b, weight));
        }
        new Main().solution(n, m, lines);
    }

    private void solution(int n, int m, List<Line> lines) {
        long[] arr = new long[n];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0] = 0;
        for (int i = 1; i < n; i++) {
            for (Line line : lines) {
                int source = line.source;
                int target = line.target;
                int weight = line.weight;
                if (arr[source] != Integer.MAX_VALUE && arr[target] > arr[source] + weight) {
                    arr[target] = arr[source] + weight;
                }
            }
        }

        for (Line line : lines) {
            int source = line.source;
            int target = line.target;
            int weight = line.weight;
            if (arr[source] != Integer.MAX_VALUE && arr[target] > arr[source] + weight) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(arr[i]);
            }
        }
    }
}
