package 백준.gold.부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, s, arr);
    }

    private void solution(int n, int s, int[] arr) {
        int result = Integer.MAX_VALUE;
        int begin = 0;
        int end = 0;
        int sum = 0;
        while (end < n) {
            if(sum < s) {
                sum += arr[end];
                end++;
            } else {
                result = Math.min(result, end - begin);
                sum -= arr[begin];
                begin++;
            }
        }
        while (sum >= s) {
            result = Math.min(result, end - begin);
            sum -= arr[begin++];
        }

        if (result == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}
