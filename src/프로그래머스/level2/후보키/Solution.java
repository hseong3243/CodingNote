package 프로그래머스.level2.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        int result = new Solution().solution(relation);
        System.out.println(result);
    }

    private List<List<Integer>> candidateKeys = new ArrayList<>();
    private int rowSize;
    private int colSize;

    public int solution(String[][] relation) {
        rowSize = relation.length;
        colSize = relation[0].length;
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            keys.add("");
        }
        for (int i = 1; i <= colSize; i++) {
            findUniqueKey(0, i, new ArrayList<>(), keys, relation);
        }
        return candidateKeys.size();
    }

    private void findUniqueKey(int idx, int max, List<Integer> columns, List<String> keys,
        String[][] relation) {
        if (columns.size() == max) {
            if (new HashSet<>(keys).size() == rowSize && isMinimal(columns)) {
                candidateKeys.add(columns);
            }
            return;
        }
        if (idx >= colSize) {
            return;
        }
        findUniqueKey(idx + 1, max, columns, keys, relation);
        List<Integer> nextColumns = new ArrayList<>(columns);
        List<String> nextKeys = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            StringBuilder sb = new StringBuilder(keys.get(i));
            sb.append(relation[i][idx]);
            nextKeys.add(sb.toString());
        }
        nextColumns.add(idx);
        findUniqueKey(idx + 1, max, nextColumns, nextKeys, relation);
    }

    private boolean isMinimal(List<Integer> inputColumns) {
        for (List<Integer> candidate : candidateKeys) {
            int size = 0;
            for (Integer column : candidate) {
                if (inputColumns.contains(column)) {
                    size++;
                }
            }
            if (size == candidate.size()) {
                return false;
            }
        }
        return true;
    }
}
