package 프로그래머스.level3.광고_삽입;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String playTime = "02:03:55";
        String advTime = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29",
            "01:30:59-01:53:29", "01:37:44-02:02:30"};
        String result = new Solution().solution(playTime, advTime, logs);
        System.out.println(result);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int[] time = new int[360000];
        for (String log : logs) {
            String[] split = log.split("-");
            int startTime = toSeconds(split[0]);
            int endTime = toSeconds(split[1]);
            for (int i = startTime; i < endTime; i++) {
                time[i]++;
            }
        }
        int advTime = toSeconds(adv_time);
        long sum = 0;
        for (int i = 0; i < advTime; i++) {
            sum += time[i];
        }
        int startAdv = 0;
        long result = sum;
        int playTime = toSeconds(play_time);
        for (int i = advTime; i < playTime; i++) {
            sum += time[i] - time[i - advTime];
            if(sum > result) {
                startAdv = i - advTime + 1;
                result = sum;
            }
        }

        return toTime(startAdv);
    }

    private int toSeconds(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60
            + Integer.parseInt(split[2]);
    }

    private String toTime(int seconds) {
        int hour = seconds / 3600;
        seconds = seconds % 3600;
        int min = seconds / 60;
        seconds = seconds % 60;
        String hourStr = hour < 10 ? "0" + hour : String.valueOf(hour);
        String minStr = min < 10 ? "0" + min : String.valueOf(min);
        String secStr = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
        return hourStr + ":" + minStr + ":" + secStr;
    }
}
