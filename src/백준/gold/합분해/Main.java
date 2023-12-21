package 백준.gold.합분해;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        new Main().solution(n, k);
    }

    public void solution(int n, int k) {
        int[][] arr = new int[k + 1][n + 1];
        Arrays.fill(arr[1], 1);
        for (int i = 2; i <= k; i++) {
            arr[i][1] = i;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                arr[i][j] = (arr[i][j - 1] + arr[i - 1][j]) % 1000000000;
            }
        }
        System.out.println(arr[k][n]);
    }
}
