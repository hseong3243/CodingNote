package 프로그래머스.level2.일24_나라의_숫자;

public class Solution {

    public static void main(String[] args) {
        for(int n=1; n<=20; n++) {
            System.out.print(n + " ");
            String result = new Solution().solution(n);
        }
    }

    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            n--;
            int number = n % 3;
            n = n/3;
            switch (number) {
                case 0 -> sb.append(1);
                case 1 -> sb.append(2);
                case 2 -> sb.append(4);
            }
        }

        return sb.reverse().toString();
    }
}
