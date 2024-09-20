package 백준.gold.줄어드는_수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        new Main().solution(n);
    }

    private void solution(int n) {
        Set<Long> set = new HashSet<>();
        set.add(0L);
        for (int i = 0; i < 10; i++) {
            find(i, set, 0, 0);
        }
        List<Long> list = set.stream()
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
        if (set.size() < n) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n - 1));
        }
    }

    private void find(int number, Set<Long> list, long sum, int depth) {
        sum += (long) (number * Math.pow(10, depth));
        list.add(sum);
        for (int i = number + 1; i < 10; i++) {
            find(i, list, sum, depth + 1);
        }
    }
}
