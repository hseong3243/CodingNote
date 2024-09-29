package 백준.gold.보석_도둑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Stone {
        private int weight;
        private int value;

        public Stone(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Stone> stones = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            stones.add(new Stone(weight, value));
        }
        int[] max = new int[k];
        for (int i = 0; i < k; i++) {
            max[i] = Integer.parseInt(br.readLine());
        }
        new Main().solution(n, k, stones, max);
    }

    private void solution(int n, int k, List<Stone> stones, int[] max) {
        stones.sort(Comparator.comparingInt(stone -> stone.weight));
        Arrays.sort(max);

        long result = 0;
        Queue<Stone> q = new PriorityQueue<>((a, b) -> b.value - a.value);
        int stoneIdx = 0;
        for (int bagWeight : max) {
            while (stoneIdx < n && stones.get(stoneIdx).weight <= bagWeight) {
                q.add(stones.get(stoneIdx++));
            }

            if(q.isEmpty()) {
                continue;
            }
            result += q.poll().value;
        }
        System.out.println(result);
    }
}
