package 백준.gold.단어_수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        for(int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }
        new Main().solution(n, words);
    }

    private void solution(int n, String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        for (String word : words) {
            for(int i=0; i<word.length(); i++) {
                double value = map.getOrDefault(word.charAt(i), 0);
                value += Math.pow(10, word.length()-i-1);
                map.put(word.charAt(i), (int) value);
            }
        }

        int cost = 9;
        Map<Character, Integer> number = new HashMap<>();
        List<Character> list = map.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .map(Entry::getKey)
            .collect(Collectors.toUnmodifiableList());
        for (Character c : list) {
            number.put(c, cost--);
        }

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            String word = words[i];
            int num = 0;
            for (char c : word.toCharArray()) {
                num *= 10;
                num += number.get(c);
            }
            arr[i] = num;
        }

        int result = 0;
        for (int i : arr) {
            result += i;
        }
        System.out.println(result);
    }
}
