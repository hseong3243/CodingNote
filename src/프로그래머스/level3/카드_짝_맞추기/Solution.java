package 프로그래머스.level3.카드_짝_맞추기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int r = 1;
        int c = 0;
        int result = new Solution().solution(board, r, c);
        System.out.println(result);
    }

    static class Point {

        int r;
        int c;
        int cost;

        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private boolean[] card;
    private int[][] board;
    private int result = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {
        card = new boolean[7];
        this.board = board;
        for (int[] ints : board) {
            for (int i : ints) {
                if(i != 0) {
                    card[i] = true;
                }
            }
        }
        findNextCard(r, c, 0);
        return result;
    }

    private void findNextCard(int r, int c, int cost) {
        boolean endGame = true;
        for (int i = 1; i <= 6; i++) {
            if (card[i]) {
                endGame = false;
                Point begin = findPath(r, c, i);
                board[begin.r][begin.c] = 0;
                Point end = findPath(begin.r, begin.c, i);
                board[end.r][end.c] = 0;
                card[i] = false;
                findNextCard(end.r, end.c, cost + begin.cost + end.cost + 2);
                card[i] = true;
                board[begin.r][begin.c] = i;
                board[end.r][end.c] = i;
            }
        }
        if(endGame) {
            result = Math.min(result, cost);
        }
    }

    private Point findPath(int r, int c, int target) {
        Point result = null;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c, 0));
        while (!q.isEmpty()) {
            Point point = q.poll();
            if(board[point.r][point.c] == target) {
                result = point;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = point.r + DR[d];
                int nextC = point.c + DC[d];
                int nextCost = point.cost + 1;
                if (canMove(nextR, nextC)) {
                    q.add(new Point(nextR, nextC, nextCost));
                    if(board[nextR][nextC] != 0) {
                        continue;
                    }
                }
                nextR += DR[d];
                nextC += DC[d];
                while (canMove(nextR, nextC)) {
                    if(board[nextR][nextC] != 0) {
                        q.add(new Point(nextR, nextC, nextCost));
                        break;
                    }
                    if(d == 0 || d == 1) {  //상, 하
                        if(nextR == 0 || nextR == 3) {
                            q.add(new Point(nextR, nextC, nextCost));
                            break;
                        }
                    } else {  //좌, 우
                        if(nextC == 0 || nextC == 3) {
                            q.add(new Point(nextR, nextC, nextCost));
                            break;
                        }
                    }
                    nextR += DR[d];
                    nextC += DC[d];
                }
            }
        }
        return result;
    }

    private boolean canMove(int r, int c) {
        if (r < 0 || r >= 4 || c < 0 || c >= 4) {
            return false;
        }
        return true;
    }
}
