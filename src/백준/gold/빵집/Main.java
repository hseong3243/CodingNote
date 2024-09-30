package 백준.gold.빵집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }
        new Main().solution(r, c, map);
    }

    private static final int[] DR = {-1, 0, 1};

    private int r;
    private int c;
    private char[][] map;

    private void solution(int r, int c, char[][] map) {
        this.r = r;
        this.c = c;
        this.map = map;
        boolean[][] visit = new boolean[r][c];
        int result = 0;
        for(int i=0; i<r; i++) {
            if(dfs(i,0, visit)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private boolean dfs(int row, int col, boolean[][] visit) {
        if(col == c-1) {
            return true;
        }
        for(int d=0; d<3; d++) {
            int nextRow = row + DR[d];
            int nextCol = col + 1;
            if(canMove(nextRow) && canVisit(nextRow, nextCol, visit)) {
                visit[nextRow][nextCol] = true;
                if(dfs(nextRow, nextCol, visit)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMove(int row) {
        if (row < 0 || row >= r) {
            return false;
        }
        return true;
    }

    private boolean canVisit(int row, int col, boolean[][] visit) {
        if(map[row][col] == 'x') {
            return false;
        }
        if(visit[row][col]) {
            return false;
        }
        return true;
    }
}
