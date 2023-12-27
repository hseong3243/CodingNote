package 프로그래머스.level2.두개_이하로_다른_비트;

public class Solution {

    public static void main(String[] args) {
        long[] numbers = {2, 7};
        long[] result = new Solution().solution(numbers);
        for (long number : result) {
            System.out.println(number);
        }
    }

    public long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            long number = numbers[i];
            String binary = Long.toString(number, 2);
            String answer = find(binary);
            result[i] = Long.parseLong(answer, 2);
        }

        return result;
    }

    private String find(String binary) {
        if(binary.equals("0")) {
            return "1";
        } else if(binary.equals("1")) {
            return "10";
        }

        char[] binaryCharArray = binary.toCharArray();
        int n = binaryCharArray.length;
        for (int i = n - 2; i >= 0; i--) {
            char first = binaryCharArray[i];
            char second = binaryCharArray[i+1];
            if(first == '1' && second == '1') {
                continue;
            }

            if(second == '0') {
                second = '1';
            } else if(first == '0' && second == '1') {
                first = '1';
                second = '0';
            }
            binaryCharArray[i] = first;
            binaryCharArray[i+1] = second;
            return String.valueOf(binaryCharArray);
        }
        return binary.replaceFirst("11", "101"); // 모든 비트가 1인 경우
    }
}
