package 백준.gold.토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        int h = Integer.parseInt(split[2]);
        int[][][] box = new int[h][n][m];
        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=0; k<m; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        new Main().solution(m,n,h, box);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static final int[] DH = {-1, 1};

    private int m;
    private int n;
    private int h;
    private int[][][] box;

    private void solution(int m, int n, int h, int[][][] box) {
        this.m = m;
        this.n = n;
        this.h = h;
        this.box = box;

        boolean isEnd = true;
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<m; k++) {
                    if(box[i][j][k] == 1) {
                        q.add(new int[]{i, j, k});
                    }
                    if(box[i][j][k] == 0) {
                        isEnd = false;
                    }
                }
            }
        }
        if(isEnd) {
            System.out.println(0);
            return;
        }

        int result = 0;
        List<int[]> list = new ArrayList<>();
        while (true) {
            list.clear();
            while (!q.isEmpty()) {
                int[] point = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nextR = point[1] + DR[d];
                    int nextC = point[2] + DC[d];
                    if (canMove(point[0], nextR, nextC)) {
                        box[point[0]][nextR][nextC] = 1;
                        list.add(new int[]{point[0], nextR, nextC});
                    }
                }
                for (int d = 0; d < 2; d++) {
                    int nextH = point[0] + DH[d];
                    if (canMove(nextH, point[1], point[2])) {
                        box[nextH][point[1]][point[2]] = 1;
                        list.add(new int[]{nextH, point[1], point[2]});
                    }
                }
            }

            if (list.isEmpty()) {
                break;
            }
            q.addAll(list);
            result++;
        }

        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<m; k++) {
                    if(box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(result);
    }

    private boolean canMove(int nextH, int nextR, int nextC) {
        if(nextR < 0 || nextC < 0 || nextR >= n || nextC >= m || nextH < 0 || nextH >= h) {
            return false;
        }
        if(box[nextH][nextR][nextC] != 0) {
            return false;
        }
        return true;
    }
}
