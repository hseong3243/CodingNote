package 백준.gold.내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, map);
    }

    private void solution(int n, int[][] map) {
        int[][] max = new int[n][3];
        int[][] min = new int[n][3];
        for (int i = 0; i < 3; i++) {
            max[0][i] = map[0][i];
            min[0][i] = map[0][i];
        }

        for (int i = 1; i < n; i++) {
            max[i][0] = map[i][0] + Math.max(max[i - 1][0], max[i - 1][1]);
            max[i][1] = map[i][1] + Math.max(max[i - 1][1], Math.max(max[i - 1][0], max[i - 1][2]));
            max[i][2] = map[i][2] + Math.max(max[i - 1][1], max[i - 1][2]);
        }

        for(int i = 1; i < n; i++) {
            min[i][0] = map[i][0] + Math.min(min[i - 1][0], min[i - 1][1]);
            min[i][1] = map[i][1] + Math.min(min[i-1][1], Math.min(min[i - 1][0], min[i - 1][2]));
            min[i][2] = map[i][2] + Math.min(min[i - 1][1], min[i - 1][2]);
        }

        int resultMin = Integer.MAX_VALUE;
        int resultMax = Integer.MIN_VALUE;
        for(int i=0; i<3; i++) {
            resultMax = Math.max(resultMax, max[n - 1][i]);
            resultMin = Math.min(resultMin, min[n - 1][i]);
        }
        System.out.println(resultMax + " " + resultMin);
    }
}
