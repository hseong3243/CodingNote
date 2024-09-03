package 백준.gold.전깃줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            lines.add(new Line(start, end));
        }
        new Main().solution(n, lines);
    }

    private void solution(int n, List<Line> lines) {
        lines.sort(Comparator.comparingInt(a -> a.start));

        int result = n;
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                Line now = lines.get(i);
                Line prev = lines.get(j);
                if(prev.end < now.end) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.min(result, n - dp[i]);
        }

        System.out.println(result);
    }
}
