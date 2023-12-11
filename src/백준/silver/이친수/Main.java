package 백준.silver.이친수;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] arr = new long[n+1][2];
        arr[1][0] = 0;
        arr[1][1] = 1;
        for(int i=2; i<=n; i++) {
            arr[i][0] = arr[i-1][0] + arr[i-1][1];
            arr[i][1] = arr[i-1][0];
        }
        System.out.println(arr[n][0] + arr[n][1]);
    }
}
