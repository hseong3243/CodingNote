package 프로그래머스.level1.붕대_감기;

public class Solution {

    public static void main(String[] args) {
        int[] bandage = {5, 1, 5};
        int health = 30;
        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
//        int[] bandage = {3, 2, 7};
//        int health = 20;
//        int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};
        int result = new Solution().solution(bandage, health, attacks);
        System.out.println(result);
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = 0;
        int count = 0;
        int idx = 0;
        int maxHealth = health;
        while (idx < attacks.length) {
            time++;
            int[] attack = attacks[idx];
            if(attack[0] == time) {
                health -= attack[1];
                count = 0;
                idx++;
            } else {
                health += bandage[1];
                count++;
            }
            if(count == bandage[0]) {
                health += bandage[2];
                count = 0;
            }
            if(maxHealth < health) {
                health = maxHealth;
            }
            if(health <= 0) {
                return -1;
            }
            System.out.println(time + " " + health + " " + count);
        }
        return health;
    }
}
