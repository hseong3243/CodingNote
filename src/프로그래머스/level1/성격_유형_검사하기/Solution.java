package 프로그래머스.level1.성격_유형_검사하기;

import java.time.chrono.MinguoDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String result = new Solution().solution(survey, choices);
        System.out.println(result);
    }

    private static final String[][] MIND = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};

    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] strings : MIND) {
            for (String m : strings) {
                map.put(m, 0);
            }
        }
        for (int i = 0; i < survey.length; i++) {
            String surv = survey[i];
            int choice = choices[i];
            String[] split = surv.split("");
            if (choice <= 3) {
                map.put(split[0], map.get(split[0]) + 4 - choice);
            } else if (choice >= 5) {
                map.put(split[1], map.get(split[1]) + choice - 4);
            }
        }
        StringBuilder result = new StringBuilder();
        for (String[] minds : MIND) {
            if(Objects.equals(map.get(minds[0]), map.get(minds[1]))) {
                int i = minds[0].compareTo(minds[1]);
                result.append(i < 0 ? minds[0] : minds[1]);
            } else {
                if(map.get(minds[0]) > map.get(minds[1])) {
                    result.append(minds[0]);
                } else {
                    result.append(minds[1]);
                }
            }
        }

        return result.toString();
    }
}
