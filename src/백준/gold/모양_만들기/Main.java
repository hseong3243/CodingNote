package 백준.gold.모양_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
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

    static class Shape {
        private final int number;
        private int count = 0;

        public Shape(int number) {
            this.number = number;
        }

        public void increaseCount() {
            count++;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private void solution(int n, int m, int[][] map) {
        Shape[][] shapes = new Shape[n][m];
        boolean[][] visited = new boolean[n][m];
        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                Shape shape = new Shape(count++);
                searchShape(i, j, map, shapes, shape, visited);
            }
        }

        int result = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 1) {
                    continue;
                }
                Set<Shape> distinct = new HashSet<>();
                for(int d=0; d<4; d++) {
                    int r = i + DR[d];
                    int c = j + DC[d];
                    if(r < 0 || c < 0 || r >= n || c >= m) {
                        continue;
                    }
                    if (Objects.nonNull(shapes[r][c])) {
                        distinct.add(shapes[r][c]);
                    }
                }
                int sum = 0;
                for (Shape shape : distinct) {
                    sum += shape.count;
                }
                result = Math.max(result, sum + 1);
            }
        }
        System.out.println(result);

    }

    private void searchShape(int r, int c, int[][] map, Shape[][] shapes, Shape shape,
        boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            shape.increaseCount();
            shapes[point[0]][point[1]] = shape;
            for(int d=0; d<4; d++) {
                int nextR = point[0] + DR[d];
                int nextC = point[1] + DC[d];
                if (canNotMove(nextR, nextC, map, visited)) {
                    continue;
                }
                visited[nextR][nextC] = true;
                q.add(new int[]{nextR, nextC});
            }
        }
    }

    private boolean canNotMove(int r, int c, int[][] map, boolean[][] visited) {
        int n = map.length;
        int m = map[0].length;
        if(r < 0 || c < 0 || r >= n || c >= m) {
            return true;
        }
        if(map[r][c] == 0) {
            return true;
        }
        if(visited[r][c]) {
            return true;
        }
        return false;
    }
}
