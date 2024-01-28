package 프로그래머스.level3.매칭_점수;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {

    public static void main(String[] args) {
        String word = "blind";
        String[] pages = {
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        int result = new Solution().solution(word, pages);
        System.out.println(result);
    }

    static class Page {

        int idx;
        String url;
        List<String> externalUrl = new ArrayList<>();
        int defaultPoint = 0;
        double matchingPoint = 0;

        public Page(String html, String word, int idx) {
            html = html.toLowerCase();
            word = word.toLowerCase();
            String[] split = html.split("[^a-z/:.1-9- =\"]+");
            for (String s : split) {
                if(s.startsWith("meta property=\"og:url\" content=\"")) {
                    this.url = s
                        .replace("meta property=\"og:url\" content=\"", "")
                        .replace("\"/", "");
                }
                if(s.startsWith("a href=\"")) {
                    String externalUrl = s
                        .replace("a href=\"", "")
                        .replace("\"", "");
                    this.externalUrl.add(externalUrl);
                }
            }
            for (String s : split) {
                String[] words = s.split("[^a-z]");
                for (String str : words) {
                    if (str.equals(word)) {
                        defaultPoint++;
                    }
                }
            }
            matchingPoint += defaultPoint;
            this.idx = idx;
        }

        public void addLinkPoint(Page referer) {
            matchingPoint += (double) referer.defaultPoint / referer.externalUrl.size();
        }
    }

    public int solution(String word, String[] pages) {
        Map<String, Page> map = new HashMap<>();
        int idx = 0;
        for (String html : pages) {
            Page page = new Page(html, word, idx++);
            map.put(page.url, page);
        }
        for (Entry<String, Page> entry : map.entrySet()) {
            for (String externalUrl : entry.getValue().externalUrl) {
                if(map.containsKey(externalUrl)) {
                    Page externalPage = map.get(externalUrl);
                    externalPage.addLinkPoint(entry.getValue());
                }
            }
        }
        double maxPoint = 0;
        int resultIdx = Integer.MAX_VALUE;
        for (Page value : map.values()) {
            if (value.matchingPoint > maxPoint) {
                maxPoint = value.matchingPoint;
                resultIdx = value.idx;
            } else if (value.matchingPoint == maxPoint && value.idx < resultIdx) {
                resultIdx = value.idx;
            }
        }
        return resultIdx;
    }
}
