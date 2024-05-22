package 백준.gold.애너그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            sb.append(new Main().solution(str));
        }
        System.out.println(sb);
    }

    private String origin;
    private Set<String> result = new TreeSet<>(String::compareTo);

    private StringBuilder solution(String str) {
        this.origin = str;

        int[] arr = new int[26];
        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        make(sb, arr);

        StringBuilder out = new StringBuilder();
        for (String s : result) {
            out.append(s).append("\n");
        }
        return out;
    }

    private void make(StringBuilder sb, int[] arr) {
        if (sb.length() == origin.length()) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) {
                continue;
            }
            arr[i]--;
            sb.append((char) ('a' + i));
            make(sb, arr);
            sb.deleteCharAt(sb.length() - 1);
            arr[i]++;
        }
    }
}
