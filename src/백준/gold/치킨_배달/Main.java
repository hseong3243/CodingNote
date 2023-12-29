package 백준.gold.치킨_배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
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
        new Main(map, n, m).solution();
    }

    public Main(int[][] map, int n, int m) {
        this.map = map;
        this.n = n;
        this.m = m;
    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public String toString() {
            return "Point{" +
                "r=" + r +
                ", c=" + c +
                '}';
        }
    }

    static class MyStore {

        Point point;
        int distance;

        public MyStore(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    private int[][] map;
    private int n;
    private int m;
    private Map<Point, List<MyStore>> myStores = new HashMap<>();
    private int result = Integer.MAX_VALUE;

    private void solution() {
        List<Point> houses = new ArrayList<>();
        List<Point> stores = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    houses.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    stores.add(new Point(i, j));
                }
            }
        }

        for (Point house : houses) {
            myStores.put(house, new ArrayList<>());
            for (Point store : stores) {
                int distance = Math.abs(store.r - house.r) + Math.abs(store.c - house.c);
                myStores.get(house).add(new MyStore(store, distance));
            }
        }

        removeAndFind(new boolean[stores.size()], 0, 0, stores);
        System.out.println(result);
    }

    private void removeAndFind(boolean[] remove, int idx, int count, List<Point> stores) {
        if(count == stores.size() - m) {
            int distance = getMinDistance(remove);
            result = Math.min(result, distance);
            return;
        }
        if(idx >= stores.size()) {
            return;
        }
        remove[idx] = true;
        removeAndFind(remove, idx + 1, count + 1, stores);
        remove[idx] = false;
        removeAndFind(remove, idx + 1, count, stores);
    }

    private int getMinDistance(boolean[] remove) {
        int minDistance = 0;
        for (Entry<Point, List<MyStore>> entry : myStores.entrySet()) {
            int distance = Integer.MAX_VALUE;
            List<MyStore> stores = entry.getValue();
            for(int i=0; i< stores.size(); i++) {
                if(!remove[i]) {
                    distance = Math.min(distance, stores.get(i).distance);
                }
            }
            minDistance += distance;
        }
        return minDistance;
    }
}
