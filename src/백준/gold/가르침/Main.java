package 백준.gold.가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        String[] arr = new String[n];
        for(int i=0; i<n; i++) {
            arr[i] = br.readLine();
        }
        new Main().solution(n, k, arr);
    }

    private int n;
    private int k;
    private String[] arr;
    private int result = 0;

    private void solution(int n, int k, String[] arr) {
        this.n = n;
        this.k = k - 5;
        this.arr = arr;

        boolean[] visit = new boolean[26];
        visit[0] = true;
        visit['n'-'a'] = true;
        visit['t'-'a'] = true;
        visit['i'-'a'] = true;
        visit['c'-'a'] = true;
        if(k < 5) {
            System.out.println(0);
            return;
        }

        find(0, visit, 0);
        System.out.println(result);
    }

    private void find(int idx, boolean[] visit, int depth) {
        if(depth == k) {
            int count = 0;
            for (String s : arr) {
                boolean possible = true;
                for (char c : s.toCharArray()) {
                    if(!visit[c - 'a']) {
                        possible = false;
                        break;
                    }
                }
                if(possible) {
                    count++;
                }
            }
            result = Math.max(result, count);
            return;
        }
        for(int i=idx + 1; i<26; i++) {
            if(visit[i]) {
                continue;
            }
            visit[i] = true;
            find(i, visit, depth+1);
            visit[i] = false;
        }
    }
}
