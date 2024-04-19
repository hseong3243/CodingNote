package 백준.gold.휴게소_세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int l = Integer.parseInt(split[2]);
        split = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        new Main().solution(n, m, l, arr);
    }

    private void solution(int n, int m, int l, int[] arr) {
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
        int current = 0;
        for (int i : arr) {
            list.add(i - current);
            current = i;
        }
        list.add(l - current);
        int result = search(m, l, list);
        System.out.println(result);
    }

    private int search(int m, int l, List<Integer> list) {
        int max = l-1;
        int min = 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (pick(mid, list, m)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    private boolean pick(int candidate, List<Integer> list, int m) {
        int count = 0;
        for (Integer length : list) {
            int mul = length / candidate;
            count += mul;
            if(length%candidate == 0) {
                count--;
            }
        }
        if (count > m) {
            return false;
        }
        return true;
    }
}
