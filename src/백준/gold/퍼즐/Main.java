package 백준.gold.퍼즐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] puzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Main().solution(puzzle);
    }

    private static final int[][] CANDIDATE = {
        {2, 4},
        {1, 3, 5},
        {2, 6},
        {1, 5, 7},
        {2, 4, 6, 8},
        {3, 5, 9},
        {4, 8},
        {5, 7, 9},
        {6, 8}
    };


    private void solution(int[][] puzzle) {
        Map<String, Integer> puzzles = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        String origin = "123456780";
        puzzles.put(origin, 0);
        q.add(origin);
        while (!q.isEmpty()) {
            String now = q.poll();
            Integer cost = puzzles.get(now);
            int zeroIdx = now.indexOf('0');
            int[] next = CANDIDATE[zeroIdx];
            for (int idx : next) {
                char[] charArray = now.toCharArray();
                char number = charArray[idx-1];
                charArray[idx-1] = '0';
                charArray[zeroIdx] = number;
                String replaced = String.valueOf(charArray);
                if(puzzles.containsKey(replaced)) {
                    continue;
                }
                puzzles.put(replaced, cost + 1);
                q.add(replaced);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] ints : puzzle) {
            for (int anInt : ints) {
                sb.append(anInt);
            }
        }
        String strPuzzle = sb.toString();
        if(puzzles.containsKey(strPuzzle)) {
            System.out.println(puzzles.get(strPuzzle));
        } else {
            System.out.println(-1);
        }
    }
}
