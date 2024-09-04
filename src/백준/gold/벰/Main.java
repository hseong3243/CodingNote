package 백준.gold.벰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static class Direction {

        private int time;
        private String direction;

        public Direction(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] apples = new int[k][2];
        for (int i = 0; i < k; i++) {
            String[] split = br.readLine().split(" ");
            apples[i][0] = Integer.parseInt(split[0]) - 1;
            apples[i][1] = Integer.parseInt(split[1]) - 1;
        }
        int l = Integer.parseInt(br.readLine());
        Queue<Direction> directions = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            String[] split = br.readLine().split(" ");
            directions.add(new Direction(Integer.parseInt(split[0]), split[1]));
        }
        new Main().solution(n, k, apples, directions);
    }

    private static class Sneak {

        private Element head;
        private Element tail;
        private boolean[][] sneakMap;

        private Sneak(int n) {
            Element element = new Element(0, 0);
            head = element;
            tail = element;
            sneakMap = new boolean[n][n];
            sneakMap[0][0] = true;
        }
    }

    private static class Element {

        private Element next;
        private Element prev;
        private int row;
        private int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[] DR = {0, 1, 0, -1};
    private static final int[] DC = {1, 0, -1, 0};
    private int n;
    private int k;
    private int[][] apples;
    private Queue<Direction> directions;

    private void solution(int n, int k, int[][] apples, Queue<Direction> directions) {
        this.n = n;
        this.k = k;
        this.apples = apples;
        this.directions = directions;

        boolean[][] appleMap = new boolean[n][n];
        for (int[] apple : apples) {
            appleMap[apple[0]][apple[1]] = true;
        }

        int timer = 0;
        int d = 0;
        int row = 0;
        int col = 0;
        Sneak sneak = new Sneak(n);
        while (true) {
            row = row + DR[d];
            col = col + DC[d];
            timer++;
            if (canNotMove(row, col)) {
                break;
            }

            if (isConflictBody(sneak, row, col)) {
                break;
            }

            moveHead(sneak, row, col);
            if (!appleMap[row][col]) {
                moveTail(sneak);
            } else {
                appleMap[row][col] = false;
            }

            if (!directions.isEmpty() && directions.peek().time == timer) {
                Direction direction = directions.poll();
                if (direction.direction.equals("L")) {
                    d--;
                    if (d < 0) {
                        d = 3;
                    }
                } else {
                    d++;
                    if (d >= 4) {
                        d = 0;
                    }
                }
            }
        }

        System.out.println(timer);
    }

    private boolean canNotMove(int row, int col) {
        return row < 0 || row >= n || col < 0 || col >= n;
    }

    private boolean isConflictBody(Sneak sneak, int nextRow, int nextCol) {
        return sneak.sneakMap[nextRow][nextCol];
    }

    private void moveTail(Sneak sneak) {
        Element prevTail = sneak.tail;
        Element nextTail = prevTail.next;
        nextTail.prev = null;
        sneak.tail = nextTail;
        sneak.sneakMap[prevTail.row][prevTail.col] = false;
    }

    private void moveHead(Sneak sneak, int nextRow, int nextCol) {
        Element prevHead = sneak.head;
        Element nextHead = new Element(nextRow, nextCol);
        prevHead.next = nextHead;
        nextHead.prev = prevHead;
        sneak.head = nextHead;
        sneak.sneakMap[nextRow][nextCol] = true;
    }
}
