package 프로그래머스.level2.순위_검색;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210",
            "python frontend senior chicken 150", "cpp backend senior pizza 260",
            "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150", "- and - and - and chicken 100",
            "- and - and - and - 150"};
        int[] result = new Solution().solution(info, query);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for (String s : info) {
            dfs(s.split(" "), 0, new StringBuilder());
        }
        for (Entry<String, List<Integer>> entry : map.entrySet()) {
            entry.getValue().sort(Comparator.comparingInt(a -> a));
        }

        int[] result = new int[query.length];
        int idx = 0;
        for (String s : query) {
            String[] split = s.split(" ");
            int point = Integer.parseInt(split[7]);
            String key = split[0] + split[2] + split[4] + split[6];
            List<Integer> scores = map.getOrDefault(key, new ArrayList<>());
            int start = binary(scores, point);
            result[idx++] = scores.size() - start;
        }

        return result;
    }

    private void dfs(String[] info, int idx, StringBuilder sb) {
        if (idx == 4) {
            List<Integer> scores = map.getOrDefault(sb.toString(), new ArrayList<>());
            scores.add(Integer.parseInt(info[4]));
            map.put(sb.toString(), scores);
            return;
        }
        sb.append("-");
        dfs(info, idx + 1, sb);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(info[idx]);
        dfs(info, idx + 1, sb);
        sb.delete(sb.length() - info[idx].length(), sb.length());
    }


    private int binary(List<Integer> scores, int point) {
        int min = 0;
        int max = scores.size() - 1;
        while (min <= max) {
            int idx = (min + max) / 2;
            int score = scores.get(idx);
            if (score >= point) {
                max = idx - 1;
            } else {
                min = idx + 1;
            }
        }
        return min;
    }
}
