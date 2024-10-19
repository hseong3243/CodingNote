package 백준.gold.싸지방에_간_준하;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, arr);
    }

    private void solution(int n, int[][] arr) {
        Arrays.sort(arr, (a,b) -> {
            int startCompare = a[0] - b[0];
            int endCompare = a[1] - b[1];
            if(startCompare == 0) {
                return endCompare;
            }
            return startCompare;
        });

        int[] rooms = new int[n];
        Queue<Integer> emptyRoms = new PriorityQueue<>(Comparator.naturalOrder());
        for(int i=0; i<n; i++) {
            emptyRoms.add(i);
        }
        Queue<int[]> roomInfos = new PriorityQueue<>((a, b) -> {
            int roomNumberCompare = a[0] - b[0];
            int endCompare = a[1] - b[1];
            if (endCompare == 0) {
                return roomNumberCompare;
            }
            return endCompare;
        });
        for (int[] startToEnd : arr) {
            int start = startToEnd[0];
            while (!roomInfos.isEmpty() && roomInfos.peek()[1] <= start) {
                emptyRoms.add(roomInfos.poll()[0]);
            }
            int end = startToEnd[1];
            Integer roomNumber = emptyRoms.poll();
            rooms[roomNumber]++;
            roomInfos.add(new int[]{roomNumber, end});
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int room : rooms) {
            if(room == 0) {
                break;
            }
            count++;
            sb.append(room).append(" ");
        }
        System.out.println(count);
        System.out.println(sb);
    }
}
