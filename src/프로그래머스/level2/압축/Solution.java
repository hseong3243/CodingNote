package 프로그래머스.level2.압축;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int[] solution(String msg) {
        Map<String, Integer> index = new HashMap<>();
        for(int i=0; i<26; i++) {
            index.put(String.valueOf((char) ('A' + i)), i+1);
        }
        List<Integer> compression = new ArrayList<>();


        int start = 0;
        int end = 1;
        while(true) {
            if(end > msg.length()) {
                break;
            }
            int nextStart = start;
            String previousWord = "";
            while(true) {
                if(end > msg.length()) {
                    compression.add(index.get(msg.substring(start)));
                    break;
                }
                String word = msg.substring(start, end);
                if(!index.containsKey(word)) {
                    compression.add(index.get(previousWord));
                    index.put(word, index.size() + 1);
                    break;
                }
                previousWord = word;
                nextStart++;
                end++;
            }
            start = nextStart;
            end = start + 1;
        }

        return compression.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        new Solution().solution("TOBEORNOTTOBEORTOBEORNOT");
    }
}
