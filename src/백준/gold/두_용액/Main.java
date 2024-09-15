package 백준.gold.두_용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        new Main().solution(n, arr);
    }

    private void solution(int n, int[] arr) {
        int targetA = arr[0];
        int targetB = arr[n - 1];
        int result = Integer.MAX_VALUE;

        Arrays.sort(arr);
        int begin = 0;
        int end = n-1;
        while (begin < end) {
            int sum = arr[begin] + arr[end];
            if(Math.abs(sum) < result) {
                result = Math.abs(sum);
                targetA = arr[begin];
                targetB = arr[end];
            }

            if(sum < 0) {
                begin++;
            } else if(sum == 0) {
                break;
            } else {
                end--;
            }
        }
        System.out.println(targetA + " " + targetB);
    }
}
