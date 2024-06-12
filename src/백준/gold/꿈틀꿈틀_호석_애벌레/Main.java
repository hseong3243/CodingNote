package 백준.gold.꿈틀꿈틀_호석_애벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, k, arr);
    }

    private void solution(int n, int k, int[] arr) {
        long[] dp = new long[n + 1];
        int start = 0;
        int end = 0;
        long accumulate = 0;
        while (end < n || accumulate >= k) {
            if(accumulate < k) {
                accumulate += arr[end++];
                dp[end] = Math.max(dp[end], dp[end - 1]);
            } else {
                dp[end] = Math.max(dp[end], dp[start] + accumulate-k);
                accumulate -= arr[start++];
            }
        }
        for (long l : dp) {
            System.out.print(l + " ");
        }

        System.out.println(dp[n]);
    }
}
