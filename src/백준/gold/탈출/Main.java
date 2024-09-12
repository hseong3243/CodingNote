package 백준.gold.탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int r = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);
        char[][] arr =new char[r][c];
        for(int i=0; i<r; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        new Main().solution(r,c,arr);
    }

    private static class Target {
        private int row;
        private int col;
        private char target;
        private int point;

        public Target(int row, int col, char target, int point) {
            this.row = row;
            this.col = col;
            this.target = target;
            this.point = point;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int r;
    private int c;
    private char[][] arr;

    private void solution(int r, int c, char[][] arr) {
        this.r = r;
        this.c = c;
        this.arr = arr;
        int[] goal = new int[2];
        boolean[][] water = new boolean[r][c];
        boolean[][] sonic = new boolean[r][c];
        Queue<Target> q = new LinkedList<>();
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(arr[i][j] =='*' ) {
                    q.add(new Target(i, j, '*', 0));
                    water[i][j] = true;
                }
                if(arr[i][j] == 'D') {
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(arr[i][j] == 'S') {
                    q.add(new Target(i, j, 'S', 0));
                    sonic[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Target target = q.poll();
            if(target.target == 'S' && arr[target.row][target.col] == 'D') {
                System.out.println(target.point);
                return;
            }

            for(int d=0; d<4;d ++) {
                int nextRow = target.row + DR[d];
                int nextCol = target.col + DC[d];
                if(canMove(nextRow, nextCol, water)) {
                    if(target.target == '*') {
                        if(arr[nextRow][nextCol] == 'D') {
                            continue;
                        }
                        water[nextRow][nextCol] = true;
                    }
                    if(target.target == 'S') {
                        if(sonic[nextRow][nextCol]) {
                            continue;
                        }
                        sonic[nextRow][nextCol] = true;
                    }
                    q.add(new Target(nextRow, nextCol, target.target, target.point + 1));
                }
            }
        }

        System.out.println("KAKTUS");
    }

    private boolean canMove(int row, int col, boolean[][] water) {
        if(row >= r || row < 0) {
            return false;
        }
        if(col >= c || col < 0) {
            return false;
        }
        if(water[row][col]) {
            return false;
        }
        if(arr[row][col] == 'X') {
            return false;
        }
        return true;
    }
}
