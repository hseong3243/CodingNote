package 프로그래머스.level3.금과_은_운반하기;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        int[] g = {100};
        int[] s = {100};
        int[] w = {7};
        int[] t = {10};
        long result = new Solution().solution(a, b, g, s, w, t);
        System.out.println(result);
    }

    static class City {

        int gold;
        int silver;
        int weight;
        int time;

        public City(int gold, int silver, int weight, int time) {
            this.gold = gold;
            this.silver = silver;
            this.weight = weight;
            this.time = time;
        }
    }

    private List<City> cities;
    private int a;
    private int b;

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.a = a;
        this.b = b;
        cities = new ArrayList<>(g.length);
        for (int i = 0; i < g.length; i++) {
            cities.add(new City(g[i], s[i], w[i], t[i]));
        }
        cities.sort(Comparator.comparingInt(city -> city.time));
        return findGoldTime();
    }

    private long findGoldTime() {
        long min = 1;
        long max = 1000000000000L;
        while (min <= max) {
            long time = (min + max) / 2;
            if (getSum(time)) {
                max = time - 1;
            } else {
                min = time + 1;
            }
        }
        return min;
    }

    private boolean getSum(long time) {
        long sum = 0;
        long sumGold = 0;
        long sumSilver = 0;
        for (City city : cities) {
            long cityTime = city.time;
            if (cityTime > time) {
                break;
            }
            long count = 1;
            if (time - cityTime > 0) {
                count += (time - cityTime) / (2 * cityTime);
            }
            long possibleWeight = city.weight * count;
            sum += Math.min(possibleWeight, city.gold + city.silver);
            sumGold += Math.min(possibleWeight, city.gold);
            sumSilver += Math.min(possibleWeight, city.silver);
        }
        return sum >= a + b && sumGold >= a && sumSilver >= b;
    }
}
