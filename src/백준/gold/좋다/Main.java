package 백준.gold.좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        int result = new Main().solution(n, arr);
        System.out.println(result);
    }

    public int solution(int n, long[] arr) {
        int result = 0;
        long[] sorted = Arrays.stream(arr).sorted().toArray();
        for(int i=0; i<n; i++) {
            int left = 0;
            int right = n-1;
            while(true) {
                if(left == i) {
                    left++;
                } else if(right == i) {
                    right--;
                }
                if(left >= right) {
                    break;
                }
                long sum = sorted[left] + sorted[right];
                if(sum > sorted[i]) {
                    right--;
                } else if(sum < sorted[i]) {
                    left++;
                } else {
                    result++;
                    break;
                }
            }
        }

        return result;
    }
}
