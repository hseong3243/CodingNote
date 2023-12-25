package 백준.silver.회의실_배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(arr);
    }

    private void solution(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            if(a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        int result = 0;
        int time = 0;
        for (int[] times : arr) {
            int startTime = times[0];
            int endTime = times[1];
            if (startTime < time) {
                continue;
            }
            time = endTime;
            result++;
        }
        System.out.println(result);
    }
}
