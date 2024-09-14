package 백준.gold.AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main main = new Main();

        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            String s = br.readLine();
            String substring = s.substring(1, s.length() - 1);
            String[] commands = substring.split(",");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(commands[i]);
            }
            main.solution2(p, n, arr);
        }
    }

    private void solution2(String p, int n, int[] arr) {
        boolean reverse = false;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i : arr) {
            dq.add(i);
        }

        for (char command : p.toCharArray()) {
            if(command == 'R') {
                reverse = !reverse;
            }
            if(command == 'D') {
                if(dq.isEmpty()) {
                    System.out.println("error");
                    return;
                }
                if(reverse) {
                    dq.removeLast();
                } else {
                    dq.removeFirst();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (reverse) {
            while (!dq.isEmpty()) {
                sb.append(dq.removeLast());
                if(dq.isEmpty()) {
                    break;
                }
                sb.append(",");
            }
        } else {
            while (!dq.isEmpty()) {
                sb.append(dq.removeFirst());
                if(dq.isEmpty()) {
                    break;
                }
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb);
    }

    private void solution(String p, int n, int[] arr) {
        boolean reverse = false;
        int begin = 0;
        int end = n;
        for (char command : p.toCharArray()) {
            if (command == 'R') {
                reverse = !reverse;
            }
            if (command == 'D') {
                if (reverse) {
                    end--;
                } else {
                    begin++;
                }
            }
            if (begin > end) {
                System.out.println("error");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (reverse) {
            for (int i = end-1; i >= begin; i--) {
                sb.append(arr[i]);
                if(i == begin) {
                    break;
                }
                sb.append(",");
            }
        } else {
            for (int i = begin; i < end; i++) {
                sb.append(arr[i]);
                if(i == end-1) {
                    break;
                }
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb);
    }
}
