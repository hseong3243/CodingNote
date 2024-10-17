package 백준.gold.과제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n,arr);
    }

    private void solution(int n, int[][] arr) {
        boolean[] visit = new boolean[1001];
        Queue<int[]> q = new PriorityQueue<>((a, b) -> {
            int score = b[1] - a[1];
            int day = b[0] - a[0];
            if (score == 0) {
                return day;
            }
            return score;
        });
        q.addAll(Arrays.asList(arr));

        int result = 0;
        while (!q.isEmpty()) {
            int[] dw = q.poll();
            for(int i=dw[0]; i>0; i--) {
                if(visit[i]) {
                    continue;
                }
                visit[i] = true;
                result += dw[1];
                break;
            }
        }
        System.out.println(result);
    }
}
