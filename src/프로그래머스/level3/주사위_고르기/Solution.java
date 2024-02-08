package 프로그래머스.level3.주사위_고르기;

import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4},
            {1, 1, 4, 4, 5, 5}};
//        int[][] dice = {{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80},
//            {70, 70, 1, 1, 70, 70}};
        int[] result = new Solution().solution(dice);
        for (int i : result) {
            System.out.println(i);
        }
    }

    private int[][] dice;
    private int n;
    private int[] diceA;
    private int[] diceB;
    private int maxWin = 0;
    private int[] maxDice;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        this.diceA = new int[n / 2];
        this.diceB = new int[n / 2];
        choiceDice(0, 0);
        for (int i = 0; i < maxDice.length; i++) {
            maxDice[i]++;
        }
        return maxDice;
    }

    private void choiceDice(int idxA, int idxB) {
        if (idxA + idxB == n) {
            PriorityQueue<Integer> qA = new PriorityQueue<>();
            PriorityQueue<Integer> qB = new PriorityQueue<>();
            roll(0, 0, diceA, qA);
            roll(0, 0, diceB, qB);
            int win = 0;
            while (!qB.isEmpty()) {
                Integer sumB = qB.poll();
                while (!qA.isEmpty() && sumB >= qA.peek()) {
                    qA.poll();
                }
                if(qA.isEmpty()) {
                    break;
                }
                win += qA.size();
            }
            if(win > maxWin) {
                maxWin = win;
                maxDice = diceA.clone();
            }
            qA.clear();
            qB.clear();
            return;
        }
        if (idxA < n / 2) {
            diceA[idxA] = idxA + idxB;
            choiceDice(idxA + 1, idxB);
            diceA[idxA] = 0;
        }
        if (idxB < n / 2) {
            diceB[idxB] = idxA + idxB;
            choiceDice(idxA, idxB + 1);
            diceB[idxB] = 0;
        }
    }

    private void roll(int idx, int sum, int[] choose, PriorityQueue<Integer> q) {
        if(idx >= n/2) {
            q.add(sum);
            return;
        }
        for (int d : dice[choose[idx]]) {
            roll(idx+1, sum + d, choose, q);
        }
    }
}
