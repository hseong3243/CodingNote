package 백준.gold.택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        new Main().solution(n, c, m, arr);
    }

    private void solution(int n, int w, int m, int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int sum = 0;
        int[] town = new int[n];
        Arrays.fill(town, w);
        for (int[] post : arr) {
            int start = post[0] - 1;
            int end = post[1] - 1;
            int weight = post[2];

            int minWeight = weight;
            for (int i = start; i < end; i++) {
                minWeight = Math.min(minWeight, town[i]);
            }
            for (int i = start; i < end; i++) {
                town[i] -= minWeight;
            }

            sum += minWeight;
        }

        System.out.println(sum);
    }
}
