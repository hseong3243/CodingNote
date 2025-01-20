package 백준.신나는_함수_실행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> inputs = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null && !line.equals("-1 -1 -1")) {
            int[] input = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            inputs.add(input);
        }
        new Main().solution(inputs);
    }

    private int[][][] arr;

    private void solution(List<int[]> inputs) {
        arr = new int[21][21][21];
        StringBuilder result = new StringBuilder();
        for (int[] input : inputs) {
            result.append("w(").append(input[0]).append(", ").append(input[1]).append(", ")
                .append(input[2]).append(") = ").append(test(input[0],
                    input[1], input[2])).append("\n");
        }
        System.out.println(result);
    }

    private int test(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        } else if (a > 20 || b > 20 || c > 20) {
            return test(20, 20, 20);
        }
        if (arr[a][b][c] != 0) {
            return arr[a][b][c];
        }

        int result;
        if (a < b && b < c) {
            result = test(a, b, c - 1) + test(a, b - 1, c - 1) - test(a, b - 1, c);
        } else {
            result = test(a - 1, b, c) + test(a - 1, b - 1, c) + test(a - 1, b, c - 1) - test(a - 1,
                b - 1, c - 1);
        }
        return arr[a][b][c] = result;
    }
}
