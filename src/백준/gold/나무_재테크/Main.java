package 백준.gold.나무_재테크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][n + 1];
        int[][] arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }
        int[][] tree = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(n, m, k, map, arr, tree);

    }

    private static final int[] DR = {-1,-1,-1,0,0,1,1,1};
    private static final int[] DC = {-1,0,1,-1,1,-1,0,1};

    private int n;
    private int m;
    private int k;
    private int[][] map;
    private int[][] arr;
    private int[][] trees;

    private void solution(int n, int m, int k, int[][] map, int[][] arr, int[][] trees) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.map = map;
        this.arr = arr;
        this.trees = trees;

        Map<Integer, Map<Integer, Queue<Integer>>> treeMap = new HashMap<>();
        Map<Integer, Map<Integer, Queue<Integer>>> live = new HashMap<>();
        Map<Integer, Map<Integer, Queue<Integer>>> dead = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            treeMap.putIfAbsent(i, new HashMap<>());
            live.putIfAbsent(i, new HashMap<>());
            dead.putIfAbsent(i, new HashMap<>());
            for (int j = 1; j <= n; j++) {
                treeMap.get(i).putIfAbsent(j, new PriorityQueue<>());
                live.get(i).putIfAbsent(j, new LinkedList<>());
                dead.get(i).putIfAbsent(j, new LinkedList<>());
            }
        }
        for (int[] tree : trees) {
            treeMap.get(tree[0]).get(tree[1]).add(tree[2]);
        }


        int year = 1;
        while (year <= k) {
            year++;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    Queue<Integer> treeQ = treeMap.get(i).get(j);
                    Queue<Integer> deadQ = dead.get(i).get(j);
                    Queue<Integer> liveQ = live.get(i).get(j);
                    while (!treeQ.isEmpty()) {
                        Integer tree = treeQ.poll();
                        if (map[i][j] < tree) {
                            deadQ.add(tree);
                        } else {
                            map[i][j] -= tree;
                            liveQ.add(tree + 1);
                        }
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    Queue<Integer> deadQ = dead.get(i).get(j);
                    while (!deadQ.isEmpty()) {
                        Integer deadTree = deadQ.poll();
                        map[i][j] += deadTree / 2;
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    Queue<Integer> liveQ = live.get(i).get(j);
                    while (!liveQ.isEmpty()) {
                        Integer liveTree = liveQ.poll();
                        if (liveTree % 5 == 0) {
                            birth(i, j, treeMap);
                        }
                        treeMap.get(i).get(j).add(liveTree);
                    }

                    map[i][j] += arr[i][j];
                }
            }
        }
        int result = 0;
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                result += treeMap.get(i).get(j).size();
            }
        }
        System.out.println(result);
    }

    private void birth(int r, int c, Map<Integer, Map<Integer, Queue<Integer>>> treeMap) {
        for(int d=0; d<8; d++) {
            int nextR = r + DR[d];
            int nextC = c + DC[d];
            if(nextR < 1 || nextR > n || nextC < 1 || nextC > n) {
                continue;
            }
            treeMap.get(nextR).get(nextC).add(1);
        }
    }
}
