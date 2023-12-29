package 백준.silver.이동하기;

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
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(map, n, m);
    }

    private static final int[] DR = {1, 0, 1};
    private static final int[] DC = {0, 1, 1};

    private void solution(int[][] map, int n, int m) {
        int[][] candy = new int[n][m];
        candy[0][0] = map[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 3; d++) {
                    int nextRow = i + DR[d];
                    int nextCol = j + DC[d];
                    if (canMove(nextRow, nextCol, n, m)) {
                        candy[nextRow][nextCol] =
                            Math.max(candy[nextRow][nextCol], candy[i][j] + map[nextRow][nextCol]);
                    }
                }
            }
        }
        System.out.println(candy[n-1][m-1]);
    }

    private boolean canMove(int nextRow, int nextCol, int n, int m) {
        if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
            return false;
        }
        return true;
    }
}
