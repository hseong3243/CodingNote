package 백준.gold.반도체_설계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        new Main().solution(n, arr);
    }

    private void solution(int n, int[] arr) {
        int[] dp = new int[n];
        dp[0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > dp[len - 1]) {
                dp[len] = arr[i];
                len++;
            } else {
                int idx = Arrays.binarySearch(dp, 0, len, arr[i]);
                if (idx < 0) {
                    idx = - (idx + 1);
                }
                dp[idx] = arr[i];
            }
        }
        System.out.println(len);
    }
}
