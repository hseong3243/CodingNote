package 프로그래머스.level3.외벽_점검;

public class Solution {

    public static void main(String[] args) {
//        int n = 12;
//        int[] weak = {1, 5, 6, 10};
//        int[] weak = {1, 3, 4, 9, 10};
//        int[] dist = {1, 2, 3, 4};
//        int[] dist = {3, 5, 7};
        int n = 200;
        int[] weak = {0, 10, 50, 80, 120, 160};
        int[] dist = {1, 10, 5, 40, 30};
        int result = new Solution().solution(n, weak, dist);
        System.out.println(result);
    }

    private int n;
    private int[] weak;
    private int[] dist;
    private int result = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;

        int[] arr = new int[dist.length];
        boolean[] visit = new boolean[dist.length];
        findDist(0, arr, visit);
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        return result;
    }

    private void findDist(int idx, int[] arr, boolean[] visit) {
        if (idx >= arr.length) {
            find(arr);
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (visit[i]) {
                continue;
            }
            arr[idx] = dist[i];
            visit[i] = true;
            findDist(idx + 1, arr, visit);
            visit[i] = false;
        }
    }

    private void find(int[] arr) {
        for (int i = 0; i < weak.length; i++) {
            find(arr, i);
        }
    }

    private void find(int[] arr, int weakIdx) {
        int arrIdx = 0;
        int weakCount = 0;
        while (weakCount < weak.length) {
            if (arrIdx >= arr.length) {
                return;
            }
            int startPoint = weak[weakIdx];
            int distance = arr[arrIdx];
            for (int i = 0; i <= distance; i++) {
                if (weak[weakIdx] == startPoint) {
                    weakIdx++;
                    weakCount++;
                }
                if (weakIdx >= weak.length) {
                    weakIdx = 0;
                }
                if (weakCount >= weak.length) {
                    break;
                }
                startPoint++;
                if (startPoint >= n) {
                    startPoint = 0;
                }
            }
            arrIdx++;
        }
        result = Math.min(result, arrIdx);
    }
}
