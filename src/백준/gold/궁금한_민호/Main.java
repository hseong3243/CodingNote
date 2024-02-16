package 백준.gold.궁금한_민호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, map);
    }

    private void solution(int n, int[][] map) {
        int result = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int link = isLink(i, j, n, map);
                if(link == -1) {
                    System.out.println(-1);
                    return;
                }
                result += link;
            }
        }
        System.out.println(result);
    }

    private int isLink(int start, int end, int n, int[][] map) {
        int result = 0;
        boolean[] visit = new boolean[n];
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> {
            if(a[2] == b[2]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        q.add(new int[]{start, 0, 0});
        q.add(new int[]{end, map[start][end], map[start][end]});
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int idx = node[0];
            int cost = node[2];
            if(idx == end) {
                if(cost != map[start][end]) {
                    result = -1;
                } else if(node[1] == map[start][end]) {
                    result = map[start][end];
                } else {
                    result = 0;
                }
                break;
            }
            if(visit[idx]) {
                continue;
            }
            visit[idx] = true;
            for(int i=0; i<n; i++) {
                if(visit[i]) {
                    continue;
                }
                q.add(new int[]{i, map[idx][i], cost + map[idx][i]});
            }
        }
        return result;
    }
}
