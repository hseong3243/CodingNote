package 백준.gold.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        new Main().solution(n, arr);
    }

    private void solution(int n, int[] arr) {
        int maxLen = 0;
        int[] len = new int[n];
        for(int i=0; i<n; i++) {
            len[i] = 1;
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, len[i]);
        }
        System.out.println(n-maxLen);

    }
}
