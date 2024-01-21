package 프로그래머스.level3.추석_트래픽;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String[] lines = {
//            "2016-09-15 01:00:04.001 2.0s",
//            "2016-09-15 01:00:07.000 2s"
//            "2016-09-15 01:00:04.002 2.0s",
//            "2016-09-15 01:00:07.000 2s"
            "2016-09-15 20:59:57.421 0.351s",
            "2016-09-15 20:59:58.233 1.181s",
            "2016-09-15 20:59:58.299 0.8s",
            "2016-09-15 20:59:58.688 1.041s",
            "2016-09-15 20:59:59.591 1.412s",
            "2016-09-15 21:00:00.464 1.466s",
            "2016-09-15 21:00:00.741 1.581s",
            "2016-09-15 21:00:00.748 2.31s",
            "2016-09-15 21:00:00.966 0.381s",
            "2016-09-15 21:00:02.066 2.62s"
        };
        int result = new Solution().solution(lines);
        System.out.println(result);
    }

    static class Time {
        int startTime;
        int endTime;

        public Time(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean notInTps(int startTime, int endTime) {
            if(this.startTime > endTime) {
                return true;
            }
            if(this.endTime < startTime) {
                return true;
            }
            return false;
        }
    }

    public int solution(String[] lines) {
        List<Time> times = new ArrayList<>();
        for (String line : lines) {
            String[] log = line.split(" ");
            String[] start = log[1].split(":");
            int hour = Integer.parseInt(start[0]);
            int min = Integer.parseInt(start[1]);
            double sec = Double.parseDouble(start[2]);
            int endTime = (int) (hour * 3600000 + min * 60000 + sec * 1000);
            int consumed = (int)(Double.parseDouble(log[2].replace("s", ""))*1000);
            Time time = new Time(
                endTime - consumed + 1,
                endTime
            );
            times.add(time);
        }
        int result = 0;
        for(int i=0; i<times.size(); i++) {
            Time time = times.get(i);
            int start = time.endTime;
            int end = time.endTime + 999;
            int count = 0;
            for(int j=i; j<times.size(); j++) {
                if(times.get(j).notInTps(start, end)) {
                    break;
                }
                count++;
            }
            result = Math.max(result, count);
        }
        return result;
    }
}
