package 백준.gold.점수따먹기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] map = new int[n][m];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        new Main().solution(n, m, map);
    }

    private void solution(int n, int m, int[][] map) {
        int[][] result = new int[n+1][m+1];
        for(int r=1; r<=n; r++) {
            for(int c=1; c<=m; c++) {
                result[r][c] = result[r][c-1] + result[r-1][c] - result[r-1][c-1] + map[r-1][c-1];
            }
        }

        int sum = Integer.MIN_VALUE;
        for(int r2=1; r2<=n; r2++) {
            for(int c2=1; c2<=m; c2++) {
                for(int r1=1; r1<=r2; r1++) {
                    for(int c1=1; c1<=c2; c1++) {
                        sum = Math.max(sum,
                            result[r2][c2] - result[r2][c1 - 1] - result[r1 - 1][c2]
                                + result[r1 - 1][c1 - 1]);
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
