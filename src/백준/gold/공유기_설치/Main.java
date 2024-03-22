package 백준.gold.공유기_설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        new Main().solution(n, c, arr);
    }

    private void solution(int n, int c, int[] arr) {
        int target = 0;
        Arrays.sort(arr);
        int max = 1000000000;
        int min = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            int result = getResult(c, arr, mid);
            if(result == -1) {
                max = mid - 1;
            } else {
                target = Math.max(target, result);
                min = mid + 1;
            }
        }
        System.out.println(target);
    }

    private int getResult(int c, int[] arr, int mid) {
        int result = Integer.MAX_VALUE;
        int count = 0;
        int prev = -1;
        for (int position : arr) {
            if(prev == -1) {
                prev = position;
                count++;
                continue;
            }
            if(position-prev >= mid) {
                result = Math.min(result, position - prev);
                prev = position;
                count++;
            }
        }
        if(count >= c) {
            return result;
        }
        return -1;
    }
}
