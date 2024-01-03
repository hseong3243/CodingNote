package 프로그래머스.level1.문자열_나누기;

public class Solution {

    public static void main(String[] args) {
        String s = "aaabbaccccabba";
        int result = new Solution().solution(s);
        System.out.println(result);
    }

    public int solution(String s) {
        int result = 0;
        char origin = s.charAt(0);
        char[] arr = s.toCharArray();
        int equal = 0;
        int notEqual = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == origin) {
                equal++;
            } else {
                notEqual++;
            }
            System.out.print(arr[i]);

            if(equal == notEqual) {
                result++;
                if(i+1 >= arr.length) {
                    break;
                }
                origin = arr[i+1];
                equal = 0;
                notEqual = 0;
            }
        }
        if(equal != notEqual) {
            result++;
        }

        return result;
    }
}
