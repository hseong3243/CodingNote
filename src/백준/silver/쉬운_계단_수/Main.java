package 백준.silver.쉬운_계단_수;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n + 1][10];
        for (int i = 1; i < 10; i++) {
            arr[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    arr[i][j + 1] += arr[i - 1][j] % 1000000000;
                } else if (j == 9) {
                    arr[i][j - 1] += arr[i - 1][j] % 1000000000;
                } else {
                    arr[i][j - 1] += arr[i - 1][j] % 1000000000;
                    arr[i][j + 1] += arr[i - 1][j] % 1000000000;
                }
            }
        }
        long result = 0;
        for (int i : arr[n]) {
            result += i % 1000000000;
        }
        System.out.println(result % 1000000000);
    }
}