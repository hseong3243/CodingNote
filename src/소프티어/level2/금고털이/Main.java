package 소프티어.level2.금고털이;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int w = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int[] m = new int[n];
        int[] p = new int[n];
        for(int i=0; i<n; i++) {
            String[] split = br.readLine().split(" ");
            m[i] = Integer.parseInt(split[0]);
            p[i] = Integer.parseInt(split[1]);
        }
        new Main().solution(w, n, m, p);
    }

    static class Item {
        int weight;
        int costPerWeight;

        public Item(int w, int c) {
            this.weight = w;
            costPerWeight = c;
        }
    }

    public void solution(int w, int n, int[] m, int[] p) {
        List<Item> items = new ArrayList<>();
        for(int i=0; i<n; i++) {
            items.add(new Item(m[i], p[i]));
        }
        items = items.stream().sorted((a, b) -> b.costPerWeight - a.costPerWeight).collect(Collectors.toList());
        int totalPrice = 0;
        int totalWeight = w;
        for(Item i : items) {
            if(i.weight <= totalWeight) {
                totalPrice += i.costPerWeight * i.weight;
                totalWeight -= i.weight;
            } else {
                totalPrice += i.costPerWeight * totalWeight;
                break;
            }
        }
        System.out.println(totalPrice);
    }
}
