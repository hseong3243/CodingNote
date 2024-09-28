package 백준.gold.친구_네트워크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < test; t++) {
            int f = Integer.parseInt(br.readLine());
            Map<String, String> parents = new HashMap<>();
            Map<String, Integer> networks = new HashMap<>();
            for(int i=0; i<f; i++) {
                String[] split = br.readLine().split(" ");
                String friendA = split[0];
                String friendB = split[1];
                String parentA = findParent(parents, friendA);
                String parentB = findParent(parents, friendB);

                Integer sizeA = networks.computeIfAbsent(parentA, key -> 1);
                Integer sizeB = networks.computeIfAbsent(parentB, key -> 1);

                int parentCompare = parentA.compareTo(parentB);
                if(parentCompare == 0) {
                    result.append(sizeA).append("\n");
                    continue;
                }
                if(parentCompare < 0) {
                    parents.put(parentB, parentA);
                } else {
                    parents.put(parentA, parentB);
                }

                int sum = sizeA + sizeB;
                networks.put(parentA, sum);
                networks.put(parentB, sum);
                result.append(sum).append("\n");
            }
        }
        System.out.println(result);
    }

    private static String findParent(Map<String, String> parents, String friend) {
        String parent = parents.computeIfAbsent(friend, key -> key);
        if(parent.equals(friend)) {
            return friend;
        }
        String findParent = findParent(parents, parent);
        parents.put(friend, findParent);
        return findParent;
    }
}
