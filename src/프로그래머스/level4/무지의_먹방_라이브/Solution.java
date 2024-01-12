package 프로그래머스.level4.무지의_먹방_라이브;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        int[] foodTimes = {3, 1, 2};
        long k = 5;
        int result = new Solution().solution(foodTimes, k);
        System.out.println(result);
    }

    static class Food {

        int index;
        int amount;

        public Food(int index, int amount) {
            this.index = index;
            this.amount = amount;
        }
    }

    public int solution(int[] food_times, long k) {
        PriorityQueue<Food> q = new PriorityQueue<>(Comparator.comparingInt(food -> food.amount));
        for (int i = 0; i < food_times.length; i++) {
            q.add(new Food(i + 1, food_times[i]));
        }

        long sum = 0;
        int number = 0;
        while (!q.isEmpty()) {
            Food food = q.peek();
            number = food.amount - number;
            if (sum + (long) number * q.size() > k) {
                break;
            }
            sum += (long) number * q.size();
            number = food.amount;
            q.poll();
        }

        List<Food> foods = new ArrayList<>(q);
        foods.sort(Comparator.comparingInt(food -> food.index));
        if(!foods.isEmpty()) {
            long n = (k - sum) % foods.size();
            Food food = foods.get((int) n);
            return food.index;
        }
        return -1;
    }
}
