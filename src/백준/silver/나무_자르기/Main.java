package 백준.silver.나무_자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, m, arr);
    }

    private void solution(int n, int m, int[] arr) {
        long result = 0;
        long min = 0;
        long max = 2_000_000_000;
        while (min <= max) {
            long sum = 0;
            long mid = (min + max) / 2;
            for (int tree : arr) {
                long mine = tree - mid;
                sum += mine > 0 ? mine : 0;
            }
            if(sum >= m) {
                min = mid + 1;
                result = Math.max(result, mid);
            } else {
                max = mid - 1;
            }
        }
        System.out.println(result);
    }
}
