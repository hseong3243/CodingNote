package 백준.silver.점프_점프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        new Main(arr, n).solution();
    }

    private final int[] arr;
    private final int n;

    public Main(int[] arr, int n) {
        this.arr = arr;
        this.n = n;
    }

    private void solution() {
        int[] jump = new int[n];
        Arrays.fill(jump, Integer.MAX_VALUE);
        jump[0] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            Integer number = q.poll();
            for (int i = 1; i <= arr[number]; i++) {
                if (number + i >= n) {
                    continue;
                }
                if (jump[number + i] > jump[number] + 1) {
                    q.add(number + i);
                    jump[number + i] = jump[number] + 1;
                }
            }
        }
        if(jump[n-1] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(jump[n-1]);
    }
}
