package 백준.gold.상어_중학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, m, map);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n;
    private int m;
    private int[][] myMap;

    private void solution(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.myMap = map;
        int result = 0;

        while (true) {
            boolean[][] visited = new boolean[n][n];
            List<Queue<int[]>> groups = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (myMap[i][j] >= 1 && !visited[i][j]) {
                        Queue<int[]> group = find(i, j, visited);
                        if (group.size() > 1) {
                            groups.add(group);
                        }
                    }
                }
            }
            int[][] finalmyMap = myMap;
            Optional<Queue<int[]>> optionalTarget = groups.stream()
                .sorted((a,b) -> {
                    int sizeCompare = b.size() - a.size();
                    long rainbowA = a.stream().filter(arr -> finalmyMap[arr[0]][arr[1]] == 0).count();
                    long rainbowB = b.stream().filter(arr -> finalmyMap[arr[0]][arr[1]] == 0).count();
                    long rainbowCompare = rainbowB - rainbowA;
                    int[] pointA = a.peek();
                    int[] pointB = b.peek();
                    int colCompare = pointB[1] - pointA[1];
                    int rowCompare = pointB[0] - pointA[0];
                    if(sizeCompare == 0) {
                        if(rainbowCompare == 0) {
                            if(rowCompare == 0) {
                                return colCompare;
                            }
                            return rowCompare;
                        }
                        return Math.toIntExact(rainbowCompare);
                    }
                    return sizeCompare;
                })
                .findFirst();
            if (optionalTarget.isEmpty()) {
                System.out.println(result);
                return;
            }
            Queue<int[]> target = optionalTarget.get();
            result += target.size() * target.size();
            for (int[] point : target) {
                myMap[point[0]][point[1]] = Integer.MIN_VALUE;
            }

            power();
            myMap = rotate();
            power();
        }
    }

    private int[][] rotate() {
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int row = n-1-j;
                temp[row][i] = myMap[i][j];
            }
        }

        return temp;
    }

    private void power() {
        for(int c=0; c<n; c++) {
            for(int r=n-1; r>=0; r--) {
                if(myMap[r][c] != Integer.MIN_VALUE) {
                    continue;
                }
                for(int k=r-1; k>=0; k--) {
                    if(myMap[k][c] == -1) {
                        break;
                    }
                    if(myMap[k][c] >= 0) {
                        myMap[r][c] = myMap[k][c];
                        myMap[k][c] = Integer.MIN_VALUE;
                        break;
                    }
                }
            }
        }
    }

    private Queue<int[]> find(int row, int col, boolean[][] visited) {
        Queue<int[]> group = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        visited[row][col] = true;
        boolean[][] visit = new boolean[n][n];
        visit[row][col] = true;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            group.add(point);
            for (int d = 0; d < 4; d++) {
                int nextRow = point[0] + DR[d];
                int nextCol = point[1] + DC[d];
                if(isOutOfRange(nextRow, nextCol) || visit[nextRow][nextCol]) {
                    continue;
                }
                if(myMap[nextRow][nextCol] == myMap[row][col] || myMap[nextRow][nextCol] == 0) {
                    q.add(new int[]{nextRow, nextCol});
                    visit[nextRow][nextCol] = true;
                }
                if(myMap[nextRow][nextCol] == myMap[row][col]) {
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return group;
    }

    private boolean isOutOfRange(int row, int col) {
        if(row < 0 || row >= n || col < 0 || col >= n) {
            return true;
        }
        return false;
    }
}
