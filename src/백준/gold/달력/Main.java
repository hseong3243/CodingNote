package 백준.gold.달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            String[] split = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
        }
        new Main(n, arr).solution();
    }

    private final int n;

    private final int[][] arr;
    public Main(int n, int[][] arr) {
        this.n = n;
        this.arr = arr;
    }

    private void solution() {
        Arrays.sort(arr, (a,b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        Queue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        int idx = 0;
        int start = Integer.MAX_VALUE;
        int end = 0;
        int height = 0;
        int sum = 0;
        for(int s=1; s<=365; s++) {
            while (!q.isEmpty()) {
                if(q.peek() < s) {
                    q.poll();
                } else {
                    break;
                }
            }

            while (idx < n) {
                if(arr[idx][0] > s) {
                    break;
                }
                q.add(arr[idx][1]);
                start = Math.min(start, arr[idx][0]);
                end = Math.max(end, arr[idx][1]);
                height = Math.max(height, q.size());
                idx++;
            }

            if(q.isEmpty()) {
                sum += (end - start + 1) * height;
                start = Integer.MAX_VALUE;
                end = 0;
                height = 0;
            }

            if(q.isEmpty() && idx >= n) {
                break;
            }
        }

        if(!q.isEmpty()) {
            sum += (end - start + 1) * height;
        }

        System.out.println(sum);
    }
}
