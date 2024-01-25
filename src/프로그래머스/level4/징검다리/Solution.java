package 프로그래머스.level4.징검다리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        int result = new Solution().solution(distance, rocks, n);
        System.out.println(result);
    }

    private int[] rocks;
    private int n;
    private int distance;
    private List<Integer> distances;

    public int solution(int distance, int[] rocks, int n) {
        this.distance = distance;
        this.rocks = rocks;
        this.n = n;
        this.distances = new ArrayList<>(rocks.length + 2);
        Arrays.sort(this.rocks);
        distances.add(rocks[0]);
        for (int i = 1; i < rocks.length; i++) {
            distances.add(rocks[i] - rocks[i - 1]);
        }
        distances.add(distance - rocks[rocks.length - 1]);
        return find();
    }

    private int find() {
        int max = distance;
        int min = 1;
        while (min <= max) {
            int[] arr = new int[distances.size()];
            int count = n;
            int mid = (min + max) / 2;
            for (int i = 0; i < distances.size(); i++) {
                if (distances.get(i) + arr[i] < mid) {
                    if(i + 1 < distances.size()) {
                        arr[i + 1] = distances.get(i) + arr[i];
                    }
                    count--;
                }
            }
            if (count <= 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
