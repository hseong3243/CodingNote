package 프로그래머스.level2.방금그곡;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
//        String m = "ABCDEFG";
//        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String m = "ABC";
//        String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:14,WORLD,ABCDEF"};
//        String m = "CC#BCC#BCC#BCC#B";
//        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String result = new Solution().solution(m, musicinfos);
        System.out.println(result);
    }

    static class Music {
        int playTime;
        String title;
        char[] melody;

        public Music(String music) {
            String[] split = music.split(",");
            String[] startTime = split[0].split(":");
            String[] endTime = split[1].split(":");
            int hour = Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]);
            int min = Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]);
            playTime = hour * 60 + min;
            title = split[2];
            melody = split[3].toCharArray();
        }

        public boolean isEqualTo(String m) {
            boolean result = false;
            char[] inputMelody = m.toCharArray();
            int begin = 0;
            int playTime = 0;
            while(begin < melody.length) {
                if(melody[begin] == inputMelody[0]) {
                    result = isEqualTo(inputMelody, begin, playTime);
                }
                if(result) {
                    break;
                }
                if(melody[begin] != '#') {
                    playTime++;
                }
                begin++;
            }
            return result;
        }

        private boolean isEqualTo(char[] inputMelody, int begin, int playTime) {
            int melodyIdx = begin;
            int inputIdx = 0;
            while(inputIdx < inputMelody.length) {
                if(playTime > this.playTime) {
                    return false;
                }
                if(melody[melodyIdx] != inputMelody[inputIdx]) {
                    return false;
                }
                if(melody[melodyIdx] != '#') {
                    playTime++;
                }
                melodyIdx++;
                inputIdx++;
                if(melodyIdx >= melody.length) {
                    melodyIdx = 0;
                }
            }
            if(melody[melodyIdx] == '#') {
                return false;
            }
            return true;
        }
    }

    public String solution(String m, String[] musicinfos) {
        List<Music> musicList = new ArrayList<>();
        for (String musicinfo : musicinfos) {
            musicList.add(new Music(musicinfo));
        }

        List<Music> candidate = new ArrayList<>();
        for (Music music : musicList) {
            if(music.isEqualTo(m)) {
                candidate.add(music);
            }
        }
        candidate.sort((a, b) -> b.playTime - a.playTime);
        if(candidate.isEmpty()) {
            return "(None)";
        }
        return candidate.get(0).title;
    }
}
