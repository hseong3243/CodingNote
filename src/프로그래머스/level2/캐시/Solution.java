package 프로그래머스.level2.캐시;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * LRU(가장 최근에 적게 사용한 놈부터) 사용, 캐시 히트 1, 캐시 미스 5 소요
     * @param cacheSize 0 이상, 30 이하
     * @param cities 최대 100,000 개, 각 원소는 최대 20자
     * @return 총 실행 시간
     */
    public int solution(int cacheSize, String[] cities) {
        int time = 0;

        int now = 0;
        Map<String, Integer> cache = new HashMap<>();
        for (String city : cities) {
            if(cacheSize == 0) {
                time += 5;
                continue;
            }
            city = city.toLowerCase();
            if(cache.containsKey(city)) {
                time += 1;
                cache.put(city, now++);
            } else {
                time += 5;
                if(cache.size() < cacheSize) {
                    cache.put(city, now++);
                } else {
                    int oldestValue = Integer.MAX_VALUE;
                    String oldestKey = "";
                    for (String key : cache.keySet()) {
                        if(cache.get(key) < oldestValue) {
                            oldestKey = key;
                            oldestValue = cache.get(key);
                        }
                    }

                    cache.remove(oldestKey);
                    cache.put(city, now++);
                }
            }
        }

        return time;
    }

    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};
        int result = new Solution().solution(cacheSize, cities);
        System.out.println(result);
    }
}
