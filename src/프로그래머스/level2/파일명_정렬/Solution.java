package 프로그래머스.level2.파일명_정렬;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
//        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II",
//            "F-14 Tomcat"};
        String[] result = new Solution().solution(files);
        for (String s : result) {
            System.out.println(s);
        }
    }

    static class File {
        int idx;
        String head;
        int number;
        String tail;

        public File(int idx, String file) {
            this.idx = idx;
            file = file.toLowerCase();
            String[] split = file.split("[0-9]+", 2);
            head = split[0];
            String replace = file.replaceFirst(head, "");
            if(split.length > 1) {
                tail = split[1];
                replace = replace.replace(tail, "");
            }
            this.number = Integer.parseInt(replace);
        }
    }

    public String[] solution(String[] files) {
        List<File> sorted = new ArrayList<>();
        for (int i=0; i<files.length; i++) {
            sorted.add(new File(i, files[i]));
        }
        sorted.sort((a, b) -> {
            int compare = a.head.compareTo(b.head);
            if(compare == 0) {
                return a.number - b.number;
            } else {
                return compare;
            }
        });

        String[] result = new String[files.length];
        for(int i=0; i< files.length; i++) {
            result[i] = files[sorted.get(i).idx];
        }
        return result;
    }
}
