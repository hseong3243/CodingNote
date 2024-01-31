package 프로그래머스.level2.당구_연습;

public class Solution {

    public static void main(String[] args) {
        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};
        int[] result = new Solution().solution(m, n, startX, startY, balls);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int idx = 0;
        for (int[] ball : balls) {
            int targetX = ball[0];
            int targetY = ball[1];
            int result = Integer.MAX_VALUE;
            if(startX == targetX && startY > targetY) {  // 위쪽 벽면을 평행하게 친다.
                int pow = (int) Math.pow(2 * n - startY - targetY, 2);
                result = Math.min(result, pow);
            }
            if(startX == targetX && startY < targetY) { // 아래쪽 벽면을 평행하게 친다.
                int pow = (int) Math.pow(startY + targetY, 2);
                result = Math.min(result, pow);
            }
            if(startY == targetY && startX > targetX) {
                int pow = (int) Math.pow(2 * m - startX - targetX, 2);
                result = Math.min(result, pow);
            }
            if(startY == targetY && startX < targetX) {
                int pow = (int) Math.pow(startX + targetX, 2);
                result = Math.min(result, pow);
            }
            if(startY == targetY) {
                int result1 = getResult(n, startX, startY, targetX, startY);
                result = Math.min(result, result1);
            }
            if(startX == targetX) {
                int result1 = getResult(m, startY, startX, targetY, targetX);
                result = Math.min(result, result1);
            }
            if(startX != targetX && startY != targetY) {
                int result1 = getResult(n, startX, startY, targetX, targetY);
                int result2 = getResult(m, startY, startX, targetY, targetX);
                result = Math.min(result, Math.min(result1, result2));
            }
            answer[idx] = result;
            idx++;
        }
        return answer;
    }

    private int getResult(
        int n,
        int startX,
        int startY,
        int targetX,
        int targetY) {
        int bottom = Math.abs(startX - targetX);
        int bottomToTop = startY + targetY;
        int topToBottom = n - startY + n - targetY;
        int vertical = Math.min(bottomToTop, topToBottom);
        return bottom * bottom + vertical * vertical;
    }
}
