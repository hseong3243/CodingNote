package 백준.gold.톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] machine = new char[4][8];
        for(int i=0; i<4; i++) {
            machine[i] = br.readLine().toCharArray();
        }
        int k = Integer.parseInt(br.readLine());
        int[][] arr = new int[k][2];
        for(int i=0; i<k; i++) {
            String[] split = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
        }
        new Main(machine, k, arr).solution();
    }

    public Main(char[][] machine, int k, int[][] arr) {
        for(int i=0; i<4; i++) {
            this.machine[i] = new LinkedList<>();
            for (char c : machine[i]) {
                this.machine[i].add(c);
            }
        }
        this.k = k;
        this.arr = arr;
    }

    private List<Character>[] machine = new List[4];
    private int k;
    private int[][] arr;

    public void solution() {
        for (int[] command : arr) {
            int idx = command[0] - 1;
            int dir = command[1];

            turnLeftMachine(machine[idx].get(6), idx - 1, dir);
            turnRightMachine(machine[idx].get(2), idx + 1, dir);

            turnMachine(idx, dir);
        }

        int result = 0;
        for(int i=0; i<4; i++) {
            result += machine[i].get(0) == '1' ? (int) Math.pow(2, i) : 0;
        }
        System.out.println(result);
    }

    private void turnMachine(int idx, int dir) {
        List<Character> m = machine[idx];
        if(dir == 1) {
            Character removed = m.remove(7);
            m.add(0, removed);
        } else {
            Character removed = m.remove(0);
            m.add(removed);
        }
    }

    private void turnRightMachine(char prev, int idx, int dir) {
        dir = dir == 1 ? -1 : 1;
        if(idx >= 4) {
            return;
        }
        char left = machine[idx].get(6);
        if (prev == left) {
            return;
        }
        turnRightMachine(machine[idx].get(2), idx + 1, dir);
        turnMachine(idx, dir);
    }

    private void turnLeftMachine(char prev, int idx, int dir) {
        dir = dir == 1 ? -1 : 1;
        if(idx < 0) {
            return;
        }
        char right = machine[idx].get(2);
        if(prev == right) {
            return;
        }
        turnLeftMachine(machine[idx].get(6), idx - 1, dir);
        turnMachine(idx, dir);
    }
}
