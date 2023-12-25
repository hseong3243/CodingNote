package 백준.silver.기타_레슨;

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
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(arr, m);
    }

    private void solution(int[] arr, int m) {
        int min = Arrays.stream(arr).max().getAsInt();
        int max = Arrays.stream(arr).sum();
        while(min <= max) {
            int mid = (min + max) / 2;

            int sum = 0;
            int count = 0;
            for (int time : arr) {
                if(sum + time > mid) {
                    sum = 0;
                    count++;
                }
                sum += time;
            }
            if(sum > 0) {
                count++;
            }

            if(count <= m) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);
    }
}
