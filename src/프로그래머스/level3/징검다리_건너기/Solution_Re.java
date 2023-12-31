package 프로그래머스.level3.징검다리_건너기;

public class Solution_Re {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k =3;
        int result = new Solution_Re().solution(stones, k);
        System.out.println(result);
    }

    public int solution(int[] stones, int k) {
        int min = 1;
        int max = 200000000;
        while(min <= max) {
            int mid = (min + max) / 2;
            int distance = 0;
            for(int stone : stones) {
                if(stone <= mid) {
                    distance++;
                } else {
                    distance = 0;
                }
                if(distance >= k) {
                    break;
                }
            }
            if(distance >= k) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }
}
