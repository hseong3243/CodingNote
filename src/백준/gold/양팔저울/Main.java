package 백준.gold.양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];
        String[] split = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            weights[i] = Integer.parseInt(split[i]);
        }
        int m = Integer.parseInt(br.readLine());
        int[] beads = new int[m];
        split = br.readLine().split(" ");
        for(int i=0; i<m; i++) {
            beads[i] = Integer.parseInt(split[i]);
        }
        new Main().solution(n, weights, m, beads);
    }

    private int n;
    private int[] weights;
    private int m;
    private int[] beads;

    private void solution(int n, int[] weights, int m, int[] beads) {
        this.n = n;
        this.weights = weights;
        this.m = m;
        this.beads = beads;

        boolean[][] dp = new boolean[31][15001];
        Set<Integer> set = new HashSet<>();
        find(0, 0, set, dp);

        for (int bead : beads) {
            if(set.contains(bead)) {
                System.out.print("Y ");
            } else {
                System.out.print("N ");
            }
        }
    }

    private void find(int idx, int sum, Set<Integer> set, boolean[][] dp) {
        if(dp[idx][sum]) {
            return;
        }
        set.add(sum);
        dp[idx][sum] = true;
        if(idx >= n) {
            return;
        }
        find(idx + 1, sum + weights[idx], set, dp);
        find(idx + 1, sum, set, dp);
        find(idx + 1, Math.abs(sum - weights[idx]), set, dp);
    }
}
