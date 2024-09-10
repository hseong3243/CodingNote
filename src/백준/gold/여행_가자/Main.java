package 백준.gold.여행_가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {

    private static class City {

        private int number;
        private Set<City> neighbors = new HashSet<>();

        public City(int number) {
            this.number = number;
            neighbors.add(this);
        }

        public void link(City city) {
            neighbors.add(city);
            city.neighbors.add(this);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(new City(i));
        }
        for(int i=0; i<n; i++) {
            City city = cities.get(i);
            String[] split = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                int target = Integer.parseInt(split[j]);
                if(target == 1) {
                    city.link(cities.get(j));
                }
            }
        }
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(s -> Integer.parseInt(s) - 1).toArray();
        new Main().solution(n, m, cities, arr);
    }

    private void solution(int n, int m, List<City> cities, int[] arr) {
        int idx = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(arr[idx++]);
        boolean[] visited = new boolean[n];
        while (!q.isEmpty()) {
            Integer cityNumber = q.poll();
            if(idx >= m) {
                break;
            }
            if(cityNumber == arr[idx]) {
                idx++;
                q.clear();
                visited = new boolean[n];
            }
            City city = cities.get(cityNumber);
            for (City neighbor : city.neighbors) {
                if(visited[neighbor.number]) {
                    continue;
                }
                visited[neighbor.number] = true;
                q.add(neighbor.number);
            }
        }

        if(idx >= m) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
