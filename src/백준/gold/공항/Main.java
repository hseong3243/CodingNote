package 백준.gold.공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] flight = new int[p];
        for (int i = 0; i < p; i++) {
            flight[i] = Integer.parseInt(br.readLine());
        }
        new Main().solution(g, p, flight);
    }

    private void solution(int g, int p, int[] flights) {
        int[] gate = new int[g + 1];
        int[] memo = new int[g + 1];
        for(int i=1; i<=g; i++) {
            memo[i] = i;
        }
        int count = 0;
        for (int flight : flights) {
            if (gate[flight] == 0) {
                gate[flight] = flight;
                memo[flight] = flight;
            } else {
                int maxNumberGate = findMaxNumberGate(flight, gate, memo);
                if (maxNumberGate == 0) {
                    break;
                }
                gate[maxNumberGate] = flight;
                memo[flight] = maxNumberGate;
            }
            count++;
        }
        System.out.println(count);
    }

    private int findMaxNumberGate(int flight, int[] gate, int[] memo) {
        for (int i = memo[flight] - 1; i >= 1; i--) {
            if (gate[i] == 0) {
                return i;
            }
            if(memo[i] != i) {
                i = memo[i];
            }
        }
        return 0;
    }
}
