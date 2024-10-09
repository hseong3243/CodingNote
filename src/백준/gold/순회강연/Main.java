package 백준.gold.순회강연;

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
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, arr);
    }

    private void solution(int n, int[][] arr) {
        Queue<int[]> q = new PriorityQueue<>((a, b) -> {
            int valueCompare = b[0] - a[0];
            int dayCompare = a[1] - b[1];
            return valueCompare;
        });
        q.addAll(Arrays.asList(arr));
        int result = 0;
        boolean[] visit = new boolean[10001];
        while (!q.isEmpty()) {
            int[] pd = q.poll();
            int value = pd[0];
            int day = pd[1];
            while (day > 0) {
                if(!visit[day]) {
                    break;
                }
                day--;
            }
            if(day == 0) {
                continue;
            }
            visit[day] = true;
            result += value;
        }
        System.out.println(result);
    }
}
