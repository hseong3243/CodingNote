package 백준.silver.동전_0;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] coins = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }
        new Main().solution(n ,k, coins);
    }

    private void solution(int n, int k, int[] coins) {
        int result = 0;
        while(k != 0) {
            int maxCoin = getMaxCoin(k, coins);
            int sum = maxCoin * (k / maxCoin);
            result += k / maxCoin;
            k -= sum;
        }
        System.out.println(result);
    }

    private int getMaxCoin(int k, int[] coins) {
        int max = 1;
        for (int coin : coins) {
            if (coin > k) {
                break;
            }
            max = coin;
        }
        return max;
    }
}
