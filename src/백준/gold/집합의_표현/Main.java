package 백준.gold.집합의_표현;

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
        int[] arr = new int[n+1];
        for(int i=0; i<=n; i++) {
            arr[i] = i;
        }
        Main main = new Main();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            main.solution(arr, n, command, a, b);
        }
    }

    private void solution(int[] arr, int n, int command, int a, int b) {
        int parentA = find(arr, a);
        int parentB = find(arr, b);
        if(command == 0) {
            arr[parentA] = Math.min(parentA, parentB);
            arr[parentB] = Math.min(parentA, parentB);
        } else {
            if(parentA == parentB) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private int find(int[] arr, int num) {
        if(arr[num] == num) {
            return num;
        }
        return arr[num] = find(arr, arr[num]);
    }
}
