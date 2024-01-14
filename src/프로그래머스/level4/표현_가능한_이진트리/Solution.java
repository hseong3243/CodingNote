package 프로그래머스.level4.표현_가능한_이진트리;

public class Solution {

    public static void main(String[] args) {
//        long[] numbers = {7, 42, 5};
        long[] numbers = {63, 111, 95};
        int[] result = new Solution().solution(numbers);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];
        int idx = 0;
        for (long number : numbers) {
            String binaryString = Long.toBinaryString(number);
            result[idx++] = isPossible(binaryString);
        }
        return result;
    }

    private int isPossible(String binaryString) {
        binaryString = padding(binaryString);
        char[] arr = binaryString.toCharArray();
        int root = arr.length / 2;
        int depth = (arr.length - root) / 2;
        return isPossible(arr, arr[root], depth, root - depth)
            && isPossible(arr, arr[root], depth, root + depth)
            ? 1 : 0;
    }

    private String padding(String binaryString) {  // 포화 이진 트리의 길이 만큼 패딩
        int len = getBinaryTreeLength(binaryString);
        if (binaryString.length() < len) {
            String padding = "0".repeat(len - binaryString.length());
            binaryString = padding + binaryString;
        }
        return binaryString;
    }

    private int getBinaryTreeLength(String binary) {  // 가능한 포화 이진 트리의 길이를 구함 1,3,7,15,....
        int len = 0;
        int i = 0;
        while (len < binary.length()) {
            len += (int) Math.pow(2, i++);
        }
        return len;
    }

    private boolean isPossible(char[] binary, char root, int depth, int idx) {  // 0인 노드를 거쳤을 때, 이번 차례가 1이면 불가
        if(depth == 0) {
            return true;
        }
        if (root == '0' && binary[idx] == '1') {
            return false;
        }
        if (binary[idx] == '0') {
            root = '0';
        }
        depth /= 2;
        return isPossible(binary, root, depth, idx - depth)
        && isPossible(binary, root, depth, idx + depth);
    }
}
