package 백준.gold.카드_정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        new Main().solution(n, arr);
    }

    private void solution(int n, int[] arr) {
        Queue<Integer> q = new PriorityQueue<>();
        int result = 0;
        for (int i : arr) {
            q.add(i);
        }
        while(!q.isEmpty()) {
            if(q.size() == 1) {
                break;
            }
            Integer a = q.poll();
            Integer b = q.poll();
            int sum = a + b;
            result += sum;
            q.add(sum);
        }
        System.out.println(result);
    }
}
