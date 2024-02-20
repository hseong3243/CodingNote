package 백준.gold.K번째_수;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        new Main().solution(n, k);
    }

    private int n;

    private void solution(int n, int k) {
        this.n = n;
        int result = binary(k);
        System.out.println(result);
    }

    private int binary(int idx) {
        int min = 1;
        int max = 1000000000;
        while (min <= max) {
            int mid = (min + max) / 2;
            if(condition(mid, idx)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    private boolean condition(int mid, int idx) {
        long count = 0;
        for(int i=1; i<=n; i++) {
            count += Math.min(mid/i, n);
            if(mid / i == 0) {
                break;
            }
        }
        return count >= idx;
    }
}
