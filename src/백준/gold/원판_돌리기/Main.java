package 백준.gold.원판_돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] command = new int[t][3];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                command[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        new Main().solution(n, m, t, arr, command);
    }

    private int n;
    private int m;

    private void solution(int n, int m, int t, int[][] arr, int[][] command) {
        this.n = n;
        this.m = m;

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new LinkedList<>());
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                list.get(i).add(arr[i - 1][j]);
            }
        }

        for (int[] cmd : command) {
            int select = cmd[0];
            int direction = cmd[1];
            int range = cmd[2];
            for (int i = select; i <= n; i += select) {
                List<Integer> selected = list.get(i);
                for (int r = 0; r < range; r++) {
                    if (direction == 0) {
                        Integer last = selected.remove(m - 1);
                        selected.add(0, last);
                    } else {
                        Integer first = selected.remove(0);
                        selected.add(first);
                    }
                }
            }

            boolean removed = false;
            boolean[][] remove = new boolean[n][m];
            for (int j = 0; j < m; j++) {
                for (int i = 1; i < n; i++) {
                    Integer a = list.get(i).get(j);
                    Integer b = list.get(i + 1).get(j);
                    if (a != 0 && Objects.equals(a, b)) {
                        removed = true;
                        remove[i - 1][j] = true;
                        remove[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m - 1; j++) {
                    Integer a = list.get(i + 1).get(j);
                    Integer b = list.get(i + 1).get(j + 1);
                    if (a == 0) {
                        continue;
                    }
                    if (Objects.equals(a, b)) {
                        removed = true;
                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                    }
                }
                Integer a = list.get(i + 1).get(0);
                Integer b = list.get(i + 1).get(m - 1);
                if (a == 0) {
                    continue;
                }
                if (Objects.equals(a, b)) {
                    removed = true;
                    remove[i][0] = true;
                    remove[i][m - 1] = true;
                }
            }

            int[] info = getInfo(list);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (remove[i][j]) {
                        list.get(i + 1).set(j, 0);
                    }
                    Integer target = list.get(i + 1).get(j);
                    if (target != 0 && !removed) {
                        double avg = (double) info[0] / info[1];
                        if (target < avg) {
                            list.get(i + 1).set(j, target + 1);
                        } else if (target > avg) {
                            list.get(i + 1).set(j, target - 1);
                        }
                    }
                }
            }
        }

        int sum = getInfo(list)[0];
        System.out.println(sum);
    }

    private int[] getInfo(List<List<Integer>> list) {
        int sum = 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                Integer number = list.get(i).get(j);
                if (number == 0) {
                    continue;
                }
                count++;
                sum += number;
            }
        }
        return new int[]{sum, count};
    }
}
