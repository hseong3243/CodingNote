package 프로그래머스.level4.가사_검색;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] result = new Solution().solution(words, queries);
        for (int i : result) {
            System.out.println(i);
        }
    }
    
    static class Trie {
        Character s;
        Map<Character, Trie> children = new HashMap<>();
        Map<Integer, Integer> wordLengthCounter = new HashMap<>();

        public Trie(Character c) {
            this.s = c;
        }

        public void countWordLength(int wordLength) {
            Integer count = wordLengthCounter.getOrDefault(wordLength, 0);
            wordLengthCounter.put(wordLength, count + 1);
        }
    }

    public int[] solution(String[] words, String[] queries) {
        Map<Character, Trie> naturalOrder = new HashMap<>();
        Map<Character, Trie> reverseOrder = new HashMap<>();
        Map<Integer, Integer> wordLengthCounter = new HashMap<>();
        for (String word : words) {
            wordLengthCounter.put(word.length(),
                wordLengthCounter.getOrDefault(word.length(), 0) + 1);
            char[] arr = word.toCharArray();
            Map<Character, Trie> node = naturalOrder;
            for (char c : arr) {
                Trie trie = node.getOrDefault(c, new Trie(c));
                node.put(c, trie);
                trie.countWordLength(word.length());
                node = trie.children;
            }
            node = reverseOrder;
            for(int i=arr.length-1; i>=0; i--) {
                char c = arr[i];
                Trie trie = node.getOrDefault(c, new Trie(c));
                node.put(c, trie);
                trie.countWordLength(word.length());
                node = trie.children;
            }
        }

        int[] result = new int[queries.length];
        int idx = 0;
        for (String query : queries) {
            char[] arr = query.toCharArray();
            if(!query.startsWith("?")) {
                Map<Character, Trie> node = naturalOrder;
                for(int i=0; i<arr.length; i++) {
                    if(!node.containsKey(arr[i])) {
                        result[idx] = 0;
                        break;
                    }
                    Trie trie = node.get(arr[i]);
                    node = trie.children;
                    if(arr[i+1] == '?') {
                        Integer count = trie.wordLengthCounter.getOrDefault(query.length(), 0);
                        result[idx] = count;
                        break;
                    }
                }
            } else if(!query.endsWith("?")) {
                Map<Character, Trie> node = reverseOrder;
                for(int i=arr.length-1; i>=0; i--) {
                    if(!node.containsKey(arr[i])) {
                        result[idx] = 0;
                        break;
                    }
                    Trie trie = node.get(arr[i]);
                    node = trie.children;
                    if(arr[i-1] == '?') {
                        Integer count = trie.wordLengthCounter.getOrDefault(query.length(), 0);
                        result[idx] = count;
                        break;
                    }
                }
            } else {
                result[idx] = wordLengthCounter.getOrDefault(query.length(), 0);
            }
            idx++;
        }

        return result;
    }
}
