package 백준.gold.제곱수_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        int result = new Main(n, m, arr).solution();
        System.out.println(result);
    }

    private static final int[] DR = {-1, 1, 0, 0, -1, 1, 1, -1};
    private static final int[] DC = {0, 0, -1, 1, 1, 1, -1, -1};

    private int n;
    private int m;
    private char[][] arr;
    private int result = -1;

    public Main(int n, int m, char[][] arr) {
        this.n = n;
        this.m = m;
        this.arr = arr;
    }

    private int solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance(i, j);
            }
        }
        if(n==1 && m==1) {
            check(arr[0][0] -'0');
        }
        return result;
    }

    private void distance(int r, int c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                direction(r, c, i, j);
            }
        }
    }

    private void direction(int r, int c, int dr, int dc) {
        for (int d = 0; d < 8; d++) {
            if(DR[d] * dr == 0 && DC[d] * dc == 0) {
                continue;
            }
            recursive(r, c, DR[d] * dr, DC[d] * dc, new StringBuilder());
        }
    }

    private void recursive(int r, int c, int plusR, int plusC, StringBuilder sb) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return;
        }
        sb.append(arr[r][c]);
        int number = Integer.parseInt(sb.toString());
        check(number);
        recursive(r + plusR, c + plusC, plusR, plusC, sb);
    }

    private void check(int number) {
        int sqrt = (int)Math.sqrt(number);
        if (sqrt*sqrt == number) {
            result = Math.max(result, number);
        }
    }
}
