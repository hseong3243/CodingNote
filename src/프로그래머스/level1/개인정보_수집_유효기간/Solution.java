package 프로그래머스.level1.개인정보_수집_유효기간;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        int[] result = new Solution().solution(today, terms, privacies);
        for (int i : result) {
            System.out.println(i);
        }
    }

    static class Date {
        int year;
        int month;
        int day;

        public Date(String date) {
            String[] split = date.split("\\.");
            year = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            day = Integer.parseInt(split[2]);
        }

        public Date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public Date plusMonth(int month) {
            this.month += month;
            if(this.month > 12) {
                this.year++;
                this.month -= 12;
            }
            return new Date(this.year, this.month, this.day);
        }

        public boolean goe(Date removeDate) {
            return toDays() >= removeDate.toDays();
        }

        public int toDays() {
            return year * 28 * 12 + month * 28 + day;
        }
    }

    private Date today;
    private Map<String, Integer> terms = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        this.today = new Date(today);
        for (String s : terms) {
            String[] split = s.split(" ");
            this.terms.put(split[0], Integer.parseInt(split[1]));
        }
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        for (String privacy : privacies) {
            String[] split = privacy.split(" ");
            Date privacyDate = new Date(split[0]);
            Integer month = this.terms.get(split[1]);
            Date removeDate = privacyDate.plusMonth(month);
            if(this.today.goe(removeDate)) {
                result.add(idx+1);
            }
            idx++;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
