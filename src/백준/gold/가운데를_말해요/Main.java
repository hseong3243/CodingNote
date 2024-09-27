package 백준.gold.가운데를_말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> minQ = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> maxQ = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(i == 0) {
                minQ.add(num);
                sb.append(num).append("\n");
                continue;
            }
            if(num <= minQ.peek()) {
                minQ.add(num);
            } else {
                maxQ.add(num);
            }

            if(maxQ.size() > minQ.size()) {
                minQ.add(maxQ.poll());
            } else if(minQ.size() > maxQ.size() + 1) {
                maxQ.add(minQ.poll());
            }
            sb.append(minQ.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
