package 프로그래머스.level4.자동완성;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
//        String[] words = {"go", "gone", "guild"};
        String[] words = {"abc","def","ghi","jklm"};
//        String[] words = {"word","war","warrior","world"};
        int result = new Solution().solution(words);
        System.out.println(result);
    }

    static class Branch {

        char name;
        Map<Character, Branch> children = new HashMap<>();
        int point = 0;
        String leaf = "";

        public Branch(char name) {
            this.name = name;
        }

        public int size() {
            return children.size();
        }

        public void increasePoint() {
            point++;
        }

        public void addLeaf(String word) {
            leaf = word;
        }
    }

    public int solution(String[] words) {
        Map<Character, Branch> trie = new HashMap<>();
        for (String word : words) {
            char[] arr = word.toCharArray();
            Branch parent = trie.getOrDefault(arr[0], new Branch(arr[0]));
            trie.put(arr[0], parent);
            parent.increasePoint();
            for(int i=1; i<arr.length; i++) {
                Branch child = parent.children.getOrDefault(arr[i], new Branch(arr[i]));
                parent.children.put(arr[i], child);
                parent = child;
                parent.increasePoint();
            }
            parent.addLeaf(word);
        }
        return search(words, trie);
    }

    private int search(String[] words, Map<Character, Branch> trie) {
        int result = 0;
        for (String word : words) {
            char[] input = word.toCharArray();
            Map<Character, Branch> children = trie;
            for(int i=0; i<input.length; i++) {
                Branch nextNode = children.get(input[i]);
                if(i == input.length-1) {
                    result += input.length;
                    break;
                }
                if(nextNode.point == 1 && nextNode.leaf.isBlank()) {
                    result += i + 1;
                    break;
                }
                children = nextNode.children;
            }
        }
        return result;
    }
}
