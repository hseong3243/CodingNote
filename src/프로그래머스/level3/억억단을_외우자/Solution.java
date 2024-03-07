package 프로그래머스.level3.억억단을_외우자;

public class Solution {

    public static void main(String[] args) {
        int e = 8;
        int[] starts = {1, 3, 7};
        int[] result = new Solution().solution(e, starts);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public int[] solution(int e, int[] starts) {
        int[] arr = find(e);
        int[] result = new int[e+1];
        int idx = 0;
        int count = 0;
        for(int i=e; i>= 0; i--) {
            if(arr[i] >= count) {
                idx = i;
                count = arr[i];
            }
            result[i] = idx;
        }

        int[] answer = new int[starts.length];
        for(int i=0; i<starts.length; i++) {
            answer[i] = result[starts[i]];
        }
        return answer;
    }

    private int[] find(int n) {
        int[] arr = new int[n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; i*j<=n; j++) {
                arr[i*j]++;
            }
        }
        return arr;
    }
}
